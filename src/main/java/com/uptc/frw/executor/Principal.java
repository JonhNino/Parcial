package com.uptc.frw.executor;

import com.uptc.frw.conf.PersistenceUtil;
import jakarta.persistence.EntityManager;

public class Principal {

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.getEntityManager();
        System.out.println("Con Conexion");
        //Crear un método que inserte en la base de datos con por lo menos 5 registros en cada tabla.

    }


}
