package com.rocha.agenda.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rocha.agenda.helper.FormularioHelper;
import com.rocha.agenda.R;
import com.rocha.agenda.dao.AlunoDAO;
import com.rocha.agenda.modelo.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 555;
    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        final Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");//RECUPERA O DADO COLOCADO NA INTENT PELO PUTEXTRA

        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //getExternalFilesDir() -> metodo que pega o caminho padrão para os diversos app (por convenção)
            caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() +  ".jpg";
            File arquivoFoto = new File(caminhoFoto);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

            startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
    }

    //este método sempre é chamado após qualquer startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CODIGO_CAMERA){
                helper.carregaImagem(caminhoFoto);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);

                if (aluno.getId() != null){
                   dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }

                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

