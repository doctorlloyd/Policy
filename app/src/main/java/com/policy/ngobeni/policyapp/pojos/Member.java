package com.policy.ngobeni.policyapp.pojos;

import java.io.Serializable;

public class Member implements Serializable{
    private String firstName;
    private String lastName;
    private Long idNumber;
    private String relationShip;

    public Member(String firstName, String lastName, Long idNumber, String relationShip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.relationShip = relationShip;
    }

    public Member(String firstName, String lastName, Long idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }
}
