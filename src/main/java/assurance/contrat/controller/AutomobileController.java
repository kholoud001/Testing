package assurance.contrat.controller;

import assurance.contrat.model.entities.Automobile;
import assurance.contrat.model.entities.User;
import assurance.contrat.model.enums.InsuranceType;
import assurance.contrat.services.AutomobileService;
import assurance.contrat.services.VehicleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/automobile")
public class AutomobileController {

    private final VehicleService vehicleService;
    private final AutomobileService automobileService;

    @Autowired
    public AutomobileController(VehicleService vehicleService, AutomobileService automobileService){
        this.vehicleService= vehicleService;
        this.automobileService= automobileService;
    }

    @GetMapping("/my-insurances")
    public String showUserAutomobileInsurances(Model model, HttpSession session) {
        Long loggedInUserId = getLoggedInUserId(session);
        if (loggedInUserId != null) {
            List<Automobile> userInsurances = automobileService.getAutomobileInsurancesForUser(loggedInUserId);
        model.addAttribute("automobileInsurances", userInsurances);
        return "quotes/automobile";
        } else {
            return "redirect:/login";
        }
    }

    private Long getLoggedInUserId(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    @GetMapping("/form")
    public String showAutomobileForm(Model model) {
        model.addAttribute("automobile", new Automobile());
        model.addAttribute("insuranceTypes", List.of(InsuranceType.values()));
        model.addAttribute("vehicles", vehicleService.findAll());
        return "forms/automobile_insurance";
    }

    @PostMapping("/submit")
    public String submitAutomobileForm(@ModelAttribute Automobile automobile, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            automobile.setUser(user);
            double calculatedPrice = automobileService.calculPrice(automobile);
            automobile.setPrice(calculatedPrice);
            automobileService.save(automobile);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/delete")
    public String deleteInsurance(@RequestParam("insuranceId") Long insuranceId, RedirectAttributes redirectAttributes) {
        try {
            automobileService.deleteInsuranceById(insuranceId);
            redirectAttributes.addFlashAttribute("message", "Insurance record deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete the insurance record.");
        }
        return "redirect:/quotes/automobile";
    }

}
