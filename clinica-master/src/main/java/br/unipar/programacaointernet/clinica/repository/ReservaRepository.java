package br.unipar.programacaointernet.clinica.repository;



import br.unipar.programacaointernet.clinica.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findAllByHospedeId(Long hospedeId);

    List<Reserva> findAllByDataEntrada(String dataEntrada);
}