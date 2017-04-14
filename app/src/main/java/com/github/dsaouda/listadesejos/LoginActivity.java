package com.github.dsaouda.listadesejos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.github.dsaouda.listadesejos.manager.LoginManager;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Login;
import com.github.dsaouda.listadesejos.model.LoginDao;
import com.github.dsaouda.listadesejos.repository.LoginRepo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  {

    private DaoSession daoSession;
    private LoginDao dao;
    private LoginRepo repo;
    private LoginManager manager;

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

        daoSession = ((App) getApplication()).getDaoSession();
        dao = daoSession.getLoginDao();
        repo = new LoginRepo(dao);
        manager = new LoginManager(dao, repo);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btLogin)
    public void login() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        boolean keepConnected = cbKeepConnected.isChecked();

        final Login login = repo.by(username, password);

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

