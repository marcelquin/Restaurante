package App.RestAPI.Infra.UseCase.ItemCardapio;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Domain.ItemCardarioRecord;
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

    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio()
    { return itemCardapioGateway.ListarItemCardapio();}
    public ResponseEntity<ItemCardarioRecord> BuscarItemCardapioPorId(@RequestParam Long id)
    { return itemCardapioGateway.BuscarItemCardapioPorId(id);}
}
