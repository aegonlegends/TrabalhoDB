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
import br.edu.iff.pooa20172.trabalhodb.Adapter.ServicoAdapter;
import br.edu.iff.pooa20172.trabalhodb.Model.Servico;
import br.edu.iff.pooa20172.trabalhodb.R;
import io.realm.Realm;

public class ActivitySelectServico extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;
    private List<Servico> servicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_servico);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySelectServico.this, ActivityEditServico.class);
                intent.putExtra("id", -1);
                startActivity(intent);
            }
        });
    }

    private List<Servico> getServicos() {
        servicos = realm.where(Servico.class).findAll();
        return servicos;
    }

    @Override
    public void onClick(Object object) {
        Servico servico = (Servico) object;
        Intent intent = new Intent(ActivitySelectServico.this, ActivityEditServico.class);
        intent.putExtra("id", servico.getId());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            getServicos();
            ((RecyclerView) findViewById(R.id.rv_servico)).getAdapter().notifyDataSetChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.rv_servico);

        recyclerView.setAdapter(new ServicoAdapter(getServicos(),this,this));
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
