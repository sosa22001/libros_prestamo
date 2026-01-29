package com.example.biblioteca_libros.service;

import com.example.biblioteca_libros.model.Categoria;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public RespuestaApi<Categoria> crearCategoria(Categoria categoria){

        RespuestaApi<Categoria> respuesta = new RespuestaApi<>();

        try{
            respuesta.setResultado( categoriaRepository.save(categoria) );

            respuesta.setMensaje("Categoria creada");
            respuesta.setCodigoResultado(0);

            return respuesta;
        }catch (Exception e){
            respuesta.setCodigoResultado(1);

            respuesta.setMensaje("Error al crear la categoria" +  e.getMessage());

            return respuesta;
        }
    }
}
