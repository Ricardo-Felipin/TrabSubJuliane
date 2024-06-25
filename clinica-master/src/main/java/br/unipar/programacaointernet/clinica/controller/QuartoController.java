package br.unipar.programacaointernet.clinica.controller;


import br.unipar.programacaointernet.clinica.model.Quarto;
import br.unipar.programacaointernet.clinica.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity<Quarto> createQuarto(@RequestBody Quarto quarto) {
        Quarto savedQuarto = quartoService.save(quarto);
        return ResponseEntity.ok(savedQuarto);
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> getAllQuartos() {
        List<Quarto> quartos = quartoService.findAll();
        return ResponseEntity.ok(quartos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> getQuartoById(@PathVariable Long id) {
        return quartoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Quarto>> getQuartosDisponiveis(@RequestParam String dataEntrada, @RequestParam String dataSaida) {
        List<Quarto> quartos = quartoService.findDisponiveis(dataEntrada, dataSaida);
        return ResponseEntity.ok(quartos);
    }

    @GetMapping("/ocupantes/{qtdMaxOcupantes}")
    public ResponseEntity<List<Quarto>> getQuartosByQtdMaxOcupantes(@PathVariable int qtdMaxOcupantes) {
        List<Quarto> quartos = quartoService.findByQtdMaxOcupantes(qtdMaxOcupantes);
        return ResponseEntity.ok(quartos);
    }

    @GetMapping("/vistamar/disponiveis")
    public ResponseEntity<List<Quarto>> getQuartosComVistaMarDisponiveis() {
        List<Quarto> quartos = quartoService.findComVistaMarDisponiveis();
        return ResponseEntity.ok(quartos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> updateQuarto(@PathVariable Long id, @RequestBody Quarto quartoDetails) {
        return quartoService.findById(id)
                .map(quarto -> {
                    quarto.setNome(quartoDetails.getNome());
                    quarto.setQtdMaxOcupantes(quartoDetails.getQtdMaxOcupantes());
                    quarto.setVistaMar(quartoDetails.isVistaMar());
                    quarto.setValorDia(quartoDetails.getValorDia());
                    quarto.setDescricao(quartoDetails.getDescricao());
                    return ResponseEntity.ok(quartoService.save(quarto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuarto(@PathVariable Long id) {
        quartoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}