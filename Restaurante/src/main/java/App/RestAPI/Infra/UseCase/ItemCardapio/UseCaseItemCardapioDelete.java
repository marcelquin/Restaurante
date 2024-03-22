package App.RestAPI.Infra.UseCase.ItemCardapio;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseItemCardapioDelete {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemCardapioDelete(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<ItemCardarioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio)
    { return itemCardapioGateway.ExcluirItemCardapio(idItemCardapio);}

}
