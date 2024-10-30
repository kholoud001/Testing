package assurance.contrat.repository.impl;

import assurance.contrat.model.entities.Vehicle;
import assurance.contrat.repository.VehicleRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRepImpl implements VehicleRep {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Vehicle> findAll() {
        return entityManager.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
    }
    @Override
    public void save(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }
}
