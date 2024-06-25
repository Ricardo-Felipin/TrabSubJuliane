package br.unipar.programacaointernet.clinica.controller;


import br.unipar.programacaointernet.clinica.model.Hospedes;
import br.unipar.programacaointernet.clinica.service.HospedesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {

    @Autowired
    private HospedesService hospedeService;

    @PostMapping
    public ResponseEntity<Hospedes> createHospede(@RequestBody Hospedes hospede) {
        Hospedes savedHospede = hospedeService.save(hospede);
        return ResponseEntity.ok(savedHospede);
    }

    @GetMapping
    public ResponseEntity<List<Hospedes>> getAllHospedes() {
        List<Hospedes> hospedes = hospedeService.findAll();
        return ResponseEntity.ok(hospedes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospedes> getHospedeById(@PathVariable Long id) {
        return hospedeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospedes> updateHospede(@PathVariable Long id, @RequestBody Hospedes hospedeDetails) {
        return hospedeService.findById(id)
                .map(hospede -> {
                    hospede.setNome(hospedeDetails.getNome());
                    hospede.setCpf(hospedeDetails.getCpf());
                    hospede.setTelefone(hospedeDetails.getTelefone());
                    hospede.setRg(hospedeDetails.getRg());
                    hospede.setDataNascimento(hospedeDetails.getDataNascimento());
                    return ResponseEntity.ok(hospedeService.save(hospede));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospede(@PathVariable Long id) {
        hospedeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}