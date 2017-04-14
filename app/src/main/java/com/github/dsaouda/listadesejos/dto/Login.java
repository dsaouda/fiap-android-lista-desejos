package com.github.dsaouda.listadesejos.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("senha")
    @Expose
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public com.github.dsaouda.listadesejos.model.Login getModel() {
        return new com.github.dsaouda.listadesejos.model.Login(usuario, senha);
    }
}