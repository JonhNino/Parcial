package com.uptc.frw.executor;

import com.uptc.frw.conf.PersistenceUtil;
import com.uptc.frw.entity.Patrocinador;
import com.uptc.frw.entity.Persona;
import com.uptc.frw.entity.Proyecto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.getEntityManager();
        System.out.println("Con Conexion");
        //Crear un método que inserte en la base de datos con por lo menos 5 registros en cada tabla.
        createData(em);
        //Crear un método que permita consultar cuales son las personas que trabajan en un proyecto determinado.
        recorreLista(getPersonsByProjectId(em, 1L));
        //Crear un método que permita consultar los proyectos coordinados por una persona.
        recorreLista(getProjectsCoordinatedByPersonId(em, 1L));
        //Crear un método que permita consultar en que proyectos trabaja una persona.
        recorreLista(getProjectsSponsoredByPatrocinadorId(em, 1L));
        //Crear un método que al ingresar una id de patrocinador borre todos los registros de proyectos de dicho patrocinador.
        deleteProjectsByPatrocinadorId(em, 1L);
        //Crear una método que imprima el ID del proyecto y el nombre del coordinador para los proyectos cuyo identificador es impar.
        printProjectIdAndCoordinatorForOddProjects(em);
    }

    public static void createData(EntityManager em) {
        //  createPerson(em);
        // createPatrocintor(em);
        // createProyect(em);
        // createPersonProyect(em);
        //  getPersonsByProjectId(em,1L);

    }

    public static void printProjectIdAndCoordinatorForOddProjects(EntityManager em) {

        System.out.println("método que imprima el ID del proyecto y el nombre del coordinador para los proyectos cuyo identificador es impar.");
        String jpql = "SELECT proj.id, proj.coordinador.nombre FROM Proyecto proj WHERE MOD(proj.id, 2) = 1";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

        List<Object[]> results = query.getResultList();

        for (Object[] result : results) {
            Long projectId = (Long) result[0];
            String coordinatorName = (String) result[1];
            System.out.println("ID del Proyecto: " + projectId + ", Nombre del Coordinador: " + coordinatorName);
        }
    }

    public static void deleteProjectsByPatrocinadorId(EntityManager em, Long patrocinadorId) {

        System.out.println("Crear un método que al ingresar una id de patrocinador borre todos los registros de proyectos de dicho patrocinador.");
        String jpql = "DELETE FROM Proyecto proj WHERE proj.patrocinador.id = :patrocinadorId";
        Query query = em.createQuery(jpql);
        query.setParameter("patrocinadorId", patrocinadorId);

        em.getTransaction().begin();
        int deletedCount = query.executeUpdate();
        em.getTransaction().commit();

        System.out.println("Número de proyectos eliminados: " + deletedCount);
    }

    public List<Proyecto> getProjectsWorkedByPersonId(EntityManager em, Long personaId) {

        String jpql = "SELECT proj FROM Proyecto proj JOIN proj.personas p WHERE p.id = :personaId";
        TypedQuery<Proyecto> query = em.createQuery(jpql, Proyecto.class);
        query.setParameter("personaId", personaId);

        return query.getResultList();
    }

    public static List<Proyecto> getProjectsSponsoredByPatrocinadorId(EntityManager em, Long patrocinadorId) {

        System.out.println("método que permita consultar en que proyectos trabaja una persona.");
        System.out.println("Crear un método que permita consultar en que proyectos trabaja una persona.");
        String jpql = "SELECT proj FROM Proyecto proj WHERE proj.patrocinador.id = :patrocinadorId";
        TypedQuery<Proyecto> query = em.createQuery(jpql, Proyecto.class);
        query.setParameter("patrocinadorId", patrocinadorId);

        return query.getResultList();
    }

    public static List<Proyecto> getProjectsCoordinatedByPersonId(EntityManager em, Long personaId) {

        System.out.println("método que permita consultar los proyectos coordinados por una persona.");

        String jpql = "SELECT proj FROM Proyecto proj WHERE proj.coordinador.id = :personaId";
        TypedQuery<Proyecto> query = em.createQuery(jpql, Proyecto.class);
        query.setParameter("personaId", personaId);

        return query.getResultList();
    }

    public static List<Persona> getPersonsByProjectId(EntityManager em, Long projectId) {
        System.out.println("método que permita consultar cuales son las personas que trabajan en un proyecto determinado.");

        String jpql = "SELECT p FROM Persona p JOIN p.proyectos proj WHERE proj.id = :projectId";
        TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
        query.setParameter("projectId", projectId);

        return query.getResultList();
    }

    public static void recorreLista(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println();
        System.out.println("Resultados " + list.size());
    }

    private static void createPersonProyect(EntityManager em) {
        em.getTransaction().begin();
        for (Long i = 1L; i < 6L; i++) {
            Persona persona = em.getReference(Persona.class, i);
            Proyecto proyecto = em.getReference(Proyecto.class, i);
            persona.setProyectos(new ArrayList<>());
            proyecto.setPersonas(new ArrayList<>());
            persona.getProyectos().add(proyecto);
            proyecto.getPersonas().add(persona);
            em.persist(persona);
            em.persist(proyecto);

        }
        em.getTransaction().commit();
    }

    private static void createPatrocintor(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Creacion de Proyecto");
        for (Long i = 0L; i < 5L; i++) {
            Patrocinador patrocinador = new Patrocinador("Jose Martinez");
            em.persist(patrocinador);
        }
        em.getTransaction().commit();
    }

    private static void createProyect(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Creacion de Proyecto");
        for (Long i = 0L; i < 5L; i++) {
            Persona cordinador = em.find(Persona.class, 1L);
            Patrocinador patrocinador = em.find(Patrocinador.class, 2L);
            Proyecto proyecto = new Proyecto(new Date(), 200.0, cordinador, patrocinador);
            em.persist(proyecto);
        }
        em.getTransaction().commit();
    }

    private static void createPerson(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Creacion de Persona");
        for (Long i = 0L; i < 5L; i++) {
            Persona persona = new Persona("Jonh" + i, "Niño" + i, new Date());
            em.persist(persona);
        }

        em.getTransaction().commit();
    }


}
