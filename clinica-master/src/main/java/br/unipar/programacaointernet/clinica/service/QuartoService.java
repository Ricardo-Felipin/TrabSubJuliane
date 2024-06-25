package br.unipar.programacaointernet.clinica.service;


import br.unipar.programacaointernet.clinica.model.Quarto;
import br.unipar.programacaointernet.clinica.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public Quarto save(Quarto quarto) {
        if (quarto.isVistaMar()) {
            quarto.setValorDia(quarto.getValorDia() * 2);
        }
        return quartoRepository.save(quarto);
    }

    public List<Quarto> findAll() {
        return quartoRepository.findAll();
    }

    public Optional<Quarto> findById(Long id) {
        return quartoRepository.findById(id);
    }

    public List<Quarto> findDisponiveis(String dataEntrada, String dataSaida) {
        return quartoRepository.findQuartosDisponiveis(dataEntrada, dataSaida);
    }

    public List<Quarto> findByQtdMaxOcupantes(int qtdMaxOcupantes) {
        return quartoRepository.findByQtdMaxOcupantes(qtdMaxOcupantes);
    }

    public List<Quarto> findComVistaMarDisponiveis() {
        return quartoRepository.findByVistaMarTrueAndReservasIsEmpty();
    }

    public void deleteById(Long id) {
        quartoRepository.deleteById(id);
    }
}
