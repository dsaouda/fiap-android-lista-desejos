package com.github.dsaouda.listadesejos.dto;

public class Produto {

    private String nome;
    private String descricao;
    private String image;
    private Double valor;

    public Produto(String nome, String image, Double valor) {
        this.nome = nome;
        this.image = image;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getImage() {
        return image;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", image='" + image + '\'' +
                ", valor=" + valor +
                '}';
    }
}
