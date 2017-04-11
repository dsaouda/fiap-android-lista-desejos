package com.github.dsaouda.listadesejos.repository;

import com.github.dsaouda.listadesejos.model.Produto;
import com.github.dsaouda.listadesejos.model.ProdutoDao;

import java.util.List;

public class ProdutoRepo {

    private final ProdutoDao dao;

    public ProdutoRepo(ProdutoDao dao) {
        this.dao = dao;
    }


    public List<Produto> loadAll() {
        return dao.loadAll();
    }
}
