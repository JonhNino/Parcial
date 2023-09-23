package com.uptc.frw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Date;

@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @Column(name = "Id_Persona")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_persona")
    @SequenceGenerator(name = "seq_id_persona", sequenceName = "seq_id_persona", allocationSize = 1)
    private Long idPersona;

    @Column(name = "Nombre_Persona", nullable = false, length = 50)
    private String nombrePersona;

    @Column(name = "Apellido_Persona", nullable = false, length = 50)
    private String apellidoPersona;

    @Column(name = "Fecha_Nacimiento", nullable = false)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "coordinador", cascade = CascadeType.ALL)
    private List<Proyecto> proyectosCoordinados;

    @ManyToMany(mappedBy = "personas",cascade = CascadeType.ALL)
    private List<Proyecto> proyectos;

    public Persona() {
    }

    public Persona(String nombrePersona, String apellidoPersona, Date fechaNacimiento) {
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Proyecto> getProyectosCoordinados() {
        return proyectosCoordinados;
    }

    public void setProyectosCoordinados(List<Proyecto> proyectosCoordinados) {
        this.proyectosCoordinados = proyectosCoordinados;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", apellidoPersona='" + apellidoPersona + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
