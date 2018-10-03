package main;

import facade.PersonFacade;
import java.util.HashMap;
import java.util.Map;

public class NikolajsTesterClass {

    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade();
        Map<String, String> params = new HashMap<>();
//        params.put("street", "Nørgaardsvej");
//        params.put("zipCode", "2800");
//        params.put("hobby", "Gaming");
//        System.out.println(pf.getPersonDTOWithFilters(params));
        //System.out.println(pf.getPersonDTOById(1));
//        System.out.println(pf.getPersonDTOByEmail("Nikolaj.Najaholm@mail.dk"));
    }
}
