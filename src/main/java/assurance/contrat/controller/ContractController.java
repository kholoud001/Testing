package assurance.contrat.controller;

import assurance.contrat.model.entities.*;
import assurance.contrat.model.enums.InsuranceType;
import assurance.contrat.services.AutomobileService;
import assurance.contrat.services.ContractService;
import assurance.contrat.services.HealthService;
import assurance.contrat.services.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private AutomobileService automobileService;

    @Autowired
    private HealthService healthService;

    @Autowired
    private HousingService housingService;

    @Autowired
    private ContractService contractService;


    @GetMapping("/list")
    public String viewContracts(Model model) {
        List<Contract> activeContracts = contractService.getActiveContracts();
        model.addAttribute("contracts", activeContracts);
        return "contracts";
    }

    @GetMapping("/edit/{id}")
    public String editContract(@PathVariable("id") Long id, Model model) {
        Contract contract = contractService.getContractById(id);
        System.out.println("contract Id"+contract.getId());
        if (contract != null) {
            model.addAttribute("contract", contract);
            model.addAttribute("insuranceType", contract.getInsurance().getType());
            model.addAttribute("insuranceId", contract.getInsurance().getId());
            System.out.println("insurance id"+ contract.getInsurance().getId());
            return "forms/edit_contract";
        }
        return "redirect:/contract/list";
    }

    @GetMapping("/automobile")
    public String showAutomobileContractForm(@RequestParam("insuranceId") Long insuranceId, Model model) {
        return showContractForm(insuranceId, model, InsuranceType.Automobile);
    }

    @GetMapping("/health")
    public String showHealthContractForm(@RequestParam("insuranceId") Long insuranceId, Model model) {
        return showContractForm(insuranceId, model, InsuranceType.Health);
    }
    @GetMapping("/housing")
    public String showHousingContractForm(@RequestParam("insuranceId") Long insuranceId, Model model) {
        return showContractForm(insuranceId, model, InsuranceType.Housing);
    }


    private String showContractForm(Long insuranceId, Model model, InsuranceType insuranceType) {
        System.out.println("Entered showContractForm method with insuranceId: " + insuranceId);
        Contract existingContract = contractService.findByInsuranceId(insuranceId);

        if (existingContract != null) {
            System.out.println("Existing Contract ID: " + existingContract.getId());
            return "redirect:/contract/list";
        } else {
            System.out.println("No existing contract found, proceeding to create a new one.");
        }

        LocalDate subscriptionDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = subscriptionDate.format(formatter);

        System.out.println("id captured: " + insuranceId);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("insuranceId", insuranceId);
        model.addAttribute("contract", new Contract());
        model.addAttribute("insuranceType", insuranceType);

        return "forms/contract";
    }

    @PostMapping("/submit")
    public String submitContract(
            @ModelAttribute("contract") Contract contract,
            @RequestParam("document") MultipartFile document,
            @RequestParam("insuranceType") InsuranceType insuranceType,
            RedirectAttributes redirectAttributes) {

        System.out.println("Contract ID: " + contract.getId());

        Insurance insurance = null;

        if (insuranceType == InsuranceType.Automobile) {
            Automobile automobile = automobileService.findById(contract.getId());
            if (automobile != null) {
                insurance = automobile;
                System.out.println("Found Automobile Insurance: " + automobile);
            } else {
                System.out.println("Automobile not found for ID: " + contract.getId());
            }
        } else if (insuranceType == InsuranceType.Health) {
            Health healthInsurance = healthService.findById(contract.getId());
            if (healthInsurance != null) {
                insurance = healthInsurance;
                System.out.println("Found Health Insurance: " + healthInsurance);
            } else {
                System.out.println("Health Insurance not found for ID: " + contract.getId());
            }
        } else if (insuranceType == InsuranceType.Housing) {
            Housing housingInsurance = housingService.findById(contract.getId());
            if (housingInsurance != null) {
                insurance = housingInsurance;
                System.out.println("Found Housing Insurance: " + housingInsurance);
            } else {
                System.out.println("Housing Insurance not found for ID: " + contract.getId());
            }
        }

        if (insurance != null) {
            contract.setInsurance(insurance);
            contract.setSubscriptionDate(LocalDate.now());
            contract.setExpirationDate(LocalDate.now().plusDays(30));
            contract.setStatus(true);

            // Save the document and set the document path
            String documentPath = saveDocument(document);
            if (documentPath != null) {
                contract.setDocumentPath(documentPath);
            } else {
                System.out.println("Document is empty or could not be saved.");
                redirectAttributes.addFlashAttribute("errorMessage", "Document upload failed. Contract not generated.");
                return "redirect:/home";
            }

            // Add contract to the database
            contractService.addContract(contract);
            redirectAttributes.addFlashAttribute("successMessage", "Contract generated successfully.");
            redirectAttributes.addFlashAttribute("formattedDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getSubscriptionDate()));
            return "redirect:/contract/list";
        }

        System.out.println("No valid insurance found for contract ID: " + contract.getId());
        redirectAttributes.addFlashAttribute("errorMessage", "No valid insurance found. Contract not generated.");
        return "redirect:/home";
    }



    @PostMapping("/update")
    public String updateContract(
            @ModelAttribute("contract") Contract contract,
            @RequestParam("insuranceId") Long insuranceId,
            @RequestParam("document") MultipartFile document,
            RedirectAttributes redirectAttributes) {

        if (contract == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Contract cannot be null.");
            return "redirect:/contract/list";
        }

        if (contract.getSubscriptionDate() == null) {
            contract.setSubscriptionDate(LocalDate.now());
        }
        if (contract.getExpirationDate() == null) {
            contract.setExpirationDate(LocalDate.now().plusDays(30));
        }

        contract.setStatus(true);

        if (insuranceId != null) {
            Insurance insurance = contractService.findInsuranceById(insuranceId);
            if (insurance != null) {
                contract.setInsurance(insurance);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Insurance not found.");
                return "redirect:/contract/list";
            }
        }

        // Handle the document upload and set its path if present
        String documentPath = saveDocument(document);
        if (documentPath != null) {
            contract.setDocumentPath(documentPath);
        }

        // Call the update service to persist the changes
        try {
            contractService.updateContract(contract);
            redirectAttributes.addFlashAttribute("successMessage", "Contract updated successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating contract: " + e.getMessage());
        }

        return "redirect:/contract/list";
    }


    @PostMapping("/delete/{id}")
    public String deleteContract(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Contract contract = contractService.getContractById(id);
            if (contract != null) {
                contract.setStatus(false);
                contractService.updateContractStatus(contract);
                redirectAttributes.addFlashAttribute("successMessage", "Contract deleted successfully.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Contract not found.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting contract: " + e.getMessage());
        }
        return "redirect:/contract/list";
    }




    private String saveDocument(MultipartFile document) {
        if (document.isEmpty()) {
            return null;
        }

        try {
            String uploadDir = "C:/jee/contrat/src/main/webapp/files";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueFilename = UUID.randomUUID().toString() + "_" + document.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(document.getInputStream(), filePath);

            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }












}
