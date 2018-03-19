package br.edu.iff.pooa20172.trabalhodb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.edu.iff.pooa20172.trabalhodb.Adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20172.trabalhodb.Adapter.ProprietarioAdapter;
import br.edu.iff.pooa20172.trabalhodb.Model.Proprietario;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivitySelectProprietario extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;
    private List<Proprietario> proprietarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_proprietario);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySelectProprietario.this, ActivityEditProprietario.class);
                intent.putExtra("id", -1);
                startActivity(intent);
            }
        });
    }

    private List<Proprietario> getProprietarios() {
        proprietarios = realm.where(Proprietario.class).findAll();
        return proprietarios;
    }

    @Override
    public void onClick(Object object) {
        Proprietario proprietario = (Proprietario) object;
        Intent intent = new Intent(ActivitySelectProprietario.this, ActivityEditProprietario.class);
        intent.putExtra("id", proprietario.getId());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            getProprietarios();
            ((RecyclerView) findViewById(R.id.rv_proprietario)).getAdapter().notifyDataSetChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.rv_proprietario);

        recyclerView.setAdapter(new ProprietarioAdapter(getProprietarios(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }

    @Override
    public void finish(){
        realm.close();
        super.finish();
    }

}
