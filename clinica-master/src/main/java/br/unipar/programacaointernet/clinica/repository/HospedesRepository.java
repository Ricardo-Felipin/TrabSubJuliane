package br.unipar.programacaointernet.clinica.repository;


import br.unipar.programacaointernet.clinica.model.Hospedes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedesRepository extends JpaRepository<Hospedes, Long> {
}