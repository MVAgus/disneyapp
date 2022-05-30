package com.cursojava.demo.repositories;


import com.cursojava.demo.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    @Query("SELECT p from Pelicula p WHERE p.titulo = ?1")
    public List<Pelicula> getPeliculaByTitle(String titulo);


    @Query("SELECT p from Pelicula p WHERE p.genero = ?1")
    public List <Pelicula> getPeliculaByGenero(String title);

    @Query("SELECT p from Pelicula p WHERE p.titulo = ?1  AND p.genero = ?2")
    public List <Pelicula> findByNameAndGenre(String title,String genero);






}
