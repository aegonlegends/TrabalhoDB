package br.edu.iff.pooa20172.trabalhodb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20172.trabalhodb.Model.Peca;
import br.edu.iff.pooa20172.trabalhodb.R;

/**
 * Created by Wesley Gomes on 17/03/2018.
 */

public class PecaAdapter extends RecyclerView.Adapter {

    private List<Peca> pecas;
    private Context context;
    private ClickRecyclerViewListener clickListener;

    public PecaAdapter(List<Peca> pecas, Context context, ClickRecyclerViewListener clickListener) {
        this.pecas = pecas;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_peca, parent, false);
        PecaViewHolder pecaViewHolder = new PecaViewHolder(view);
        return pecaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PecaViewHolder holder = (PecaViewHolder) viewHolder;

        Peca peca  = pecas.get(position) ;

        holder.nome.setText(peca.getNome());
        holder.descricao.setText(peca.getDescricao());
    }

    @Override
    public int getItemCount() {
        return pecas.size();
    }


    public class PecaViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView descricao;


        public PecaViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(pecas.get(getLayoutPosition()));

                }
            });


        }
    }
}
