package br.edu.iff.pooa20172.trabalhodb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20172.trabalhodb.Model.Peca;
import br.edu.iff.pooa20172.trabalhodb.Model.Proprietario;
import br.edu.iff.pooa20172.trabalhodb.R;

/**
 * Created by Wesley Gomes on 17/03/2018.
 */

public class ProprietarioAdapter extends RecyclerView.Adapter {

    private List<Proprietario> proprietarios;
    private Context context;
    private ClickRecyclerViewListener clickListener;

    public ProprietarioAdapter(List<Proprietario> proprietarios, Context context, ClickRecyclerViewListener clickListener) {
        this.proprietarios = proprietarios;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_proprietario, parent, false);
        ProprietarioViewHolder proprietarioViewHolder = new ProprietarioViewHolder(view);
        return proprietarioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ProprietarioViewHolder holder = (ProprietarioViewHolder) viewHolder;

        Proprietario proprietario  = proprietarios.get(position) ;

        holder.nome.setText(proprietario.getNome());
        holder.endereco.setText(proprietario.getEndereco());
        holder.telefone.setText(proprietario.getTelefone());

    }

    @Override
    public int getItemCount() {
        return proprietarios.size();
    }


    public class ProprietarioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView endereco;
        private final TextView telefone;

        public ProprietarioViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            endereco = (TextView) itemView.findViewById(R.id.endereco);
            telefone = (TextView) itemView.findViewById(R.id.telefone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(proprietarios.get(getLayoutPosition()));

                }
            });
        }
    }
}
