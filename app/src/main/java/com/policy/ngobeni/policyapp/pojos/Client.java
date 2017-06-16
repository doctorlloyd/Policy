package com.policy.ngobeni.policyapp.pojos;

import java.io.Serializable;

public class Client implements Serializable{

    private String firstName;
    private String lastName;
    private Long idNumber;
    private String address;
    private String cellNumber;
    private String gender;

    public Client(String firstName, String lastName, Long idNumber, String address, String cellNumber,String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.cellNumber = cellNumber;
        this.gender = gender;
    }

    public Client() {
    }

    public String getFirstName() {

        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }
}
