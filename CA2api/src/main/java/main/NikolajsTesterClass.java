package main;

import exception.PersonNotFoundException;
import facade.HobbyFacade;
import facade.PersonFacade;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NikolajsTesterClass {

    public static void main(String[] args) {
        try {
            PersonFacade pf = new PersonFacade();
            Map<String, String> params = new HashMap<>();
//        params.put("street", "Hansstrede");
//        params.put("zipCode", "388");
//        pf.getPersonDTOWithFilters(params).forEach(System.out::println);
pf.getPersonDTOByHobby("fodbold").forEach(System.out::println);
//        System.out.println(pf.getPersonDTOById(1));
//        System.out.println(pf.getPersonDTOByPhone("12345678"));
//        System.out.println(pf.getPersonDTOByEmail("Nikolaj.Stephan.zip91@mail.dk"));
//        HobbyFacade hf = new HobbyFacade();
//        hf.getAllHobby().forEach(System.out::println);
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(NikolajsTesterClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
