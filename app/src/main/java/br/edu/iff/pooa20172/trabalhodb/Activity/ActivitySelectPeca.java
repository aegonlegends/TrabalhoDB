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
import br.edu.iff.pooa20172.trabalhodb.Adapter.PecaAdapter;
import br.edu.iff.pooa20172.trabalhodb.Model.Peca;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivitySelectPeca extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;
    private List<Peca> pecas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_peca);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySelectPeca.this, ActivityEditPeca.class);
                intent.putExtra("id", -1);
                startActivity(intent);
            }
        });
    }

    private List<Peca> getPecas() {
        pecas = realm.where(Peca.class).findAll();
        return pecas;
    }

    @Override
    public void onClick(Object object) {
        Peca peca = (Peca) object;
        Intent intent = new Intent(ActivitySelectPeca.this, ActivityEditPeca.class);
        intent.putExtra("id", peca.getId());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            getPecas();
            ((RecyclerView) findViewById(R.id.rv_peca)).getAdapter().notifyDataSetChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.rv_peca);

        recyclerView.setAdapter(new PecaAdapter(getPecas(),this,this));
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
