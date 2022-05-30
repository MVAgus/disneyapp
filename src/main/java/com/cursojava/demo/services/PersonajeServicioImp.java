package com.cursojava.demo.services;

import com.cursojava.demo.dto.PersonajeDTO;
import com.cursojava.demo.exceptions.ResourceNotFoundException;
import com.cursojava.demo.models.Pelicula;
import com.cursojava.demo.models.Personaje;
import com.cursojava.demo.repositories.PeliculaRepository;
import com.cursojava.demo.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeServicioImp  implements PersonajeServicio{

    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO) {
        Personaje personaje = mapearEntidad(personajeDTO);
        personajeRepository.save(personaje);
        return mapearDTO(personaje);
    }

    public PersonajeDTO añadirPersonajeaPelicula(long idpelicula,long idpersonaje){
        Pelicula pelicula = peliculaRepository.findById(idpelicula).orElseThrow(() -> new ResourceNotFoundException("Pelicula","id",idpelicula));
        Personaje personaje = personajeRepository.findById(idpersonaje).orElseThrow(() -> new ResourceNotFoundException("Personaje","id",idpersonaje));

        personaje.setPelicula(pelicula);
        Personaje nuevoPersonaje = personajeRepository.save(personaje);
        return mapearDTO(nuevoPersonaje);

    }
    @Override
    public List<PersonajeDTO> obtenerPersonajes() {
        List<Personaje> personajes = personajeRepository.findAll();
        return personajes.stream().map(personaje -> mapearDTO(personaje)).collect(Collectors.toList());
    }

    @Override
    public PersonajeDTO obtenerPersonajePorId(long id) {
        Personaje personaje = personajeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));
        return mapearDTO(personaje);
    }

    @Override
    public PersonajeDTO obtenerPersonajePorNombre(String nombre) {
        Personaje personaje = personajeRepository.findByName(nombre);
        return mapearDTO(personaje);
    }

    @Override
    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id) {
        Personaje personaje = personajeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setImagen(personajeDTO.getImg());
        personaje.setPeso(personajeDTO.getPeso());
        Personaje personajeActualizado = personajeRepository.save(personaje);
        return mapearDTO(personajeActualizado);
    }

    @Override
    public void eliminarPersonaje(long id) {
        Personaje personaje = personajeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));
        personajeRepository.delete(personaje);
    }

    private PersonajeDTO mapearDTO(Personaje personaje){
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personaje.getId_personaje());
        personajeDTO.setEdad(personaje.getEdad());
        personajeDTO.setHistoria(personaje.getHistoria());
        personajeDTO.setPeso(personaje.getPeso());
        personajeDTO.setImg(personaje.getImagen());
        personajeDTO.setNombre(personaje.getNombre());

        return personajeDTO;
    }

    private Personaje mapearEntidad(PersonajeDTO personajeDTO){
        Personaje personaje = new Personaje();
        personaje.setId_personaje(personajeDTO.getId());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setImagen(personajeDTO.getImg());
        personaje.setEdad(personajeDTO.getEdad());

        return personaje;

    }
}
