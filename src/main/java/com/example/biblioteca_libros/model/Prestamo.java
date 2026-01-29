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
@Table(name = "prestamos", schema = "biblioteca_libros")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESTAMO", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTOR")
    private Autor idAutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LIBRO")
    private Libro idLibro;

    @Column(name = "FECHA_PRESTAMO")
    private LocalDate fechaPrestamo;

    @Column(name = "FECHA_DEVOLUCION")
    private LocalDate fechaDevolucion;


}