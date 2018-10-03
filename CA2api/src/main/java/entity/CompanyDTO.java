/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO {
    
    //company
    private final String name;
    private final String description;
    private final String cvr;
    private final int numEmployees;
    private final double marketValue;
    
    //phones
    private final List<String> phones;
    
    //address
    private final String addressInfo;
    private final String street;
    
    //cityinfo
    private final int zipCode;
    private final String city;

    public CompanyDTO(Company c)
    {
        //company
        this.name = c.getName();
        this.description = c.getDescription();
        this.cvr = c.getCvr();
        this.numEmployees = c.getNumEmployees();
        this.marketValue = c.getMarketValue();
        
        //phones -> maybe es works?
        this.phones = c.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
        
        //address
        this.addressInfo = c.getAddress().getAddressInfo();
        this.street = c.getAddress().getStreet();
        
        //cityinfo
        this.zipCode = c.getAddress().getCityInfo().getZipCode();
        this.city = c.getAddress().getCityInfo().getCity();
    }
    
    
    
    
    

}
