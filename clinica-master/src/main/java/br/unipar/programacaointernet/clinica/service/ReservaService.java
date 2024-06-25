package br.unipar.programacaointernet.clinica.service;


import br.unipar.programacaointernet.clinica.model.Reserva;
import br.unipar.programacaointernet.clinica.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva save(Reserva reserva) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataEntrada = LocalDate.parse(reserva.getDataEntrada(), formatter);
        LocalDate dataSaida = LocalDate.parse(reserva.getDataSaida(), formatter);
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);

        if (dias < 2) {
            throw new IllegalArgumentException("Reserva deve ter no mínimo 2 dias");
        }

        double valorTotal = dias * reserva.getQuarto().getValorDia();
        reserva.setValorTotal(valorTotal);
        reserva.setStatus("PENDENTE");
        return reservaRepository.save(reserva);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> findByHospedeId(Long hospedeId) {
        return reservaRepository.findAllByHospedeId(hospedeId);
    }

    public List<Reserva> findByDataEntrada(String dataEntrada) {
        return reservaRepository.findAllByDataEntrada(dataEntrada);
    }

    public void checkin(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        reserva.setStatus("ABERTO");
        reservaRepository.save(reserva);
    }

    public void checkout(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        reserva.setStatus("FECHADO");
        reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }
}