package com.example.biblioteca_libros.model.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaApi<T> {

    private int codigoResultado;

    private String mensaje;

    private T resultado;

}
