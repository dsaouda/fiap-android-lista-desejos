package com.github.dsaouda.listadesejos;

import android.app.Application;

import com.github.dsaouda.listadesejos.dagger.DaggerDefaultComponent;
import com.github.dsaouda.listadesejos.dagger.DefaultComponent;
import com.github.dsaouda.listadesejos.dagger.GreenDaoModule;
import com.github.dsaouda.listadesejos.model.DaoMaster;
import com.github.dsaouda.listadesejos.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static final String DB = "lista_de_desejos";
    private DaoSession daoSession;
    private static DefaultComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        initDagger();
    }

    private void initDagger() {
        component = DaggerDefaultComponent
                .builder()
                .greenDaoModule(new GreenDaoModule(daoSession))
                .build();

    }

    public static DefaultComponent getComponent() {
        return component;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}