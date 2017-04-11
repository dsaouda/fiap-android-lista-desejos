package com.github.dsaouda.listadesejos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.repository.ProdutoRepo;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    @BindView(R.id.ivProduto)
    ImageView ivProduto;

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

    @OnClick(R.id.ivProduto)
    public void selecionarImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.picture_chooser)), 300);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri uri = Uri.parse(data.getDataString());
            try {
                InputStream is = getContentResolver().openInputStream(uri);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] bitMapData = new byte[16384];

                while ((nRead = is.read(bitMapData, 0, bitMapData.length)) != -1) {
                    buffer.write(bitMapData, 0, nRead);
                }

                buffer.flush();

                System.out.println(Base64.encodeToString(bitMapData, Base64.DEFAULT));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //byte[] bitMapData = input.toByteArray();



            data.getDataString();


            Picasso.with(this)
                    .load(uri)
                    .noFade()
                    .placeholder(R.drawable.ic_menu_camera)
                    .error(R.drawable.ic_menu_send)
                    .resize(116, 116)
                    .centerCrop()
                    .into(ivProduto);

            //ivProduto.setImageURI(uri);
        }
    }

    private void loadProduto() {
        final Bundle extras = getIntent().getExtras();
        Long produtoId = (extras != null) ? extras.getLong("forceSplash") : null;

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
