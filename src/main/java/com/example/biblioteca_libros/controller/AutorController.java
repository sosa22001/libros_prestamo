package com.example.biblioteca_libros.controller;

import com.example.biblioteca_libros.model.Autor;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autor")
class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<RespuestaApi<Autor>> crearAutor(@RequestBody Autor autor){

        RespuestaApi<Autor> respuestaApi = new RespuestaApi<>();

        try{
            respuestaApi = autorService.crearAutor(autor);

            if(respuestaApi.getCodigoResultado() == 1){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaApi);
            }

            if(respuestaApi.getCodigoResultado() == 2){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);
            }


            return ResponseEntity.status(HttpStatus.OK).body(respuestaApi);

        }catch(Exception e){
            respuestaApi.setCodigoResultado(2);
            respuestaApi.setMensaje("Error: "+ e.getMessage() + "Fuente: " + e.getLocalizedMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);

        }
    }

    @PatchMapping
    public ResponseEntity<RespuestaApi<Autor>> actualizarAutor(@RequestBody Autor autor, @RequestParam int idAutor){

        RespuestaApi<Autor> respuestaApi = new RespuestaApi<>();

        try{
            respuestaApi = autorService.actualizarAutor(autor, idAutor);

            if(respuestaApi.getCodigoResultado() == 3){
                return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(respuestaApi);
            }

            if(respuestaApi.getCodigoResultado() == 1){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaApi);
            }

            if(respuestaApi.getCodigoResultado() == 2){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);
            }

            return ResponseEntity.status(HttpStatus.OK).body(respuestaApi);

        }catch(Exception e){
            respuestaApi.setCodigoResultado(2);
            respuestaApi.setMensaje("Error: "+ e.getMessage() + "Fuente: " + e.getLocalizedMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);
        }
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<List<Autor>>> obtenerAutores(){
        RespuestaApi<List<Autor>> respuestaApi = new RespuestaApi<>();

        try{
            respuestaApi = autorService.listadoDeAutores();

            if(respuestaApi.getCodigoResultado() == 2){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);
            }

            return ResponseEntity.status(HttpStatus.OK).body(respuestaApi);

        }catch(Exception e){
            respuestaApi.setCodigoResultado(2);
            respuestaApi.setMensaje("Error: "+ e.getMessage() + "Fuente: " + e.getLocalizedMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaApi);

        }
    }

    @DeleteMapping
    public ResponseEntity<RespuestaApi<Autor>> eliminarAutor(@RequestParam int idAutor){

        RespuestaApi<Autor> respuestaApi = new RespuestaApi<>();

        respuestaApi = autorService.eliminarAutor(idAutor);

        if(respuestaApi.getCodigoResultado() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaApi);
        }

        return ResponseEntity.ok(respuestaApi);
    }
}
