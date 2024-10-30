package assurance.contrat.repository.impl;

import assurance.contrat.model.entities.Housing;
import assurance.contrat.model.entities.User;
import assurance.contrat.repository.HousingRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HousingRepImpl implements HousingRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Housing housing) {
        User managedUser = entityManager.merge(housing.getUser());
        housing.setUser(managedUser);
        entityManager.persist(housing);
    }
    @Override
    public Housing findById(Long id) {
        return entityManager.find(Housing.class, id);
    }


    @Override
    @Transactional
    public List<Housing> findHousingByUserId(Long userId) {
        String jpql = "SELECT h FROM Housing h WHERE h.user.id = :userId";
        TypedQuery<Housing> query = entityManager.createQuery(jpql, Housing.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
