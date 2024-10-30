package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Housing;
import assurance.contrat.services.HousingService;
import assurance.contrat.repository.HousingRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRep housingRep;

    @Autowired
    public HousingServiceImpl(HousingRep housingRep) {
        this.housingRep = housingRep;
    }

    @Override
    public void save(Housing housing){
        housingRep.save(housing);
    }

    @Override
    public Housing findById(Long id){
        return housingRep.findById(id);
    }

    @Override
    public List<Housing> getHousingInsurancesForUser(Long userId) {
        return housingRep.findHousingByUserId(userId);
    }

    @Override
    public double calculPrice(Housing housing) {
        double base = 300;
        double finalAmount = base;

        if ("House".equalsIgnoreCase(housing.getTypeHome())) {
            finalAmount += PercentageIncrease(2, base);
        }

        if ("High-risk".equalsIgnoreCase(housing.getLocation())) {
            finalAmount += PercentageIncrease(5, base);
        }

        if (housing.getHomeValue() > 200000) {
            finalAmount += PercentageIncrease(10, base);
        }

        String securitySystem = housing.getSecuritySystem();
        if ("None".equalsIgnoreCase(securitySystem)) {
            finalAmount += PercentageIncrease(15, base);
        } else {
            finalAmount -= PercentageIncrease(15, base);
        }

        System.out.println(finalAmount);
        return finalAmount;
    }

    private double PercentageIncrease(double percentage, double price) {
        return (percentage / 100) * price;
    }



}
