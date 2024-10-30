package assurance.contrat.services;

import assurance.contrat.model.entities.Automobile;

import java.util.List;

public interface AutomobileService {
    void save(Automobile automobile);

    Automobile findById(Long id);

    List<Automobile> getAutomobileInsurancesForUser(Long userId);

    void deleteInsuranceById(Long insuranceId);

    double calculPrice(Automobile automobile);
}
