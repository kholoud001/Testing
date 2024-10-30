package assurance.contrat.repository.impl;

import assurance.contrat.model.entities.Contract;
import assurance.contrat.model.entities.Insurance;
import assurance.contrat.repository.ContractRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContractRepImpl implements ContractRep {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(Contract contract) {
        if (contract.getId() == null) {
            entityManager.persist(contract);
        } else {
            entityManager.merge(contract);
        }
    }

    @Override
    public Contract findById(Long id) {
        return entityManager.find(Contract.class, id);
    }

    @Override
    public Insurance findInsuranceById(Long id) {
        return entityManager.find(Insurance.class, id);
    }



    @Override
    public List<Contract> findAll() {
        return entityManager.createQuery("FROM Contract", Contract.class).getResultList();
    }

    @Override
    public Contract findByInsuranceId(Long insuranceId) {
        String jpql = "SELECT c FROM Contract c WHERE c.insurance.id = :insuranceId";
        TypedQuery<Contract> query = entityManager.createQuery(jpql, Contract.class);
        query.setParameter("insuranceId", insuranceId);

        List<Contract> contracts = query.getResultList();
        return contracts.isEmpty() ? null : contracts.get(0);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Contract contract = findById(id);
        if (contract != null) {
            entityManager.remove(contract);
        }
    }



}
