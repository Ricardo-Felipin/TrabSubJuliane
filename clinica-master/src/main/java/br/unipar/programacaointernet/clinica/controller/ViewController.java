package br.unipar.programacaointernet.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/cadastro-cliente")
    public String cadastroCliente() {
        return "cadastro-cliente";
    }

    @GetMapping("/cadastro-reserva")
    public String cadastroReserva() {
        return "cadastro-reserva";
    }

    @GetMapping("/controle-reserva")
    public String controleReserva() {
        return "controle-reserva";
    }

    @GetMapping("/consulta-quartos")
    public String consultaQuartos() {
        return "consulta-quartos";
    }
}