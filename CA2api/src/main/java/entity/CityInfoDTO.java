/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Stephan
 */
public class CityInfoDTO {
    private int zipCode;
    private String city;
    private List<Address> addresses;

    public CityInfoDTO(CityInfo ci) {
        this.zipCode = ci.getZipCode();
        this.city = ci.getCity();
        //addresses = ci.getAddresses().stream().map(Address::getName).collect(Collectors.toList());
        this.addresses = ci.getAddresses().stream().collect(Collectors.toList()); // ???
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
}
