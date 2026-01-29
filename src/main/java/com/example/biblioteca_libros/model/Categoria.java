package com.example.biblioteca_libros.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "categoria", schema = "biblioteca_libros")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE_CATEGORIA", length = 100)
    private String nombreCategoria;


}