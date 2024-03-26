package App.RestAPI.Domain;

import App.RestAPI.Infra.Persistence.Enum.StatusPedido;

import java.util.List;

public record AtendimentoRecord(
        Long mesa,
        Long numeroPessoas,
        List<String> pedidos,
        String valor,
        StatusPedido statusPedido
) {
}
