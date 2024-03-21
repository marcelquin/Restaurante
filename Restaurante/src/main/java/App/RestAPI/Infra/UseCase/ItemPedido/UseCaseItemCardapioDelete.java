package App.RestAPI.Infra.UseCase.ItemPedido;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseItemCardapioDelete {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemCardapioDelete(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<CardapioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio)
    { return itemCardapioGateway.ExcluirItemCardapio(idItemCardapio);}

}
