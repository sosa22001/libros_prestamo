package com.example.biblioteca_libros.service;

import com.example.biblioteca_libros.model.Autor;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private String respuestaError = "Ocurrió un error interno";

    @Autowired
    private AutorRepository autorRepository;

    public RespuestaApi<Autor> crearAutor(Autor autor) {

        RespuestaApi<Autor> respuesta = new RespuestaApi<>();

        try{

            if(autor.getNombre() == null){
                respuesta.setResultado(null);
                respuesta.setCodigoResultado(1);
                respuesta.setMensaje("El nombre es requerido");
                return respuesta;
            }

            if(autor.getApellido() == null){
                respuesta.setResultado(null);
                respuesta.setCodigoResultado(1);
                respuesta.setMensaje("El apellido es requerido");
                return respuesta;
            }

            respuesta.setResultado( autorRepository.save(autor) );
            respuesta.setMensaje("Autor creado");
            respuesta.setCodigoResultado(0);

            return respuesta;
        }catch(Exception e){
            respuesta.setResultado(null);
            respuesta.setCodigoResultado(2);
            respuesta.setMensaje("Error interno: " + e.getMessage() + "Fuente: " + e.getLocalizedMessage());

            return respuesta;
        }
    }

    public RespuestaApi<Autor> actualizarAutor(Autor autor, int idAutor) {

        RespuestaApi<Autor> respuesta = new RespuestaApi<>();

        try{

            if(autor.getNombre() == null && autor.getApellido() == null){
                respuesta.setCodigoResultado(1);
                respuesta.setMensaje("No se actualizó ningun autor");

                return respuesta;
            }

            //Buscar usuario:
            Optional<Autor> autorEncontrado = autorRepository.findById(idAutor);

            if(!autorEncontrado.isPresent()){
                respuesta.setMensaje("El autor no existe");
                respuesta.setCodigoResultado(3);

                return respuesta;
            }

            Autor autorACrear = new Autor();

            if(!autor.getNombre().isEmpty()){
                autorACrear.setNombre(autor.getNombre());
            }

            if(!autor.getApellido().isEmpty()){
                autorACrear.setApellido(autor.getApellido());
            }

            autorRepository.save(autorACrear);

            respuesta.setCodigoResultado(0);
            respuesta.setMensaje("Autor actualizado");
            return respuesta;

        }catch(Exception e){
            respuesta.setCodigoResultado(2);
            respuesta.setMensaje("Error interno: " + e.getMessage() + "Fuente: " + e.getLocalizedMessage());
            return respuesta;
        }
    }

    public RespuestaApi<List<Autor>> listadoDeAutores(){

        RespuestaApi<List<Autor>> respuesta = new RespuestaApi<>();

        try{

            respuesta.setMensaje("Listado de Autores");
            respuesta.setCodigoResultado(0);
            respuesta.setResultado(autorRepository.findAll());

            return respuesta;

        }catch (Exception e){
            respuesta.setMensaje(this.respuestaError + e.getMessage() + "Fuente: "+ e.getLocalizedMessage());

            respuesta.setCodigoResultado(2);

            return respuesta;
        }
    }

    public RespuestaApi<Autor> eliminarAutor(int idAutor){

        RespuestaApi respuesta = new RespuestaApi<>();

        //Buscar usuario por id:
        Optional<Autor> autorEncontrado = autorRepository.findById(idAutor);

        if(!autorEncontrado.isPresent()){
            respuesta.setCodigoResultado(1);
            respuesta.setMensaje("El autor no existe");

            return respuesta;
        }

        autorRepository.deleteById(idAutor);

        respuesta.setCodigoResultado(0);
        respuesta.setMensaje("Autor eliminado");

        return respuesta;
    }
}
