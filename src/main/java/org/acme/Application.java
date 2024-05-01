package org.acme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            // Inicializar o EntityManagerFactory com base nas configurações do persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("NomeDaUnidadeDePersistencia");

            // Criar o EntityManager
            entityManager = entityManagerFactory.createEntityManager();

            // Verificar se a conexão foi bem-sucedida
            if (entityManager != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
            } else {
                System.out.println("Falha ao estabelecer conexão com o banco de dados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar o EntityManager e o EntityManagerFactory quando terminar de usá-los
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
