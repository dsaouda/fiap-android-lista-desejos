package com.github.dsaouda.listadesejos.repository;

import com.github.dsaouda.listadesejos.model.Login;
import com.github.dsaouda.listadesejos.model.LoginDao;
import static com.github.dsaouda.listadesejos.model.LoginDao.Properties.*;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class LoginRepo {

    private final LoginDao dao;

    public LoginRepo(LoginDao dao) {
        this.dao = dao;
    }

    public Login defaultLogin() {
        final List<Login> logins = dao.loadAll();
        return logins.size() > 0 ? logins.get(0) : null;
    }

    public Login by(String usuario, String senha) {
        final Query<Login> build = dao.queryBuilder().where(Usuario.eq(usuario), Senha.eq(senha)).build();
        return build.unique();
    }

    public Login by(String usuario) {
        final Query<Login> build = dao.queryBuilder().where(Usuario.eq(usuario)).build();
        return build.unique();
    }

    public List<Login> loadAll() {
        return dao.loadAll();
    }
}
