/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO
{

    //infoentity
    private String email = null;

    //company
    private String name = null;
    private String description = null;
    private String cvr = null;
    private Integer numEmployees = null;
    private Double marketValue = null;

    //phones
    private List<String> phones = null;

    //address
    private String addressInfo = null;
    private String street = null;

    //cityinfo
    private Integer zipCode = null;
    private String city = null;

    public CompanyDTO(Company c)
    {

        System.out.println(c);

        this.email = c.getEmail();

        //company
        this.name = c.getName();
        this.description = c.getDescription();
        this.cvr = c.getCvr();
        this.numEmployees = c.getNumEmployees();
        this.marketValue = c.getMarketValue();

        if(!c.getPhones().isEmpty()){
            this.phones = c.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
        }
        
        if (c.getAddress() != null)
        {
            this.addressInfo = c.getAddress().getAddressInfo();
            this.street = c.getAddress().getStreet();

            if (c.getAddress().getCityInfo() != null)
            {
                this.zipCode = c.getAddress().getCityInfo().getZipCode();
                this.city = c.getAddress().getCityInfo().getCity();
            }
        }

    }

    @Override
    public String toString()
    {
        return "CompanyDTO{" + "email=" + email + ", name=" + name + ", description=" + description + ", cvr=" + cvr + ", numEmployees=" + numEmployees + ", marketValue=" + marketValue + ", phones=" + phones + ", addressInfo=" + addressInfo + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city + '}';
    }

}
