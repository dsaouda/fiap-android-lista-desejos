# fiap-android-wishlist

O objetivo desse aplicativo é gerar uma lista de produtos desejados. Atualmente o suporte está
apenas aos ecommerce do grupo B2W (submarino.com.br, americanas.com.br, shoptime.com.br, soubarato.com.br)

# Download apk

A apk pode ser baixada no seguinte endereço https://github.com/dsaouda/fiap-android-wishlist/releases/tag/1.1.0

# Exemplo de URLs que podem ser importadas

 - http://www.americanas.com.br/produto/14161370/cozinha-compacta-4-pecas-poquema-julia-c1?condition=NEW
 - https://www.submarino.com.br/produto/15217270/torneira-cozinha-gourmet-monocomando-original-luxo-baixa-de-metal-cromado.?condition=NEW
 - http://www.shoptime.com.br/produto/123100044/fritadeira-sem-oleo-fun-kitchen-fritalight-retro-3l-preta?condition=NEW
 - http://www.soubarato.com.br/produto/125653312/usado-smart-tv-led-55-semp-toshiba-le55l7400-ultra-hd-4k-com-conversor-digital-integrado-3-hdmi-2-usb-wi-fi-grava-programas

# Dependências

Para realização do projeto foram utilizados alguma bibliotecas:

 - org.greenrobot:greendao:3.2.2 para ORM
 - com.android.support:recyclerview-v7:25.2.0 para montar a lista de produtos
 - com.squareup.retrofit2:retrofit:2.2.0 para realizar consultas a api externas
 - com.google.code.gson:gson:2.8.0 para converter um json em uma classe java
 - com.jakewharton:butterknife:8.5.1 para realização de bind entre os campos de um formulário
 - com.mobsandgeeks:android-saripaar para validação dos campos via annotation
 - com.squareup.picasso:picasso:2.5.2 para exibição de fotos da web com mais facilidade
 - com.toptoche.searchablespinner:searchablespinnerlibrary:1.3.1 para fazer um spinner com busca (select com busca)
 - com.google.dagger:dagger:2.10 para realização injeção de dependência


