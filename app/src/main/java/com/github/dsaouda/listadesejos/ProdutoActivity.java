package com.github.dsaouda.listadesejos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.repository.ProdutoRepo;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProdutoActivity extends AppCompatActivity implements Validator.ValidationListener {

    private DaoSession daoSession;
    private ProdutoDao dao;
    private ProdutoRepo repo;

    @NotEmpty(message = "Campo deve ser preenchido")
    @BindView(R.id.etProduto)
    EditText etProduto;

    @NotEmpty(message = "Campo deve ser preenchido")
    @Digits(message = "Valor inválido. Ex: 100.10", integer = 7, fraction = 2)
    @BindView(R.id.etValor)
    EditText etValor;

    @BindView(R.id.etTag)
    EditText etTag;

    @BindView(R.id.etInformacao)
    EditText etInformacao;

    private Validator validator;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        ButterKnife.bind(this);
        validator = new Validator(this);

        daoSession = ((App) getApplication()).getDaoSession();
        dao = daoSession.getProdutoDao();
        repo = new ProdutoRepo(dao);

        loadProduto();
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
            etProduto.setText(produto.getNome());
            etTag.setText(produto.getTag());
            etValor.setText(String.valueOf(produto.getValor()));
            etInformacao.setText(produto.getDescricao());
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (produto == null) {
            produto = new Produto();
        }

        produto.setNome(etProduto.getText().toString());
        produto.setDescricao(etInformacao.getText().toString());
        produto.setValor(Double.parseDouble(etValor.getText().toString()));
        produto.setTag(etTag.getText().toString());

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
}
