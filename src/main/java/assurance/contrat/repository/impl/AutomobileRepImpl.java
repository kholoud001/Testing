package assurance.contrat.repository.impl;

import assurance.contrat.model.entities.Automobile;
import assurance.contrat.model.entities.User;
import assurance.contrat.repository.AutomobileRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AutomobileRepImpl implements AutomobileRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Automobile automobile) {
        User managedUser = entityManager.merge(automobile.getUser());
        automobile.setUser(managedUser);
        entityManager.persist(automobile);
    }


    @Override
    public Automobile findById(Long id) {
        return entityManager.find(Automobile.class, id);
    }

    @Override
    @Transactional
    public List<Automobile> findAutomobileByUserId(Long userId) {
        String jpql = "SELECT a FROM Automobile a WHERE a.user.id = :userId";
        TypedQuery<Automobile> query = entityManager.createQuery(jpql, Automobile.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Automobile automobile = entityManager.find(Automobile.class, id);
        if (automobile != null) {
            entityManager.remove(automobile);
        }
    }


}
