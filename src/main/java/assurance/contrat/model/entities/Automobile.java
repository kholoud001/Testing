package assurance.contrat.model.entities;

import assurance.contrat.model.enums.InsuranceType;
import jakarta.persistence.*;

@Entity
@Table(name = "automobile_insurances")
public class Automobile extends Insurance {

    @Column(name="driver_age", nullable = false)
    private int driverAge;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name="vehicle_use",nullable = false)
    private String vehicleUse;

    @Column(name="driving_history",nullable = false)
    private String drivingHistory;

    public Automobile(){}

    public Automobile(double price, InsuranceType type, int driverAge, String vehicleUse, String drivingHistory, Vehicle vehicle){
        super(price, type);
        this.driverAge=driverAge;
        this.vehicleUse=vehicleUse;
        this.drivingHistory= drivingHistory;
        this.vehicle=vehicle;
    }


    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleUse() {
        return vehicleUse;
    }

    public void setVehicleUse(String vehicleUse) {
        this.vehicleUse = vehicleUse;
    }

    public String getDrivingHistory() {
        return drivingHistory;
    }

    public void setDrivingHistory(String drivingHistory) {
        this.drivingHistory = drivingHistory;
    }
}
