package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cors.CorsRequestFilter.class);
        resources.add(cors.CorsResponseFilter.class);
        resources.add(rest.AddressResource.class);
        resources.add(rest.CityResource.class);
        resources.add(rest.CompanyResource.class);
        resources.add(rest.HobbyResource.class);
        resources.add(rest.PersonResource.class);
        resources.add(rest.PhoneResource.class);
        resources.add(rest.PersonResource.class);
        resources.add(rest.AddressResource.class);
    }
    
}
