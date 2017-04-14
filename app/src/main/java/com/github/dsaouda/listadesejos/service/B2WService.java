package com.github.dsaouda.listadesejos.service;

import com.github.dsaouda.listadesejos.dto.Produto;
import com.github.dsaouda.listadesejos.dto.b2w.B2WJson;
import com.github.dsaouda.listadesejos.dto.b2w.Image;
import com.github.dsaouda.listadesejos.dto.b2w.PaymentOptions;
import com.github.dsaouda.listadesejos.dto.b2w.Product;
import com.github.dsaouda.listadesejos.api.B2WSerciceProvider;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

public class B2WService {

    private final B2WSerciceProvider api;

    public B2WService(B2WSerciceProvider api) {
        this.api = api;
    }

    public Produto getProduto(URL url) {
        final String produtoId = extractProdutoId(url);
        return getProduto(produtoId);
    }

    public Produto getProduto(String produtoId) {
        try {
            final Response<B2WJson> response = api.getProduto("itemId:(" + produtoId + ")", 1, "BOLETO").execute();
            final Product p = response.body().getProducts().get(0);
            final PaymentOptions paymentOptions = p.getOffers().get(0).getPaymentOptions();

            return new Produto(
                    p.getName(),
                    p.getImages().get(0).getMedium(),
                    paymentOptions.getBOLETO().getPrice());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractProdutoId(URL url) {
        final Pattern regex = Pattern.compile("/produto/([0-9]*)");
        final Matcher matcher = regex.matcher(url.getPath());
        matcher.find();
        return matcher.group(1);
    }
}
