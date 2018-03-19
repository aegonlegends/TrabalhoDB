package br.edu.iff.pooa20172.trabalhodb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.iff.pooa20172.trabalhodb.Model.Proprietario;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivityEditProprietario extends AppCompatActivity {

    private int id;
    private Proprietario proprietario;
    private Realm realm;

    private EditText nome, endereco, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_proprietario);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");

        realm = Realm.getDefaultInstance();

        nome = findViewById(R.id.proprietarionome);
        endereco = findViewById(R.id.proprietarioende);
        telefone = findViewById(R.id.proprietariotele);

        Button deletar = findViewById(R.id.deletarproprietario);
        Button salvar = findViewById(R.id.salvarproprietario);

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
            proprietario = new Proprietario("","","");
            proprietario.setId(id);
        }
        else{
            proprietario = realm.where(Proprietario.class).equalTo("id",id).findFirst();
        }

        nome.setText(proprietario.getNome());
        endereco.setText(proprietario.getEndereco());
        telefone.setText(proprietario.getTelefone());
    }

    protected void onClickDelete(){
        realm.beginTransaction();
        proprietario.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        this.setResult(RESULT_OK);
        this.finish();
    }

    protected void onClickSave(){
        if(id == -1){
            int proximoID = 1;
            if(realm.where(Proprietario.class).max("id") !=null)
                proximoID = realm.where(Proprietario.class).max("id").intValue()+1;
            proprietario.setId(proximoID);
        }

        realm.beginTransaction();

        proprietario.setNome(nome.getText().toString());
        proprietario.setEndereco(endereco.getText().toString());
        proprietario.setTelefone(telefone.getText().toString());

        realm.copyToRealmOrUpdate(proprietario);
        realm.commitTransaction();
        realm.close();

        this.setResult(RESULT_OK);
        this.finish();

    }
}
