package com.uptc.frw.executor;

import com.uptc.frw.conf.PersistenceUtil;
import com.uptc.frw.entity.Patrocinador;
import com.uptc.frw.entity.Persona;
import com.uptc.frw.entity.Proyecto;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Date;

public class Principal {

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.getEntityManager();
        System.out.println("Con Conexion");
        //Crear un método que inserte en la base de datos con por lo menos 5 registros en cada tabla.
        createData(em);
    }
    public static void createData(EntityManager em){
        createPerson(em);
        createPatrocintor(em);
        createProyect(em);
        createPersonProyect(em);
    }

    private static void createPersonProyect(EntityManager em) {
        em.getTransaction().begin();
        for (Long i = 1L; i < 6L; i++) {
            Persona persona = em.getReference(Persona.class,i);
            Proyecto proyecto = em.getReference(Proyecto.class,i);
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
        for (Long i = 0L; i <5L ; i++) {
            Patrocinador patrocinador = new Patrocinador("Jose Martinez");
            em.persist(patrocinador);
        }
        em.getTransaction().commit();
    }

    private static void createProyect(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Creacion de Proyecto");
        for (Long i = 0L; i <5L ; i++) {
            Persona cordinador = em.find(Persona.class,1L);
            Patrocinador patrocinador = em.find(Patrocinador.class,2L);
            Proyecto proyecto = new Proyecto(new Date(),200.0,cordinador,patrocinador);
            em.persist(proyecto);
        }
        em.getTransaction().commit();
    }

    private static void createPerson(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Creacion de Persona");
        for (Long i = 0L; i <5L ; i++) {
            Persona persona = new Persona("Jonh"+i,"Niño"+i,new Date());
            em.persist(persona);
        }

        em.getTransaction().commit();
    }


}
