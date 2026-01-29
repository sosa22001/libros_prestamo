package com.example.biblioteca_libros.service;

import com.example.biblioteca_libros.model.Autor;
import com.example.biblioteca_libros.model.Categoria;
import com.example.biblioteca_libros.model.Libro;
import com.example.biblioteca_libros.model.dto.RespuestaApi;
import com.example.biblioteca_libros.repository.AutorRepository;
import com.example.biblioteca_libros.repository.CategoriaRepository;
import com.example.biblioteca_libros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public RespuestaApi<Libro> crearLibro(Libro libro, int idAutor, int idCategoria) {
        RespuestaApi respuesta = new RespuestaApi();
        try{

            Optional<Autor> autor = autorRepository.findById(idAutor);

            if(autor.isEmpty()){
                respuesta.setCodigoResultado(1);
                respuesta.setMensaje("No se encontró el autor, no se puede crear libro");
                return respuesta;
            }

            Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

            if(categoria.isEmpty()){
                respuesta.setCodigoResultado(1);
                respuesta.setMensaje("No se encontró la categoria, no se puede crear libro");
                return respuesta;
            }

            Libro libroACrear = new Libro();

            libroACrear.setNombre(libro.getNombre());
            libroACrear.setDescripcion(libro.getDescripcion());
            libroACrear.setIsbn(libro.getIsbn());
            libroACrear.setFechaCreacion(libro.getFechaCreacion());
            libroACrear.setIdAutor(autor.get());
            libroACrear.setIdCategoria(categoria.get());

            respuesta.setResultado(libroRepository.save(libroACrear));
            respuesta.setMensaje("Libro creado");
            respuesta.setCodigoResultado(0);

            return respuesta;
        }catch(Exception e){
            respuesta.setMensaje("Error al crear el libro" + e.getMessage());
            respuesta.setCodigoResultado(2);

            return respuesta;
        }
    }

    public RespuestaApi<Libro> eliminarLibro(int idLibro){
        RespuestaApi respuesta = new RespuestaApi();

        try{
            Optional<Libro> libroAEliminar = libroRepository.findById(idLibro);

            if(!libroAEliminar.isPresent()){
                respuesta.setMensaje("Libro no encontrado, no se puede eliminar");
                respuesta.setCodigoResultado(1);
            }

            libroRepository.deleteById(idLibro);

            respuesta.setCodigoResultado(0);
            respuesta.setMensaje("Libro eliminado");
            return respuesta;
        }catch(Exception e){
            respuesta.setMensaje("Error al eliminar el libro" + e.getMessage());
            respuesta.setCodigoResultado(2);

            return respuesta;
        }

    }

    public RespuestaApi<List<Libro>> buscarLibro(String nombre, Integer idAutor, int categoria){
        RespuestaApi<List<Libro>> respuesta = new RespuestaApi();

        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosADevolver = new ArrayList<>();

        if(nombre.isEmpty() || categoria == 0){
            for(Libro libro : libros){
                if(libro.getIdAutor() != null && libro.getIdAutor().getId() == idAutor ){
                    librosADevolver.add(libro);
                }
            }
        }

        respuesta.setResultado(librosADevolver);
        respuesta.setMensaje("Libros");

        return respuesta;
    }
}
