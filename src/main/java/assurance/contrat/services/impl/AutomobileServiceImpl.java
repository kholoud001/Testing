package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Automobile;
import assurance.contrat.repository.AutomobileRep;
import assurance.contrat.services.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileRep automobileRep;

    @Autowired
    public AutomobileServiceImpl(AutomobileRep automobileRep) {
        this.automobileRep = automobileRep;
    }

    @Override
    public void save(Automobile automobile) {
        automobileRep.save(automobile);
    }

    @Override
    public Automobile findById(Long id){
        return automobileRep.findById(id);
    }

    @Override
    public List<Automobile> getAutomobileInsurancesForUser(Long userId) {
        return automobileRep.findAutomobileByUserId(userId);
    }

    @Override
    public void deleteInsuranceById(Long insuranceId) {
        automobileRep.deleteById(insuranceId);
    }

    @Override
    public double calculPrice(Automobile automobile) {
        double base = 500;
        double finalAmount = base;

        if (automobile.getDriverAge() < 25) {
            finalAmount += PercentageIncrease(10, base);
        }

        if (automobile.getVehicle() != null && "Luxury".equals(automobile.getVehicle().getType())) {
            finalAmount += PercentageIncrease(15, base);
        }

        if ("Professional".equals(automobile.getVehicleUse())) {
            finalAmount += PercentageIncrease(10, base);
        }

        if (automobile.getDrivingHistory() != null && !automobile.getDrivingHistory().trim().isEmpty()) {
            finalAmount += PercentageIncrease(10, base);
        } else {
            finalAmount -= PercentageIncrease(20, base);
        }

        System.out.println(finalAmount);
        return finalAmount;
    }

    private double PercentageIncrease(double percentage, double price) {
        return (percentage / 100) * price;
    }

}





