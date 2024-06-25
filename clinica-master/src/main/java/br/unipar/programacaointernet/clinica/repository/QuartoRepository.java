package br.unipar.programacaointernet.clinica.repository;


import br.unipar.programacaointernet.clinica.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

        @Query("SELECT q FROM Quarto q WHERE q.status = 'DISPONIVEL' AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.quarto.id = q.id AND r.dataEntrada <= :dataSaida AND r.dataSaida >= :dataEntrada)")
        List<Quarto> findQuartosDisponiveis(String dataEntrada, String dataSaida);

        List<Quarto> findByQtdMaxOcupantes(int qtdMaxOcupantes);

        List<Quarto> findByVistaMarTrueAndReservasIsEmpty();
}