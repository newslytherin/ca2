/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Phone;

/**
 *
 * @author Stephan
 */
public class PhoneDTO
{

    private String number;
    private String description;
    private String infoEntity;

    public PhoneDTO(Phone phone)
    {
        this.number = phone.getNumber();
        this.description = phone.getDescription();

        if (phone.getInfoEntity() != null)
        {
            this.infoEntity = phone.getInfoEntity().getName();

        }

    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getInfoEntity()
    {
        return infoEntity;
    }

    public void setInfoEntity(String infoEntity)
    {
        this.infoEntity = infoEntity;
    }

    @Override
    public String toString()
    {
        return "PhoneDTO{" + "number=" + number + ", description=" + description + ", infoEntity=" + infoEntity + '}';
    }
    
    

}
