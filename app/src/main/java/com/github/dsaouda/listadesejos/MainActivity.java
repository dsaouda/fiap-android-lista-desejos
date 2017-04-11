package com.github.dsaouda.listadesejos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.dsaouda.listadesejos.model.DaoSession;
import com.github.dsaouda.listadesejos.model.Login;
import com.github.dsaouda.listadesejos.model.LoginDao;
import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;
import com.github.dsaouda.listadesejos.repository.LoginRepo;
import com.github.dsaouda.listadesejos.repository.ProdutoRepo;
import com.github.dsaouda.listadesejos.view.adapter.ProdutoListaAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DaoSession daoSession;
    private ProdutoDao dao;
    private ProdutoRepo repo;

    RecyclerView rvProdutoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoSession = ((App) getApplication()).getDaoSession();
        dao = daoSession.getProdutoDao();

        repo = new ProdutoRepo(dao);


        rvProdutoLista = (RecyclerView) findViewById(R.id.rvProdutoLista);

        recycleViewEnderecoLista();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // get the note DAO
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        final LoginDao loginDao = daoSession.getLoginDao();

        //Login login = new Login("root","dsaouda");
        //loginDao.insert(login);

        /*
        MockyService service = MockyServiceFactory.create();
        final Call<com.github.dsaouda.listadesejos.dto.Login> login = service.login("58b9b1740f0000b614f09d2f");
        login.enqueue(new Callback<com.github.dsaouda.listadesejos.dto.Login>() {
            @Override
            public void onResponse(Call<com.github.dsaouda.listadesejos.dto.Login> call, Response<com.github.dsaouda.listadesejos.dto.Login> response) {
                System.out.println(response.body().getUsuario());
            }

            @Override
            public void onFailure(Call<com.github.dsaouda.listadesejos.dto.Login> call, Throwable t) {

            }
        });
        */

        /*
        System.out.println("Query 2");
        System.out.println(new LoginRepo(loginDao).by("root", "dsaouda"));
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
                startActivityForResult(intent, 200);
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_CANCELED:
                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
                break;

            case 201:
                Toast.makeText(MainActivity.this, "Produto salvo com sucesso", Toast.LENGTH_LONG).show();
                recycleViewEnderecoLista();
                break;
        }
    }


    private void recycleViewEnderecoLista() {
        final List<Produto> produtos = repo.loadAll();


        //TextView tvSemEndereco = (TextView) findViewById(R.id.tvSemEndereco);

        if (produtos.size() == 0) {
            //tvSemEndereco.setVisibility(View.VISIBLE);
            //rvEnderecoLista.setVisibility(View.INVISIBLE);
        } else {
            //tvSemEndereco.setVisibility(View.INVISIBLE);
            //rvEnderecoLista.setVisibility(View.VISIBLE);
        }

        final ProdutoListaAdapter adapter = new ProdutoListaAdapter(produtos, this, dao);

        rvProdutoLista.setLayoutManager(new LinearLayoutManager(this));
        rvProdutoLista.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        rvProdutoLista.invalidate();

        /*
        //swipe
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Snackbar.make(viewHolder.itemView, "Deletado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                ((EnderecoViewHolder)viewHolder).removerEndereco();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvEnderecoLista);
        */

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            final LoginDao loginDao = daoSession.getLoginDao();
            final LoginRepo loginRepo = new LoginRepo(loginDao);
            final Login login = loginRepo.defaultLogin();

            login.setManterConectado(false);
            loginDao.insertOrReplace(login);

            finish();
            System.exit(0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_produto) {
            // Handle the camera action
        } else if (id == R.id.nav_login) {

        } else if (id == R.id.nav_splashscreen) {

        } else if (id == R.id.nav_sobre) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
