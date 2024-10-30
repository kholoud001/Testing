package assurance.contrat.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CustomEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;

    private CustomEntityManagerFactory() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("Contract_Assurance");
        }
        return entityManagerFactory;
    }
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}
