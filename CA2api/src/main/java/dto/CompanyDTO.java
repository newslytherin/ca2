/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Company;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO
{

    private Integer id = null;

    //infoentity
    private String name = null;
    private String email = null;
    private String address = null;

    private Integer zipCode = null;
    private String city = null;

    private String cvr = null;
    private String description = null;

    private Integer numEmployees = null;
    private Double marketValue = null;

    //phones
    private List<String> phones = null;

    //address
//    private String addressInfo = null;
//    private String street = null;
    //cityinfo
    public CompanyDTO(Company c)
    {

        this.id = c.getId();

        this.email = c.getEmail();

        //company
        this.name = c.getName();
        this.description = c.getDescription();
        this.cvr = c.getCvr();
        this.numEmployees = c.getNumEmployees();
        this.marketValue = c.getMarketValue();

        if (c.getPhones() != null && !c.getPhones().isEmpty())
        {
            this.phones = c.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
        }

        if (c.getAddress() != null)
        {
            this.address = c.getAddress().getStreet() + " " + c.getAddress().getAddressInfo();

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
        return "CompanyDTO{" + "id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", zipCode=" + zipCode + ", city=" + city + ", cvr=" + cvr + ", description=" + description + ", numEmployees=" + numEmployees + ", marketValue=" + marketValue + ", phones=" + phones + '}';
    }

}
