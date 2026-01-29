package com.example.biblioteca_libros.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "libro", schema = "biblioteca_libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRO", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ISBN")
    private Integer isbn;

    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTOR")
    private Autor idAutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA")
    private Categoria idCategoria;


}