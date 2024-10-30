package assurance.contrat.repository;

import assurance.contrat.model.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRep {

    @Transactional
    void save(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();
}
