package com.cursojava.demo.services;

import com.cursojava.demo.dto.PeliculaDTO;
import com.cursojava.demo.exceptions.ResourceNotFoundException;
import com.cursojava.demo.models.Pelicula;
import com.cursojava.demo.models.Personaje;
import com.cursojava.demo.repositories.PeliculaRepository;
import com.cursojava.demo.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServicioImp implements PeliculaServicio {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeRepository personajeRepository;

    public PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO){
        Pelicula pelicula = mapearEntidad(peliculaDTO);
        Pelicula nuevaPelicula = peliculaRepository.save(pelicula);
        PeliculaDTO peliculaRespuesta = mapearDTO(nuevaPelicula);
        return peliculaRespuesta;

    }

    @Override
    public List<PeliculaDTO> obtenerPeliculas() {

             List <Pelicula> peliculas = peliculaRepository.findAll();

        return peliculas.stream().map(pelicula -> mapearDTO(pelicula)).collect(Collectors.toList());
    }

    public String guardarPersonaje(long idpelicula,long idpersonaje){
        Pelicula pelicula = peliculaRepository.findById(idpelicula).orElseThrow(() -> new ResourceNotFoundException("Pelicula","id",idpelicula));
        Personaje  personaje= personajeRepository.findById(idpersonaje).orElseThrow(() -> new ResourceNotFoundException("Personaje","idpersonaje",idpersonaje));
        int res = pelicula.pertenecePersonaje(idpersonaje);

        if (res == -1) {
            pelicula.añadirPersonaje(personaje);
            personaje.setPelicula(pelicula);
            Pelicula nuevaPelicula = peliculaRepository.save(pelicula);
            Personaje personajeNuevo = personajeRepository.save(personaje);
            return "Personaje con id: "+idpersonaje+" añadido a pelicula con id: "+idpelicula+ "con exito";
        }
        return "Error, Personaje repetido";

    }

    @Override
    public String removerPersonaje(long idpelicula, long idpersonaje) {
        Pelicula pelicula = peliculaRepository.findById(idpelicula).orElseThrow(() -> new ResourceNotFoundException("Pelicula","id",idpelicula));
        Personaje  personaje= personajeRepository.findById(idpersonaje).orElseThrow(() -> new ResourceNotFoundException("Personaje","idpersonaje",idpersonaje));
        int res = pelicula.pertenecePersonaje(idpersonaje);

        if (res != -1){
            pelicula.removerPersonaje(res);
            personajeRepository.delete(personaje);
            Pelicula nuevaPelicula = peliculaRepository.save(pelicula);
            return "Personaje con id: "+idpersonaje+" removido de pelicula con id: "+idpelicula+" con exito";
        }
        return "Error, Personaje no encontrado";

    }
    @Override
    public PeliculaDTO obtenerPeliculaPorId(long id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id));
        return mapearDTO(pelicula);
    }

    @Override
    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO, long id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id));
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setImagen(peliculaDTO.getImg());
        pelicula.setGenero(peliculaDTO.getGenero());
        pelicula.setCalificacion(peliculaDTO.getCalificacion());
        pelicula.setFechaCreacion((Date) peliculaDTO.getFechaCreacion());
        Pelicula peliculaActualizada = peliculaRepository.save(pelicula);
        return mapearDTO(peliculaActualizada);

    }

    public void eliminarPelicula(long id){
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id));
        peliculaRepository.delete(pelicula);

    }

    @Override
    public List<PeliculaDTO> obtenerPeliculasPorGenero(String genero) {
        List<Pelicula> peliculas = peliculaRepository.getPeliculaByGenero(genero);
        return peliculas.stream().map(pelicula -> mapearDTO(pelicula)).collect(Collectors.toList());
    }

    public List<PeliculaDTO> obtenerPeliculasPorNombre(String nombre) {
        List<Pelicula> peliculas = peliculaRepository.getPeliculaByTitle(nombre);
        return peliculas.stream().map(pelicula -> mapearDTO(pelicula)).collect(Collectors.toList());
    }

    private PeliculaDTO mapearDTO (Pelicula pelicula){
        //Convierte entidad a DTO
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(pelicula.getIdPelicula());
        peliculaDTO.setCalificacion(pelicula.getCalificacion());
        peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
        peliculaDTO.setGenero(pelicula.getGenero());
        peliculaDTO.setTitulo(pelicula.getTitulo());
        peliculaDTO.setImg(pelicula.getImagen());
        peliculaDTO.setPersonajes(pelicula.getPersonajes());
        return peliculaDTO;
    }

    private Pelicula mapearEntidad(PeliculaDTO peliculaDTO) {
        //Convertimos de DTO a entidad
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setCalificacion(peliculaDTO.getCalificacion());
        pelicula.setGenero(peliculaDTO.getGenero());
        pelicula.setFechaCreacion((Date) peliculaDTO.getFechaCreacion());
        pelicula.setPersonajes(peliculaDTO.getPersonajes());
        pelicula.setImagen(peliculaDTO.getImg());
        return pelicula;
    }



}
