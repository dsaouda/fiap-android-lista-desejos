package com.github.dsaouda.listadesejos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.task.ProdutoTask;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Url;
import com.squareup.picasso.Picasso;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class ProdutoActivity extends AppCompatActivity implements Validator.ValidationListener {

    private DaoSession daoSession;
    private ProdutoDao dao;

    @NotEmpty(messageResId = R.string.field_require)
    @BindView(R.id.etProduto)
    EditText etProduto;

    @NotEmpty(messageResId = R.string.field_require)
    @Digits(messageResId = R.string.digits_not_valid, integer = 7, fraction = 2)
    @BindView(R.id.etValor)
    EditText etValor;

    @BindView(R.id.spTag)
    SearchableSpinner spTag;

    @BindView(R.id.ivProduto)
    ImageView ivProduto;

    @NotEmpty(messageResId = R.string.field_require)
    @Url(messageResId = R.string.url_not_valid)
    @BindView(R.id.etUrlProduto)
    EditText etUrlProduto;

    private Validator validator;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        ButterKnife.bind(this);
        validator = new Validator(this);

        daoSession = ((App) getApplication()).getDaoSession();
        dao = daoSession.getProdutoDao();

        loadSpinner();
        loadProduto();



    }

    @OnFocusChange(R.id.etUrlProduto)
    public void buscarProduto(View v, boolean hasFocus) {

        if (hasFocus == true) {
            return ;
        }

        String urlStr = etUrlProduto.getText().toString();

        try {
            final URL url = new URL(urlStr);
            new ProdutoTask(this).execute(url);
        } catch (MalformedURLException e) {
            etUrlProduto.setError(getString(R.string.url_not_valid));
            return ;
        }
    }

    private void loadSpinner() {
        spTag.setTitle(getString(R.string.select_category));
        spTag.setPositiveButton(getString(R.string.close));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tag_product));
        spTag.setAdapter(spinnerArrayAdapter);
    }

    @OnClick(R.id.btnSalvar)
    public void salvar() {
        validator.setValidationListener(this);
        validator.validate();
    }

    private void loadProduto() {
        final Bundle extras = getIntent().getExtras();
        Long produtoId = (extras != null) ? extras.getLong("produto") : null;

        produto = dao.load(produtoId);
        if (produto != null) {
            showImageInto(produto.getImage(), ivProduto);
            etUrlProduto.setText(produto.getUrl());
            etUrlProduto.setEnabled(false);
            etUrlProduto.setFocusable(false);
            etProduto.setText(produto.getNome());
            spTag.setSelection(selecionarValorSpinner(produto.getTag()));
            etValor.setText(String.valueOf(produto.getValor()));
        } else {
            produto = new Produto();
        }
    }

    private void showImageInto(String image, ImageView into) {
        Picasso.with(this)
                .load(image)
                .noFade()
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.mipmap.ic_error)
                .resize(116, 116)
                .centerCrop()
                .into(into);
    }

    private int selecionarValorSpinner(String valor) {
        if (valor == null || valor.isEmpty()) {
            return 0;
        }

        return ((ArrayAdapter<String>)spTag.getAdapter()).getPosition(valor);
    }

    @Override
    public void onValidationSucceeded() {
        produto.setUrl(etUrlProduto.getText().toString());
        produto.setNome(etProduto.getText().toString());
        produto.setValor(Double.parseDouble(etValor.getText().toString()));
        produto.setTag(spTag.getSelectedItem().toString());

        dao.insertOrReplace(produto);
        voltarTelaAnterior();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void voltarTelaAnterior() {
        setResult(201, new Intent());
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void preencherCampos(com.github.dsaouda.listadesejos.dto.Produto produto) {
        etProduto.setText(produto.getNome());
        etValor.setText(String.valueOf(produto.getValor()));

        this.produto.setImage(produto.getImage());
        showImageInto(produto.getImage(), ivProduto);
    }

    public void setErrorEditTextURL(String s) {
        etUrlProduto.setError(s);
    }

    @OnClick(R.id.ivProduto)
    public void onClickImageView() {
        Toast.makeText(this, R.string.image_fallback, Toast.LENGTH_SHORT).show();
    }
}
