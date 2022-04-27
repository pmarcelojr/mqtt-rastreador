package com.phonerastreador.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"nome", "user_id"})
})
public class Dispositivo {

    @Id
    private String id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User usuario;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private String topico;

    @Column(nullable = false)
    private String senha;

    @OneToOne(optional = true)
    @JoinColumn(name = "ultima_localizacao_id", nullable = true, updatable = true)
    private Localizacao ultimaLocalizacao;

    @Column(nullable = true)
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    public void gerarPadroes() {
        this.id = UUID.randomUUID().toString();
        this.criadoEm = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public User getUsuario() {
        return this.usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getTopico() {
        return this.topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Localizacao getUltimaLocalizacao() {
        return this.ultimaLocalizacao;
    }

    public void setUltimaLocalizacao(Localizacao ultimaLocalizacao) {
        this.ultimaLocalizacao = ultimaLocalizacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return this.ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

}
