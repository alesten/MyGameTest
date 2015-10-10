package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically populated with all resources defined in the project. If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.UserResource.class);
        resources.add(rest.exception.AllExceptionMapper.class);
        resources.add(rest.exception.UserAlreadyExistsExceptionMapper.class);
        resources.add(rest.exception.UserNotFoundExceptionMapper.class);
    }

}
