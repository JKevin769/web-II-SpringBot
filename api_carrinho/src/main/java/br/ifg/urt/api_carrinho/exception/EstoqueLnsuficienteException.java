package br.ifg.urt.api_carrinho.exception;

public class EstoqueLnsuficienteException extends RuntimeException {

//    public EstoqueLnsuficienteException(String message) {
//        super(message);
//    }
//
//    public EstoqueLnsuficienteException(Long produtoId, Integer quantidadeDisponivel, Integer quantidadeSolicitada) {
//        super(String.format(
//                "Estoque insuficiente para o produto %d. Disponível: %d, solicitado: %d",
//                produtoId,
//                quantidadeDisponivel,
//                quantidadeSolicitada
//        ));
//    }

    public EstoqueLnsuficienteException(String message) {
        super(message);
    }

}