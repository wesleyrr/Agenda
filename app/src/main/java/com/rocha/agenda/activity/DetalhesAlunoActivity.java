package com.rocha.agenda.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.rocha.agenda.R;
import com.rocha.agenda.modelo.Aluno;

public class DetalhesAlunoActivity extends AppCompatActivity {

    private ImageView campoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_aluno);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        TextView campoNome = (TextView) findViewById(R.id.detalhes_aluno_nome);
        campoNome.setText(aluno.getNome());

        /*TextView campoEndereco = (TextView) findViewById(R.id.detalhes_aluno_endereco);
        campoEndereco.setText(aluno.getEndereco());*/

        if(aluno.getNota() != null){
            RatingBar campoNota = (RatingBar) findViewById(R.id.detalhes_aluno_nota);
            campoNota.setProgress(aluno.getNota().intValue());
        }

       Button btnCancelar = (Button) findViewById(R.id.detalhes_aluno_btn_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnReservar = (Button) findViewById(R.id.detalhes_aluno_btn_reservar);

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalhesAlunoActivity.this,"Parabéns! Reserva efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }



    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap,300,300,true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);//ajusta a foto no imageview
            campoFoto.setTag(caminhoFoto);
        }
    }
}
