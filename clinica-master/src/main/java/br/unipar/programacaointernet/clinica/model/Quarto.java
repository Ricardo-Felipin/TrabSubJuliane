package br.unipar.programacaointernet.clinica.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private boolean vistaMar;
    private double valorDia;
    private int qtdMaxOcupantes;
    private String status;
    private String descricao;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Quarto() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVistaMar() {
        return vistaMar;
    }

    public void setVistaMar(boolean vistaMar) {
        this.vistaMar = vistaMar;
    }

    public double getValorDia() {
        return valorDia;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }

    public int getQtdMaxOcupantes() {
        return qtdMaxOcupantes;
    }

    public void setQtdMaxOcupantes(int qtdMaxOcupantes) {
        this.qtdMaxOcupantes = qtdMaxOcupantes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
