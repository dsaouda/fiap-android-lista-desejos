package com.github.dsaouda.listadesejos.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Produto {

    @Id
    private Long id;

    @NotNull
    private String nome;
    private String descricao;
    private double valor;
    private String tag;

    @Generated(hash = 601775242)
    public Produto(Long id, @NotNull String nome, String descricao, double valor,
            String tag) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tag = tag;
    }
    @Generated(hash = 1436078576)
    public Produto() {
    }


    public Produto(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tag='" + tag + '\'' +
                '}';
    }
}
