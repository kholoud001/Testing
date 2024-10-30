package assurance.contrat.repository;

import assurance.contrat.model.entities.Health;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HealthRep {
    @Transactional
    void save(Health health);

    Health findById(Long id);

    @Transactional
    List<Health> findHealthByUserId(Long userId);
}
