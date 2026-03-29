package com.resenhita.model.entity;

import com.resenhita.enums.CategoriaResenha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resenhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resenha {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo_resenha", length = 150, nullable = false)
    private String tituloResenha;

    @Column(name = "nome_obra",  length = 150, nullable = false)
    private String nomeObra;

    @Column(name = "conteudo", length = 500, nullable = false)
    private String conteudo;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "categoria", nullable = false)
    private CategoriaResenha categoria;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
