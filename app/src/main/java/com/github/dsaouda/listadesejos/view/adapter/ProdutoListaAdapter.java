package com.github.dsaouda.listadesejos.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.dsaouda.listadesejos.R;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.view.holder.ProdutoViewHolder;

import java.util.List;

import butterknife.ButterKnife;

public class ProdutoListaAdapter extends RecyclerView.Adapter {

    private final List<Produto> produtos;
    private final Context context;
    private final ProdutoDao dao;

    public ProdutoListaAdapter(List<Produto> produtos, Context context, ProdutoDao dao) {
        this.produtos = produtos;
        this.context = context;
        this.dao = dao;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view, this, dao);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ProdutoViewHolder holder = (ProdutoViewHolder) viewHolder;
        final Produto produto = produtos.get(position);
        holder.preencher(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void remove(int position) {
        produtos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}