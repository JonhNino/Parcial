package com.uptc.frw.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Patrocinador")
public class Patrocinador {

    @Id
    @Column(name = "Id_Patrocinador")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_patrocinador")
    @SequenceGenerator(name = "seq_id_patrocinador", sequenceName = "seq_id_patrocinador", allocationSize = 1)
    private Long idPatrocinador;

    @Column(name = "Nombre_Patrocinador", nullable = false, length = 100)
    private String nombrePatrocinador;

    @OneToMany(mappedBy = "patrocinador", cascade = CascadeType.ALL)
    private List<Proyecto> proyectos;

    public Patrocinador() {
    }

    public Patrocinador(String nombrePatrocinador) {
        this.nombrePatrocinador = nombrePatrocinador;
    }

    public Long getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(Long idPatrocinador) {
        this.idPatrocinador = idPatrocinador;
    }

    public String getNombrePatrocinador() {
        return nombrePatrocinador;
    }

    public void setNombrePatrocinador(String nombrePatrocinador) {
        this.nombrePatrocinador = nombrePatrocinador;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Patrocinador{" +
                "idPatrocinador=" + idPatrocinador +
                ", nombrePatrocinador='" + nombrePatrocinador + '\'' +
                '}';
    }
}
