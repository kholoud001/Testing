package assurance.contrat.repository.impl;

import assurance.contrat.model.entities.Health;
import assurance.contrat.model.entities.User;
import assurance.contrat.repository.HealthRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HealthRepImpl implements HealthRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Health health) {
        User managedUser = entityManager.merge(health.getUser());
        health.setUser(managedUser);
        entityManager.persist(health);
    }

    @Override
    public Health findById(Long id) {
        return entityManager.find(Health.class, id);
    }


    @Override
    @Transactional
    public List<Health> findHealthByUserId(Long userId) {
        String jpql = "SELECT h FROM Health h WHERE h.user.id = :userId";
        TypedQuery<Health> query = entityManager.createQuery(jpql, Health.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
