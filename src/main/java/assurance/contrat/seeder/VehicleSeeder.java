package assurance.contrat.seeder;

import assurance.contrat.model.entities.Vehicle;
import assurance.contrat.repository.VehicleRep;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class VehicleSeeder {

    @Autowired
    private VehicleRep vehicleRep;

    @Transactional
    public void seed() {
        if (vehicleRep.findAll().isEmpty()) {
            seedVehicles();
        }
    }

    private void seedVehicles() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setModele("Model S");
        vehicle1.setMarque("Tesla");
        vehicle1.setType("Electric");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setModele("Civic");
        vehicle2.setMarque("Honda");
        vehicle2.setType("Sedan");

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setModele("F-150");
        vehicle3.setMarque("Ford");
        vehicle3.setType("Truck");

        vehicleRep.save(vehicle1);
        vehicleRep.save(vehicle2);
        vehicleRep.save(vehicle3);
    }


}
