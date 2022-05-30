package com.cursojava.demo.services;

import com.cursojava.demo.dto.PeliculaDTO;
import com.cursojava.demo.dto.PersonajeDTO;
import com.cursojava.demo.models.Personaje;

import java.util.List;

public interface PersonajeServicio {
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO);

    public PersonajeDTO añadirPersonajeaPelicula(long idPelicula,long idPersonaje);

    public List<PersonajeDTO> obtenerPersonajes();

    public PersonajeDTO obtenerPersonajePorId(long id);

    public PersonajeDTO obtenerPersonajePorNombre(String nombre);


    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO,long id);

    public void eliminarPersonaje(long id);


}
