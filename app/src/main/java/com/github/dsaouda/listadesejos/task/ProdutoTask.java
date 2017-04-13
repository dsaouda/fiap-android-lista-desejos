package com.github.dsaouda.listadesejos.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.github.dsaouda.listadesejos.ProdutoActivity;
import com.github.dsaouda.listadesejos.R;
import com.github.dsaouda.listadesejos.dto.Produto;
import com.github.dsaouda.listadesejos.factory.B2WServiceFactory;
import com.github.dsaouda.listadesejos.service.B2WService;

import java.net.URL;

public class ProdutoTask extends AsyncTask<URL, Void, Produto> {

    private ProgressDialog pgLoading;
    private ProdutoActivity activity;

    public ProdutoTask(ProdutoActivity produtoActivity) {
        this.activity = produtoActivity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pgLoading = new ProgressDialog(activity);
        pgLoading.setMessage("Buscando dados do produto ...");
        pgLoading.show();
    }

    @Override
    protected Produto doInBackground(URL... params) {
        final URL url = params[0];

        try {
            final B2WService service = B2WServiceFactory.create(url);
            final Produto produto = service.getProduto(url);
            return produto;
        } catch (RuntimeException e) {
            activity.setErrorEditTextURL(activity.getString(R.string.url_not_supported));
            return null;
        }
    }

    @Override
    protected void onPostExecute(Produto produto) {
        if (produto != null) {
            activity.preencherCampos(produto);
        }

        pgLoading.hide();
        pgLoading.dismiss();
    }
}