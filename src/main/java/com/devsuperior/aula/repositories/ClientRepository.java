package com.devsuperior.aula.repositories;

import com.devsuperior.aula.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
