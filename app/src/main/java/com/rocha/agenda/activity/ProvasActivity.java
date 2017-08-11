package com.rocha.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rocha.agenda.R;
import com.rocha.agenda.modelo.Aluno;
import com.rocha.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Pronomes", "Adverbios","Crase");
        Prova provaPortugue = new Prova("Portugues","20/02/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Frações","Eq Segundo Grau", "Logica");
        Prova provaMatematica = new Prova("Matemática","22/02/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugue, provaMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);

                Intent intentDetalhesProva = new Intent(ProvasActivity.this, DetalhesProvaActivity.class);
                intentDetalhesProva.putExtra("prova", prova);
                startActivity(intentDetalhesProva);

                Toast.makeText(ProvasActivity.this, "Clicou na prova de " + prova.getMateria(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
