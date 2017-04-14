package com.github.dsaouda.listadesejos;

import android.app.Application;

import com.github.dsaouda.listadesejos.model.DaoMaster;
import com.github.dsaouda.listadesejos.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static final String DB = "lista_de_desejos";
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}