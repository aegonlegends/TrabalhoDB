package br.edu.iff.pooa20172.trabalhodb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.iff.pooa20172.trabalhodb.Model.Peca;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivityEditPeca extends AppCompatActivity {

    private int id;
    private Peca peca;
    private Realm realm;

    private EditText nome, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_peca);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");

        realm = Realm.getDefaultInstance();

        nome = findViewById(R.id.pecanome);
        descricao = findViewById(R.id.pecadesc);

        Button deletar = findViewById(R.id.deletarpeca);
        Button salvar = findViewById(R.id.salvarpeca);

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
            peca = new Peca("","");
            peca.setId(id);
        }
        else{
            peca = realm.where(Peca.class).equalTo("id",id).findFirst();
        }

        nome.setText(peca.getNome());
        descricao.setText(peca.getDescricao());
    }

    protected void onClickDelete(){
        realm.beginTransaction();
        peca.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        this.setResult(RESULT_OK);
        this.finish();
    }

    protected void onClickSave(){
        if(id == -1){
            int proximoID = 1;
            if(realm.where(Peca.class).max("id") !=null)
                proximoID = realm.where(Peca.class).max("id").intValue()+1;
            peca.setId(proximoID);
        }

        realm.beginTransaction();

        peca.setNome(nome.getText().toString());
        peca.setDescricao(descricao.getText().toString());

        realm.copyToRealmOrUpdate(peca);
        realm.commitTransaction();
        realm.close();

        this.setResult(RESULT_OK);
        this.finish();

    }
}
