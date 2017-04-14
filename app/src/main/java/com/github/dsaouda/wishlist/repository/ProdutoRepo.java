package com.github.dsaouda.wishlist.repository;

import com.github.dsaouda.wishlist.model.Produto;
import com.github.dsaouda.wishlist.model.ProdutoDao;

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
