package com.example.agendinha3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Agenda_DAO {

    private Conexao_DB conexao_db;
    private SQLiteDatabase banco;

    public Agenda_DAO(Context context){
    conexao_db= new Conexao_DB(context);
    banco=conexao_db.getWritableDatabase();
    }
    public long Adiciona(Agenda_OBJ agenda_obj){
        ContentValues Valores =new ContentValues();
        Valores.put("Nome",agenda_obj.getNome());
        Valores.put("Tele",agenda_obj.getTele());
        Valores.put("Email",agenda_obj.getEmail());
        return banco.insert("Agenda",null,Valores);
    }

    public List<Agenda_OBJ> ObterTudo(){
        List<Agenda_OBJ> agenda_objs = new ArrayList<>();
        Cursor ponteiro = banco.query("Agenda",new String[]{"Id","Nome","Tele","Email"},
                null,null,null,null,null);

        while (ponteiro.moveToNext()){
            Agenda_OBJ Roda =new Agenda_OBJ();
            Roda.setID(ponteiro.getInt(0));
            Roda.setNome(ponteiro.getString(1));
            Roda.setTele(ponteiro.getString(2));
            Roda.setEmail(ponteiro.getString(3));
            agenda_objs.add(Roda);
        }
        return agenda_objs;

    }
    public void ExcluirDao(Agenda_OBJ a){
        banco.delete("Agenda","id = ?",new String[]{a.getID().toString()});
    }

    public void Alterar(Agenda_OBJ a){
        ContentValues Valores =new ContentValues();
        Valores.put("Nome",a.getNome());
        Valores.put("Tele",a.getTele());
        Valores.put("Email",a.getEmail());
        banco.update("Agenda", Valores,"Id= ?",new String[]{a.getID().toString()});

    }


}
