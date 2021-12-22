package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@ToString
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor(staticName = "of")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column( name = "nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="email")
    @EqualsAndHashCode.Include
    private String email;
    @Column(name="telefono")
    private String teléfono;
    @Column(name="contraseña")
    private String password;

}
