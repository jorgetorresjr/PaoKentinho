package org.padaria.paokentinho.controllers;

import org.padaria.paokentinho.models.entities.Fornada;
import org.padaria.paokentinho.models.entities.Pao;
import org.padaria.paokentinho.models.repositories.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/fornadas")
@CrossOrigin("*")
public class FornadaController {

    @Autowired
    private FacadeRepository facade;

    @PostMapping("/{paoId}")
    public ResponseEntity<Fornada> create(@PathVariable int paoId) {
        try {
            Pao pao = facade.readPao(paoId);
            if (pao == null) {
                return ResponseEntity.notFound().build();
            }

            Fornada fornada = new Fornada(pao);

            this.facade.create(fornada);

            return ResponseEntity.ok(fornada);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Fornada>> readAll() throws SQLException {
        return ResponseEntity.ok(this.facade.readAllFornada());
    }
}
