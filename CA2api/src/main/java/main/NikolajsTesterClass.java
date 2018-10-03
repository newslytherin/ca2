package main;

import facade.PersonFacade;
import java.util.HashMap;
import java.util.Map;

public class NikolajsTesterClass {

    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade();
        Map<String, String> params = new HashMap<>();
        params.put("street", "Nørgaardsvej");
        params.put("zipCode", "2800");
        params.put("hobby", "Gaming");
        pf.getPersonDTOWithFilters(params).forEach(System.out::println);
    }
}
