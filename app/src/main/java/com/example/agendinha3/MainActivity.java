package com.example.agendinha3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //criando obijetos e variaves de relação e clases
    private EditText nome;
    private EditText tele;
    private EditText email;
    private Agenda_DAO dao;
    private Agenda_OBJ agendaItem = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//atribundo valores dos obijetos
        nome = findViewById(R.id.EdNome);
        tele = findViewById(R.id.EdTele);
        email = findViewById(R.id.EdEmail);

        dao = new Agenda_DAO(this);

        Intent acao = getIntent();
        if (acao.hasExtra("agendaItem")){
            agendaItem=(Agenda_OBJ) acao.getSerializableExtra("agendaItem");
            nome.setText(agendaItem.getNome());
            tele.setText(agendaItem.getTele());
            email.setText(agendaItem.getEmail());
        }

    }
//crição da chamada de adição ao banco
    public void adiciona (View view){

        if (agendaItem == null) {
            Agenda_OBJ dado = new Agenda_OBJ();
            dado.setNome(nome.getText().toString());
            dado.setTele(tele.getText().toString());
            dado.setEmail(email.getText().toString());

            Long id = dao.Adiciona(dado);
            Toast.makeText(this, " Cadastrado OK " + id, Toast.LENGTH_LONG).show();
        }else {
            agendaItem.setNome(nome.getText().toString());
            agendaItem.setTele(tele.getText().toString());
            agendaItem.setEmail(email.getText().toString());
            dao.Alterar(agendaItem);
            Toast.makeText(this, " Alterado OK " , Toast.LENGTH_LONG).show();
        }
    }
//chamada da tela de listagem
    public void Tela_Lista(View view){
        Intent acao = new Intent(this,Lista_Tudo.class);
        startActivity(acao);
        finish();
    }
}