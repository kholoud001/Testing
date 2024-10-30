package assurance.contrat.repository;

import assurance.contrat.model.entities.Housing;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HousingRep {
    @Transactional
    void save(Housing housing);

    Housing findById(Long id);

    @Transactional
    List<Housing> findHousingByUserId(Long userId);
}
