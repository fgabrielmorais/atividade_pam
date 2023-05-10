package com.example.agendinha3;

import android.content.ContentValues;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.nio.channels.NonReadableChannelException;


public class Agenda_OBJ implements Serializable{

    private Integer ID;
    private String Nome;
    private String Tele;
    private String Email;

    public Integer getID() {

        return ID;
    }

    public void setID(Integer ID) {

        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {

        Nome = nome;
    }

    public String getTele() {

        return Tele;
    }

    public void setTele(String tele) {

        Tele = tele;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {

        Email = email;
    }

@Override
    public String toString() {
        //mascara para a lista
    return "\n \n " +
            "Cod: "+ this.ID+ "\n" +
            "Nome: " + this.Nome +"\n " +
            "Tel:" +this.Tele + "\n " +
            "Email: "+ this.Email;
    }






}
