package test; 

import deploy.DeploymentConfiguration;
import javax.persistence.Persistence;

public class Tester { 
    public static void main(String[] args) {
        Persistence.generateSchema(DeploymentConfiguration.PU_NAME,null);
    }
}