package com.cursojava.demo.controllers;

import com.cursojava.demo.dto.PersonajeDTO;

import com.cursojava.demo.services.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeServicio personajeServicio;

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardarPersonaje(@RequestBody PersonajeDTO personajeDTO ){
        return new ResponseEntity<>(personajeServicio.crearPersonaje(personajeDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PersonajeDTO> obtenerPersonajes(){
        return personajeServicio.obtenerPersonajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> obtenerPersonajePorId(@PathVariable(name="id") long id){
        return ResponseEntity.ok(personajeServicio.obtenerPersonajePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPersonaje(@RequestBody PersonajeDTO personajeDTO,@PathVariable(name="id") long id ){
        PersonajeDTO personajeRespuesta = personajeServicio.actualizarPersonaje(personajeDTO,id);
        return new ResponseEntity<>(personajeRespuesta,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersonaje(@PathVariable(name="id") long id){
        personajeServicio.eliminarPersonaje(id);
        return new ResponseEntity<>("Personaje eliminado con exito",HttpStatus.OK);

    }
    @GetMapping(params = "nombre")
    public PersonajeDTO obtenerPersonajePorNombre(@RequestParam String nombre) {
        return personajeServicio.obtenerPersonajePorNombre(nombre);
    }



}
