package com.cursojava.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "personajes",uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Personaje {

    @Id
    @Column(name = "id_personaje")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_personaje;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private int edad;
    @Column(name = "peso")
    private int peso;
    @Column(name = "historia")
    private String historia;
    @Column(name = "img")
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula",nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pelicula pelicula;

    public Personaje() {

    }

    public Personaje(long id_personaje, String nombre, int edad, int peso, String historia, String imagen, Pelicula pelicula) {
        this.id_personaje = id_personaje;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.imagen = imagen;
        this.pelicula = pelicula;
    }

    public Personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    public long getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(long id_personaje) {
        this.id_personaje = id_personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
