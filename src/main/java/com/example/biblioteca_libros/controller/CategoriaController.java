package com.example.biblioteca_libros.controller;

import com.example.biblioteca_libros.model.Categoria;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<RespuestaApi<Categoria>> crearCategoria(@RequestBody Categoria categoria){


        RespuestaApi<Categoria> respuesta = new RespuestaApi<>();

        respuesta = categoriaService.crearCategoria(categoria);

        if(respuesta.getCodigoResultado() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return ResponseEntity.ok(respuesta);
    }

}
