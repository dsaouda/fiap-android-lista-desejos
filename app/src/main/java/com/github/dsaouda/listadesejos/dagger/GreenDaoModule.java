package com.github.dsaouda.listadesejos.dagger;

import com.github.dsaouda.listadesejos.manager.LoginManager;
import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.LoginDao;
import com.github.dsaouda.listadesejos.repository.LoginRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class GreenDaoModule {

    private final DaoSession daoSession;

    public GreenDaoModule(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Provides
    public LoginDao providerLoginDao() {
        return daoSession.getLoginDao();
    }

    @Provides
    public LoginRepo providerLoginRepo(LoginDao dao) {
        return new LoginRepo(dao);
    }

    @Provides
    public LoginManager providerLoginManager(LoginDao dao, LoginRepo repo) {
        return new LoginManager(dao, repo);
    }
}