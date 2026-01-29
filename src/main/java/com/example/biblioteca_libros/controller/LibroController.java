package com.example.biblioteca_libros.controller;

import com.example.biblioteca_libros.model.Libro;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.repository.LibroRepository;
import com.example.biblioteca_libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<RespuestaApi<Libro>> crearLibro(@RequestBody Libro libro, @RequestParam int idAutor, @RequestParam int idCategoria){

        RespuestaApi<Libro> respuesta = new RespuestaApi<>();

        respuesta = libroService.crearLibro(libro, idAutor, idCategoria);

        if(respuesta.getCodigoResultado() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if(respuesta.getCodigoResultado() == 2){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @DeleteMapping
    public ResponseEntity<RespuestaApi<Libro>> eliminarLibro(@RequestParam int idLibro){

        RespuestaApi respuesta = new RespuestaApi();

        respuesta = libroService.eliminarLibro(idLibro);

        if(respuesta.getCodigoResultado() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if(respuesta.getCodigoResultado() == 2){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/buscar")
    public ResponseEntity<RespuestaApi<List<Libro>>> librosPorAutor(@RequestParam Integer idAutor, @RequestParam int idCategoria, @RequestParam String nombre){
        RespuestaApi<List<Libro>>  respuesta = new RespuestaApi<>();

        respuesta = libroService.buscarLibro(nombre, idAutor, idCategoria);

        return ResponseEntity.ok(respuesta);
    }
}
