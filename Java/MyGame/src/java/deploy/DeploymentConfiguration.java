package deploy; 

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DeploymentConfiguration implements ServletContextListener { 
public static String PU_NAME = "pu_development"; //USE the RIGHT name here 

    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> env = System.getenv();
        //If we are running in the OPENSHIFT environment change the pu-name 

        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "pu_OPENSHIFT";
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}