package com.example.agendinha3;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao_DB extends SQLiteOpenHelper {
//criação do banco e da versão
        private static final String name= "Banco.db";
        private static final int version = 1;
//aplicando a criação do banco em sql
        public Conexao_DB(Context context){

                super(context,name,null,version);
        }
        @Override
        //criação de tabelas e atributos
        public void onCreate(SQLiteDatabase db){
                db.execSQL("create Table Agenda (Id integer primary key autoincrement,"+
                        "Nome varchar(60),Tele varchar(12),Email varchar(70) )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

//                public String autenticaUsuario(Usuario usuario){
//
//                        SQLiteDatabase db = getReadableDatabase();
//                        String sqli_busca_usuarios = "select * from Agenda Where Email = " + "'"
//                                + Agenda_OBJ.getEmail() + "'";
//
//                        Cursor cursor = db.rawQuery(sqli_busca_usuarios, null);
//                        while(cursor.moveToNext()){
//                                if(Agenda_OBJ.getEmail().equals(cursor.getString(cursor.getColumnIndex("Email")))) {
//                                        if(Agenda_OBJ.getNome().equals(cursor.getString(cursor.getColumnIndex("Nome")))){
//                                                return "usuário existente"
//                                }
//                                }
//                        }
//
//                        return "Senha ou email inseridos indevidamente";
//                }

        }




}
