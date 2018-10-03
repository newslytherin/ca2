package main;

import javax.persistence.Persistence;

public class SchemaGenerator {

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
}
