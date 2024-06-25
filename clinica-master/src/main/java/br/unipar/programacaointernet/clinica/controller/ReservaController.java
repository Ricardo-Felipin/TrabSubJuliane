package br.unipar.programacaointernet.clinica.controller;


import br.unipar.programacaointernet.clinica.model.Reserva;
import br.unipar.programacaointernet.clinica.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva savedReserva = reservaService.save(reserva);
        return ResponseEntity.ok(savedReserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hospede/{hospedeId}")
    public ResponseEntity<List<Reserva>> getReservasByHospedeId(@PathVariable Long hospedeId) {
        List<Reserva> reservas = reservaService.findByHospedeId(hospedeId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/dataEntrada")
    public ResponseEntity<List<Reserva>> getReservasByDataEntrada(@RequestParam String dataEntrada) {
        List<Reserva> reservas = reservaService.findByDataEntrada(dataEntrada);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/checkin/{id}")
    public ResponseEntity<Void> checkin(@PathVariable Long id) {
        reservaService.checkin(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/checkout/{id}")
    public ResponseEntity<Void> checkout(@PathVariable Long id) {
        reservaService.checkout(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}