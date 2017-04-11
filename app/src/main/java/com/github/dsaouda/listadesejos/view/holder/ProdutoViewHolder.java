package com.github.dsaouda.listadesejos.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.dsaouda.listadesejos.ProdutoActivity;
import com.github.dsaouda.listadesejos.R;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.view.adapter.ProdutoListaAdapter;

public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private final TextView tvProduto;
    private final TextView tvValor;
    private final TextView tvTag;
    private final ProdutoDao dao;
    private final ProdutoListaAdapter adapter;
    private Produto produto;

    public ProdutoViewHolder(final View view, final ProdutoListaAdapter adapter, final ProdutoDao dao) {
        super(view);
        this.adapter = adapter;
        this.dao = dao;

        view.setOnLongClickListener(this);
        tvProduto = (TextView) view.findViewById(R.id.tvProduto);
        tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvTag = (TextView) view.findViewById(R.id.tvTag);
    }

    public void removerEndereco() {
        dao.delete(produto);
        adapter.remove(getAdapterPosition());
    }

    public void preencher(Produto produto) {
        this.produto = produto;
        tvProduto.setText(produto.getNome());
        tvValor.setText(String.valueOf(produto.getValor()));
        tvTag.setText(produto.getTag());
    }


    @Override
    public boolean onLongClick(final View v) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.menu_item_produto, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuEditar:

                        final Activity context = (Activity)v.getContext();
                        final Intent intent = new Intent(context, ProdutoActivity.class);
                        intent.putExtra("produto", produto.getId());
                        context.startActivityForResult(intent, 201);

                        break;

                    case R.id.menuDeletar:
                        removerEndereco();

                        break;

                    case R.id.menuVisualizarExterno:

                        break;
                }

                return true;
            }
        });

        popup.show();
        return false;
    }

}