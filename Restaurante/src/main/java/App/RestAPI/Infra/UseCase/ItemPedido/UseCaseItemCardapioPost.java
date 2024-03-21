package App.RestAPI.Infra.UseCase.ItemPedido;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseItemCardapioPost {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemCardapioPost(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<CardapioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                           @RequestParam String descrisao)
    { return itemCardapioGateway.NovoItemCardapio(idIngredientes, nome, descrisao);}
}
