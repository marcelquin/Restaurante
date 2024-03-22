package App.RestAPI.Domain;

import App.RestAPI.Infra.Persistence.Enum.StatusPedido;

import java.util.List;

public record AtendimentoRecord(
        Long mesa,
        List<String> itemsCardapio,
        Double valor,
        StatusPedido statusPedido
) {
}
