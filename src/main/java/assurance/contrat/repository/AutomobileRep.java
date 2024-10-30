package assurance.contrat.repository;

import assurance.contrat.model.entities.Automobile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AutomobileRep {
    void save(Automobile automobile);

    Automobile findById(Long id);

    @Transactional
    List<Automobile> findAutomobileByUserId(Long userId);

    @Transactional
    void deleteById(Long id);
}
