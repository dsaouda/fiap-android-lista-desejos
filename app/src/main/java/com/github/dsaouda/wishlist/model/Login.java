package com.github.dsaouda.wishlist.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Login {

    @Id
    private Long id;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private boolean manterConectado = false;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Generated(hash = 1343796882)
    public Login(Long id, @NotNull String usuario, @NotNull String senha,
            boolean manterConectado) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.manterConectado = manterConectado;
    }

    @Generated(hash = 1827378950)
    public Login() {
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isManterConectado() {
        return manterConectado;
    }

    public void setManterConectado(boolean manterConectado) {
        this.manterConectado = manterConectado;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public boolean getManterConectado() {
        return this.manterConectado;
    }
}
