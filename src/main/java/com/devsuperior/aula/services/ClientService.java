package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.ClientDTO;
import com.devsuperior.aula.entities.Client;
import com.devsuperior.aula.repositories.ClientRepository;
import com.devsuperior.aula.exceptions.ResourceNotFoundException;
import com.devsuperior.aula.exceptions.DatabaseException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: id " + id));
        return toDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: id " + id));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cliente não encontrado: id " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    @Transactional
    public Page<ClientDTO> findAllPaged(Pageable pageable) {
        Page<Client> page = repository.findAll(pageable);
        return page.map(this::toDTO);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    private ClientDTO toDTO(Client entity) {
        return new ClientDTO(entity.getId(), entity.getName(), entity.getCpf(), entity.getIncome(), entity.getBirthDate(), entity.getChildren());
    }
}
