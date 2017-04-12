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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProdutoActivity extends AppCompatActivity implements Validator.ValidationListener {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9991;
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
    private String pathImage;

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

        /*
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
        */

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
    */

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

    private void loadProduto() {
        final Bundle extras = getIntent().getExtras();
        Long produtoId = (extras != null) ? extras.getLong("forceSplash") : null;

        produto = dao.load(produtoId);
        if (produto != null) {
            etProduto.setText(produto.getNome());
            etTag.setText(produto.getTag()+produto.getImage());
            etValor.setText(String.valueOf(produto.getValor()));
            etInformacao.setText(produto.getDescricao());
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (produto == null) {
            produto = new Produto();
        }

        produto.setImage(pathImage);
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
