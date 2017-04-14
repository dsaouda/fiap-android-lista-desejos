package com.github.dsaouda.wishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.github.dsaouda.wishlist.manager.LoginManager;
import com.github.dsaouda.wishlist.model.Login;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  {

    @Inject
    LoginManager manager;

    @BindView(R.id.tvError)
    TextView tvError;

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.cbKeepConnected)
    CheckBox cbKeepConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        App.getComponent().inject(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btLogin)
    public void login() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        boolean keepConnected = cbKeepConnected.isChecked();

        final Login login = manager.getRepo().by(username, password);

        tvError.setText("");

        if (login != null) {
            login.setManterConectado(keepConnected);
            manager.save(login);

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();

            return ;
        }

        tvError.setText(R.string.error_message);
    }
}

