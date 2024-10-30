package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Health;
import assurance.contrat.repository.HealthRep;
import assurance.contrat.services.HealthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthServiceImpl implements HealthService {

    private final HealthRep healthRep;

    @Autowired
    public HealthServiceImpl(HealthRep healthRep) {
        this.healthRep = healthRep;
    }

    @Override
    public void save(Health health){
        healthRep.save(health);
    }

    @Override
    public Health findById(Long id){
        return healthRep.findById(id);
    }

    @Override
    public List<Health> getHealthInsurancesForUser(Long userId) {
        return healthRep.findHealthByUserId(userId);
    }

    @Override
    public double calculPrice(Health health) {
        double base = 150;
        double finalAmount = base;

        if (health.getAge() > 60) {
            finalAmount += PercentageIncrease(20, base);
        }

        if ("Yes".equalsIgnoreCase(health.getHealthState())) {
            finalAmount += PercentageIncrease(30, base);
        }

        if ("Basic".equalsIgnoreCase(health.getMedicalCoverageType())) {
            finalAmount -= PercentageIncrease(10, base);
        } else if ("Premium".equalsIgnoreCase(health.getMedicalCoverageType())) {
            finalAmount += PercentageIncrease(5, base);
        }

        System.out.println(finalAmount);
        return finalAmount;
    }

    private double PercentageIncrease(double percentage, double price) {
        return (percentage / 100) * price;
    }

}
