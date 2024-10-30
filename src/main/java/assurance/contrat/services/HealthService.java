package assurance.contrat.services;

import assurance.contrat.model.entities.Health;

import java.util.List;

public interface HealthService {
    void save(Health health);

    Health findById(Long id);

    List<Health> getHealthInsurancesForUser(Long userId);

    double calculPrice(Health health);
}
