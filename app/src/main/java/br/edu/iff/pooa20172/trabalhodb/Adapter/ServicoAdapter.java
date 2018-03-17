package br.edu.iff.pooa20172.trabalhodb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20172.trabalhodb.Model.Servico;
import br.edu.iff.pooa20172.trabalhodb.R;

/**
 * Created by Wesley Gomes on 17/03/2018.
 */

public class ServicoAdapter extends RecyclerView.Adapter {

    private List<Servico> servicos;
    private Context context;
    private ClickRecyclerViewListener clickListener;

    public ServicoAdapter(List<Servico> servicos, Context context, ClickRecyclerViewListener clickListener) {
        this.servicos = servicos;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_servico, parent, false);
        ServicoViewHolder servicoViewHolder = new ServicoViewHolder(view);
        return servicoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ServicoViewHolder holder = (ServicoViewHolder) viewHolder;

        Servico servico  = servicos.get(position) ;

        holder.nome.setText(servico.getNome());
        holder.mecanico.setText(servico.getMecanico());
        holder.horas.setText(servico.getHoras());

    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }


    public class ServicoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView mecanico;
        private final TextView horas;

        public ServicoViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            mecanico = (TextView) itemView.findViewById(R.id.mecanico);
            horas = (TextView) itemView.findViewById(R.id.horas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(servicos.get(getLayoutPosition()));

                }
            });
        }
    }
}
