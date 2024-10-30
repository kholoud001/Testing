package assurance.contrat.services;

import assurance.contrat.model.entities.Housing;

import java.util.List;

public interface HousingService {
    void save(Housing housing);


    Housing findById(Long id);

    List<Housing> getHousingInsurancesForUser(Long userId);

    double calculPrice(Housing housing);
}
