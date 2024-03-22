package App.RestAPI.Domain;

import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;

public record ProdutoRequest(
        String nome,
        String descrisao,
        Double quantidade,
        UnidadeMedida unidadeMedida,
        Double valorNotaProduto


) {
}
