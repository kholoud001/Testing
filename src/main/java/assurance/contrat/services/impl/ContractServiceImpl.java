package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Contract;
import assurance.contrat.model.entities.Insurance;
import assurance.contrat.repository.AutomobileRep;
import assurance.contrat.repository.ContractRep;
import assurance.contrat.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRep contractRep;

    @Autowired
    private AutomobileRep automobileRep;

//    @Override
//    @Transactional
//    public void saveContract(Contract contract, Long insuranceId) {
//        Automobile automobile = automobileRep.findById(insuranceId);
//
//        contract.setInsurance(automobile);
//        contractRep.save(contract);
//    }

    @Override
    public void addContract(Contract contract) {

        contract.setSubscriptionDate(LocalDate.now());
        contract.setStatus(true);
        contractRep.save(contract);
    }

    @Override
    @Transactional
    public void updateContractStatus(Contract contract) {
        contract.setStatus(false);
        contractRep.save(contract);
    }

    @Transactional
    public void updateContract(Contract contract) {
        Contract existingContract = contractRep.findById(contract.getId());
        existingContract.setInsurance(contract.getInsurance());
        contractRep.save(existingContract);
    }


    @Override
    public Contract getContractById(Long id) {
        return contractRep.findById(id);
    }

    @Override
    public Insurance findInsuranceById(long id){
        return contractRep.findInsuranceById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRep.findAll();
    }

    @Override
    public Contract findByInsuranceId(Long insuranceId) {
        return contractRep.findByInsuranceId(insuranceId);
    }


    @Override
    public void deleteContract(Long id) {
        contractRep.delete(id);
    }

//    @Override
//    public void updateContract(Contract contract) {
//        contractRep.save(contract);
//    }


    @Override
    public List<Contract> getActiveContracts() {
        List<Contract> contracts = contractRep.findAll();

        for (Contract contract : contracts) {
            if (contract.getExpirationDate().isBefore(LocalDate.now()) && contract.isStatus()) {
                contract.setStatus(false);
                updateContract(contract);
            }
        }

        return contracts.stream()
                .filter(Contract::isStatus)
                .collect(Collectors.toList());
    }



}
