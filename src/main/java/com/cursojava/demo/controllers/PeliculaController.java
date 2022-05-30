package com.cursojava.demo.controllers;

import com.cursojava.demo.dto.PeliculaDTO;
import com.cursojava.demo.services.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @PostMapping
    public ResponseEntity<PeliculaDTO> guardarPelicula(@RequestBody PeliculaDTO pelicula) {
        return new ResponseEntity<>(peliculaServicio.crearPelicula(pelicula), HttpStatus.CREATED);
    }

    @PostMapping("/{idpelicula}/personajes/{idpersonaje}")
    public ResponseEntity<String> guardarPersonaje(@PathVariable(value = "idpelicula") long idpelicula,@PathVariable(value = "idpersonaje") long idpersonaje){
        return new ResponseEntity<>(peliculaServicio.guardarPersonaje(idpelicula,idpersonaje),HttpStatus.OK);

    }
    @DeleteMapping("/{idpelicula}/personajes/{idpersonaje}")
    public ResponseEntity<String> removerPersonaje(@PathVariable(value = "idpelicula") long idpelicula,@PathVariable(value = "idpersonaje") long idpersonaje){
        return new ResponseEntity<>(peliculaServicio.removerPersonaje(idpelicula,idpersonaje),HttpStatus.OK);

    }

    @GetMapping
    public List<PeliculaDTO> obtenerPeliculas(){
        return peliculaServicio.obtenerPeliculas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaPorId(@PathVariable(name="id") long id){
        return ResponseEntity.ok(peliculaServicio.obtenerPeliculaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPelicula(@RequestBody PeliculaDTO peliculaDTO,@PathVariable(name="id") long id ){
        PeliculaDTO peliculaRespuesta = peliculaServicio.actualizarPelicula(peliculaDTO,id);
        return new ResponseEntity<>(peliculaRespuesta,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable(name="id") long id){
        peliculaServicio.eliminarPelicula(id);
        return new ResponseEntity<>("Pelicula eliminada con exito",HttpStatus.OK);

    }

    @GetMapping(params = "genero")
    public List<PeliculaDTO> obtenerPeliculaPorGenero(@RequestParam String genero) {
        return this.peliculaServicio.obtenerPeliculasPorGenero(genero);
    }

    @GetMapping(params = "nombre")
    public List<PeliculaDTO> obtenerPeliculaPorNombre(@RequestParam String nombre) {
        return this.peliculaServicio.obtenerPeliculasPorNombre(nombre);
    }


     }





