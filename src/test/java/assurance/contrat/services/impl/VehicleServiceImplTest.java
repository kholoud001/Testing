package assurance.contrat.services.impl;

import assurance.contrat.model.entities.Vehicle;
import assurance.contrat.repository.VehicleRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRep vehicleRep;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Vehicle> mockVehicles = new ArrayList<>();
        mockVehicles.add(new Vehicle());
        when(vehicleRep.findAll()).thenReturn(mockVehicles);

        List<Vehicle> vehicles = vehicleService.findAll();

        assertNotNull(vehicles);
        assertEquals(1, vehicles.size());
        verify(vehicleRep, times(1)).findAll();
    }

    @Test
    void testSaveVehicle() {
        Vehicle vehicleToSave = new Vehicle();

        vehicleService.saveVehicle(vehicleToSave);

        verify(vehicleRep, times(1)).save(vehicleToSave);
    }

}