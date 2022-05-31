package com.phonerastreador.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acls {

    public static final Integer SOMENTE_LEITURA = 1;
    public static final Integer LER_GRAVAR = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column
    private String nome;

    @Column(nullable = false)
    private String topic;

    // 1 apenas leitura
    // 2 ler-gravar
    @Column(nullable = false)
    private Integer rw = 1;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getRw() {
        return this.rw;
    }

    public void setRw(Integer rw) {
        this.rw = rw;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
