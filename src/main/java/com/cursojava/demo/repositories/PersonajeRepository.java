package com.cursojava.demo.repositories;

import com.cursojava.demo.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje,Long> {

    @Query("SELECT p from Personaje p WHERE p.nombre = ?1")
    public Personaje findByName(String name);

    @Query("SELECT p from Personaje p WHERE p.edad = ?1")
    public List<Personaje> findByAge(int age);

    /*@Query("SELECT p from PersonajeModel p WHERE p.idPelicula =?1")
    public List<PersonajeModel> findByMovie(long idPelicula);*/

}
