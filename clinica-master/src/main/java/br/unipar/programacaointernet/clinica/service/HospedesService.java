package br.unipar.programacaointernet.clinica.service;


import br.unipar.programacaointernet.clinica.model.Hospedes;
import br.unipar.programacaointernet.clinica.repository.HospedesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class HospedesService {

    @Autowired
    private HospedesRepository hospedeRepository;

    public Hospedes save(Hospedes hospede) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(hospede.getDataNascimento(), formatter);
        if (Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Hospede deve ser maior de idade");
        }
        return hospedeRepository.save(hospede);
    }

    public List<Hospedes> findAll() {
        return hospedeRepository.findAll();
    }

    public Optional<Hospedes> findById(Long id) {
        return hospedeRepository.findById(id);
    }

    public void deleteById(Long id) {
        hospedeRepository.deleteById(id);
    }
}