package org.padaria.paokentinho.controllers;

import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("/paes")
@CrossOrigin("*")
public class PaoController {
    @Autowired
    private FacadeRepository facade;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pao pao, HttpServletResponse response){
        try {
            this.facade.create(pao);

            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pao> read(@PathVariable int id){
        try {
            Pao pao = facade.readPao(id);

            if (pao == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pao);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pao>> readAll() throws SQLException {
        return ResponseEntity.ok(this.facade.readAllPao());
    }
}
