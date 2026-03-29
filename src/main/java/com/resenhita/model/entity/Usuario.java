package com.resenhita.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "biografia", length = 500)
    private String biografia;

    @Column(name = "link_social_1", length = 255)
    private String linkSocial1;

    @Column(name = "link_social_2", length = 255)
    private String linkSocial2;

    @Column(name = "link_social_3", length = 255)
    private String linkSocial3;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "senha", length = 255)
    private String senha;

    @Column(name = "data_cadastro", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;
}
