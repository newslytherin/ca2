package main;

import facade.HobbyFacade;
import facade.PersonFacade;
import java.util.HashMap;
import java.util.Map;

public class NikolajsTesterClass {

    public static void main(String[] args) {
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
    }
}
