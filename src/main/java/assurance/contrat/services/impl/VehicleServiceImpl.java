package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Vehicle;
import assurance.contrat.repository.VehicleRep;
import assurance.contrat.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRep vehiclerep;

    @Override
    public List<Vehicle> findAll(){
      return  vehiclerep.findAll();
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehiclerep.save(vehicle);
    }

    }
