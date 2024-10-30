package assurance.contrat.services;

import assurance.contrat.model.entities.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    void saveVehicle(Vehicle vehicle);
}
