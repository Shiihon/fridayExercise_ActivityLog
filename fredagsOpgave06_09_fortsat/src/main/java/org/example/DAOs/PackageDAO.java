package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Set;

public class PackageDAO implements IDAO<Package> {
    EntityManagerFactory emf;

    public PackageDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Package create(Package p) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } catch (
                ConstraintViolationException e) {
            System.out.println("Constraint violation " + e.getMessage());
        }
        return null;
    }

    @Override
    public Package update(Package p) {
        return null;
    }

    @Override
    public void delete(Package p) {

    }

    @Override
    public Package getById(Long id) {
        return null;
    }

    @Override
    public Set<Package> getAll() {
        return Set.of();
    }
}
