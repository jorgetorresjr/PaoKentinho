package org.padaria.paokentinho.controllers;

import org.padaria.paokentinho.dtos.FornadaDTO;
import org.padaria.paokentinho.models.entities.Fornada;
import org.padaria.paokentinho.models.repositories.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    FacadeRepository facade;


    @GetMapping("/status-fornadas")
    public ResponseEntity<List<FornadaDTO>> getStatusFornadas() {
        try {
            List<Fornada> fornadas = facade.readAllFornada();
            List<FornadaDTO> fornadaDTOs = new ArrayList<>();

            for (Fornada f : fornadas) {
                LocalTime agora = LocalTime.now();
                LocalTime saida = f.getHorarioSaida();

                boolean pronto;
                long minRestantes;

                if(agora.isBefore(saida)) {
                    pronto = false;
                    minRestantes = saida.getHour() * 60 + saida.getMinute() - (agora.getHour() * 60 + agora.getMinute());
                } else {
                    pronto = true;
                    minRestantes = 0;
                }

                fornadaDTOs.add(new FornadaDTO(f, pronto, minRestantes));
            }
            return ResponseEntity.ok(fornadaDTOs);

        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
