package br.edu.iff.pooa20172.trabalhodb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.iff.pooa20172.trabalhodb.Model.Servico;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivityEditServico extends AppCompatActivity {

    private int id;
    private Servico servico;
    private Realm realm;

    private EditText nome, horas, mecanico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_servico);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");

        realm = Realm.getDefaultInstance();

        nome = findViewById(R.id.serviconome);
        horas = findViewById(R.id.servicohora);
        mecanico = findViewById(R.id.servicomeca);

        Button deletar = findViewById(R.id.deletarservico);
        Button salvar = findViewById(R.id.salvarservico);

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelete();
            }
        });
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSave();
            }
        });

        if(id == -1){
            deletar.setEnabled(false);
            deletar.setClickable(false);
            deletar.setVisibility(View.INVISIBLE);
            servico = new Servico("","","");
            servico.setId(id);
        }
        else{
            servico = realm.where(Servico.class).equalTo("id",id).findFirst();
        }

        nome.setText(servico.getNome());
        horas.setText(servico.getHoras());
        mecanico.setText(servico.getMecanico());
    }

    protected void onClickDelete(){
        realm.beginTransaction();
        servico.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        this.setResult(RESULT_OK);
        this.finish();
    }

    protected void onClickSave(){
        if(id == -1){
            int proximoID = 1;
            if(realm.where(Servico.class).max("id") !=null)
                proximoID = realm.where(Servico.class).max("id").intValue()+1;
            servico.setId(proximoID);
        }

        realm.beginTransaction();

        servico.setNome(nome.getText().toString());
        servico.setHoras(horas.getText().toString());
        servico.setMecanico(mecanico.getText().toString());

        realm.copyToRealmOrUpdate(servico);
        realm.commitTransaction();
        realm.close();

        this.setResult(RESULT_OK);
        this.finish();

    }
}
