package br.ifg.urt.api_carrinho.repository;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
