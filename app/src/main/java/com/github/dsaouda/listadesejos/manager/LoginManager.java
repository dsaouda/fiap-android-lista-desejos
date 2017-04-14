package com.github.dsaouda.listadesejos.manager;

import com.github.dsaouda.listadesejos.model.Login;
import com.github.dsaouda.listadesejos.model.LoginDao;
import com.github.dsaouda.listadesejos.repository.LoginRepo;

public class LoginManager {

    private final LoginDao dao;
    private final LoginRepo repo;

    public LoginManager(LoginDao dao, LoginRepo repo) {
        this.dao = dao;
        this.repo = repo;
    }

    public void save(Login login) {

        if (login.getId() != null && login.getId() > 0) {
            dao.update(login);
            return ;
        }

        if (repo.by(login.getUsuario()) == null) {
            dao.insert(login);
        }
    }

    public LoginRepo getRepo() {
        return repo;
    }

}
