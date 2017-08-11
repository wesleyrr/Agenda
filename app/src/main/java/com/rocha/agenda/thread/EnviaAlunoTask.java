package com.rocha.agenda.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rocha.agenda.ws.WebClient;
import com.rocha.agenda.converter.AlunoConverter;
import com.rocha.agenda.dao.AlunoDAO;
import com.rocha.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by wesley on 08/02/17.
 */
//envia aluno para webservice
public class EnviaAlunoTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunoTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Aguarde","Enviando Alunos...", true, true);
    }

    //método a ser executado na thread secundária
    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJson(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;//retorno env para onPostExecute
    }

    //roda na thread principal
    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta, Toast.LENGTH_LONG).show();
    }
}
