package com.example.agendinha3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Lista_Tudo extends AppCompatActivity {
    //criando obijetos e variaves de relação e clases
        private ListView listView;
        private Agenda_DAO dao;
        private List<Agenda_OBJ> objList;
        private List<Agenda_OBJ> objList_fil =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tudo);
        //atribundo valores dos obijetos
        listView = findViewById(R.id.Listar);
        dao=new Agenda_DAO(this);
        objList=dao.ObterTudo();
        objList_fil.addAll(objList);

        ArrayAdapter<Agenda_OBJ> adaptador = new ArrayAdapter<Agenda_OBJ>(
                this, android.R.layout.simple_list_item_1, objList_fil);
        listView.setAdapter(adaptador);

        //registrando o menu de contexto
        registerForContextMenu(listView);
    }

    //menu de contexto
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i =getMenuInflater();
        i.inflate(R.menu.menu_flutuante,menu);
    }
    //função de chamada da alteração
    public void Alterar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo=
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Agenda_OBJ itemAuterado = objList_fil.get(menuInfo.position);

        Intent acao = new Intent(this,MainActivity.class);
        acao.putExtra("agendaItem", itemAuterado);
        startActivity(acao);
        finish();
    }

    //chada da exclusão
    public void Excluir (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo=
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Agenda_OBJ itemExcuido = objList_fil.get(menuInfo.position);

        AlertDialog dialog =new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja Excluir?")
                .setNegativeButton("CANCELAR",null)
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        objList_fil.remove(itemExcuido);
                        objList.remove(itemExcuido);
                        dao.ExcluirDao(itemExcuido);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    //menu de buscando na lista + busca de item
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);

        SearchView sv =(SearchView)menu.findItem(R.id.app_bar_buscar).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //System.out.println("digitou "+s);
                ProcuraAgenda(s);
                return false;
            }
        });
        return true;
    }

// busca na lista
    public void ProcuraAgenda(String nome){
        objList_fil.clear();
        for (Agenda_OBJ a : objList ){
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())){
                objList_fil.add(a);
            }
        }
        listView.invalidateViews();
    }
//volta para tela de cadastro
    public void Voltar_Principal (View view){
        Intent acao = new Intent(this,MainActivity.class);
        startActivity(acao);
        finish();
    }

}