package assurance.contrat.repository;

import assurance.contrat.model.entities.Vehicle;

import java.util.List;

public interface VehicleRep {
    List<Vehicle> findAll();
    void save(Vehicle vehicle);
}
