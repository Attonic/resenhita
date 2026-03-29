package com.resenhita.repository;

import com.resenhita.model.entity.Resenha;
import com.resenhita.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, UUID> {

    @Query("SELECT COUNT(r) FROM Resenha r WHERE r.usuario.id = :usuarioId AND r.dataCriacao >= :inicio AND r.dataCriacao <= :fim")
    long limiteDeResenhaPorUsuario(@Param("usuarioId") UUID idUsuario,
                                   @Param("inicio") LocalDateTime dataInicio,
                                   @Param("fim") LocalDateTime dataFim);
}
