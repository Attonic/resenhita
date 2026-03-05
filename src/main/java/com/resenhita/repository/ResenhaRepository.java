package com.resenhita.repository;

import com.resenhita.model.entity.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, UUID> {
}
