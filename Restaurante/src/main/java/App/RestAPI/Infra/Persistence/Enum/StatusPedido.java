package App.RestAPI.Infra.Persistence.Enum;

public enum StatusPedido {
        COLETADO, //reserva ingrediente
        EM_PROCESSO,
        PRONTO,
        ENTREGUE_MESA,
        FINALIZADO,
        CANCELADO // ANTES DE EM_PROCESSO RETORNA A QUANTIDADE / C(R) -> EP-> ->
}
