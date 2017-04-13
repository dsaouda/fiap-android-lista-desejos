package com.github.dsaouda.listadesejos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.dsaouda.listadesejos.factory.B2WServiceFactory;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.repository.ProdutoRepo;
import com.github.dsaouda.listadesejos.service.B2WService;
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

    @NotEmpty(message = "Campo deve ser preenchido")
    @BindView(R.id.etProduto)
    EditText etProduto;

    @NotEmpty(message = "Campo deve ser preenchido")
    @Digits(message = "Valor inválido. Ex: 100.10", integer = 7, fraction = 2)
    @BindView(R.id.etValor)
    EditText etValor;

    @BindView(R.id.spTag)
    SearchableSpinner spTag;

    @BindView(R.id.ivProduto)
    ImageView ivProduto;

    @NotEmpty(message = "Campo deve ser preenchido")
    @Url(message = "URL não é válida")
    @BindView(R.id.etUrlProduto)
    EditText etUrlProduto;


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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tag_produtos));
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

            Picasso.with(ProdutoActivity.this)
                    .load(produto.getImage())
                    .noFade()
                    .placeholder(R.drawable.ic_menu_camera)
                    .error(R.mipmap.ic_error)
                    .resize(116, 116)
                    .centerCrop()
                    .into(ivProduto);

            etUrlProduto.setText(produto.getUrl());
            etUrlProduto.setEnabled(false);
            etProduto.setText(produto.getNome());
            spTag.setSelection(selecionarValorSpinner(produto.getTag()));
            etValor.setText(String.valueOf(produto.getValor()));
        } else {
            produto = new Produto();
        }
    }

    private int selecionarValorSpinner(String valor) {
        System.out.println(valor);

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

    public void preencherCampos(com.github.dsaouda.listadesejos.dto.Produto produto) {
        etProduto.setText(produto.getNome());
        etValor.setText(String.valueOf(produto.getValor()));

        System.out.println(produto);

        this.produto.setImage(produto.getImage());
        Picasso.with(ProdutoActivity.this)
                .load(produto.getImage())
                .noFade()
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.mipmap.ic_error)
                .resize(116, 116)
                .centerCrop()
                .into(ivProduto);
    }

    public void setErrorEditTextURL(String s) {
        etUrlProduto.setError(s);
    }

    /*
    SELECIONE UMA FOTO INTERNA - REMOVIDO DA V1

    @OnFocusChange(R.id.etUrlFoto)
    public void mudarFotoProduto() {
        String url = etUrlFoto.getText().toString();

        try {
            new URL(url);
        } catch (MalformedURLException e) {
            return ;
        }

        Picasso.with(ProdutoActivity.this)
                .load(url)
                .noFade()
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.mipmap.ic_error)
                .resize(116, 116)
                .centerCrop()
                .into(ivProduto);
    }


    @OnClick(R.id.ivProduto)
    public void selecionarImagem() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


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
            pathImage = getRealPathFromURI(uri);

            Picasso.with(this)
                    .load(new File(pathImage))
                    .noFade()
                    .placeholder(R.drawable.ic_menu_camera)
                    .error(R.drawable.ic_menu_camera)
                    .resize(116, 116)
                    .centerCrop()
                    .into(ivProduto);
        }
    }
    */

    /*
    private String toBase64(Uri uri) {
        File file = new File(getRealPathFromURI(uri));
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }

        return result;
    }
    */
}
