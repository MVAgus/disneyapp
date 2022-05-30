package com.cursojava.demo.services;

import com.cursojava.demo.dto.PeliculaDTO;
import com.cursojava.demo.models.Pelicula;

import java.util.List;

public interface PeliculaServicio {
    public PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO);

    public List<PeliculaDTO> obtenerPeliculas();

    public PeliculaDTO obtenerPeliculaPorId(long id);


    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO,long id);

    public void eliminarPelicula(long id);

    public String guardarPersonaje(long idpelicula,long idpersonaje);

    public String removerPersonaje(long idpelicula,long idpersonaje);

    public List<PeliculaDTO> obtenerPeliculasPorGenero(String genero);
    public List<PeliculaDTO> obtenerPeliculasPorNombre(String genero);


}
