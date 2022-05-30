package com.cursojava.demo.models;

import javax.persistence.*;
import java.util.*;
import java.sql.Date;

@Entity
@Table(name = "peliculas", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Pelicula {
    @Id
    @Column(name = "id_pelicula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPelicula;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "calificacion")
    private int calificacion;
    @Column(name = "genero")
    private String genero;
    @Column(name = "img")
    private String imagen;
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    //el orphanRemoval = true es para eliminar los personajes asociados de una pelicula en la bd cuando esta se elimina
    private List<Personaje> personajes = new ArrayList<Personaje>();

    public Pelicula() {
    }

    public Pelicula(long idPelicula, String titulo, Date fechaCreacion, int calificacion, String genero, String imagen, List<Personaje> personajes) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
        this.imagen = imagen;
        this.personajes = personajes;
    }

    public long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
        for (Personaje personajeModel : personajes) {
            personajeModel.setPelicula(this);
        }
    }

    public int pertenecePersonaje(long  idpersonaje) {
        int i = 0;
        int respuesta = -1;
        int tamList = this.personajes.size();
        while (i < tamList && respuesta == -1){
            Personaje p = this.personajes.get(i);
            if (p.getId_personaje() == idpersonaje){
                respuesta = i;
            }
            i++;
        }
        return respuesta;

    }

    public void añadirPersonaje(Personaje personaje)
    {
        personaje.setPelicula(this);
        this.personajes.add(personaje);

    }

    public void  removerPersonaje(int idpersonaje){
        this.personajes.remove(idpersonaje);

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
