package App.RestAPI.Infra.UseCase.ItemPedido;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseItemPedidoGet {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemPedidoGet(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<List<ItemCardapioEntity>> ListarPratos()
    { return itemCardapioGateway.ListarPratos();}
    public ResponseEntity<CardapioRecord> BuscarItemCardapioPorId(@RequestParam Long id)
    { return itemCardapioGateway.BuscarItemCardapioPorId(id);}
}
