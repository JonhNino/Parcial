package com.uptc.frw.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @Column(name = "Id_Proyecto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_proyecto")
    @SequenceGenerator(name = "seq_id_proyecto", sequenceName = "seq_id_proyecto", allocationSize = 1)
    private Long idProyecto;

    @Column(name = "Fecha_Inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "Fecha_Fin")
    private Date fechaFin;

    @Column(name = "Presupuesto", nullable = false, precision = 7, scale = 2)
    private Double presupuesto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Coordinador_Id", nullable = false)
    private Persona coordinador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Patrocinador_Id", nullable = false)
    private Patrocinador patrocinador;

    @ManyToMany
    @JoinTable(
            name = "personas_proyectos",
            joinColumns = @JoinColumn(name = "Id_Proyecto"),
            inverseJoinColumns = @JoinColumn(name = "Id_Persona")
    )
    private List<Persona> personas;

    public Proyecto() {

    }

    public Proyecto(Date fechaInicio, Double presupuesto, Persona coordinador, Patrocinador patrocinador) {
        this.fechaInicio = fechaInicio;
        this.presupuesto = presupuesto;
        this.coordinador = coordinador;
        this.patrocinador = patrocinador;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Persona getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Persona coordinador) {
        this.coordinador = coordinador;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto=" + idProyecto +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", presupuesto=" + presupuesto +
                '}';
    }
}
