/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

public class CompanyDTO {
    
    //company
    private final String name;
    private final String description;
    private final String cvr;
    private final int numEmployees;
    private final double marketValue;
    
    

    public CompanyDTO(Company c)
    {
        name = c.getName();
        description = c.getDescription();
        cvr = c.getCvr();
        numEmployees = c.getNumEmployees();
        marketValue = c.getMarketValue();
    }
    
    
    
    
    

}
