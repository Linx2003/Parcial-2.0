package com.example.agenda;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario;

    public Usuario(String usuario){
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
}
