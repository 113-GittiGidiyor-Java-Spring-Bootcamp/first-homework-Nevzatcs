package dev.patika.models;

import javax.persistence.Entity;
// implemented according to requirements
@Entity
public class PermanentInstructor extends Instructor{
    private double fixedSalary;

    public PermanentInstructor(String name, String address, String phoneNumber, double fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public PermanentInstructor() {}

    public double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
