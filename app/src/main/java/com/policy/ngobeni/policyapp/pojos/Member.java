package com.policy.ngobeni.policyapp.pojos;

import java.io.Serializable;

public class Member implements Serializable{
    private String firstName;
    private String lastName;
    private Long idNumber;
    private String relationShip;
    private String _gennder;

    public Member(String firstName, String lastName, Long idNumber, String relationShip,String _gennder) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.relationShip = relationShip;
        this._gennder = _gennder;
    }

    public Member(String firstName, String lastName, Long idNumber,String _gennder) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this._gennder = _gennder;
    }

    public String get_gennder() {
        return _gennder;
    }

    public void set_gennder(String _gennder) {
        this._gennder = _gennder;
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
