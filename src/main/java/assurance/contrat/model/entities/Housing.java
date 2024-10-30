package assurance.contrat.model.entities;

import assurance.contrat.model.enums.InsuranceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "housing_insurances")

public class Housing extends Insurance{

    @Column(name="home_value", nullable= false)
    private double homeValue;

    @Column(name="home_type", nullable = false)
    private String typeHome;

    @Column(nullable = false)
    private String location;

    @Column(name="security_system", nullable = false)
    private String securitySystem;

    public Housing(){}

    public Housing(double price, InsuranceType type, double homeValue, String typeHome, String location, String security){
        super(price, type);
        this.homeValue=homeValue;
        this.typeHome= typeHome;
        this.location= location;
        this.securitySystem = security;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(double value) {
        this.homeValue = value;
    }

    public String getTypeHome() {
        return typeHome;
    }

    public void setTypeHome(String type) {
        this.typeHome = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSecuritySystem() {
        return securitySystem;
    }

    public void setSecuritySystem(String securitySystem) {
        this.securitySystem = securitySystem;
    }
}
