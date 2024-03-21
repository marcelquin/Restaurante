package App.RestAPI.Infra.UseCase.ItemPedido;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseItemCardapioPut {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemCardapioPut(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<CardapioRecord> EditarItemCardapio(@RequestParam Long idItemCardapio, @RequestParam String nome,
                                                             @RequestParam String descrisao)
    { return itemCardapioGateway.EditarItemCardapio(idItemCardapio, nome, descrisao);}

    public ResponseEntity<CardapioRecord> AlterarIngredientes(@RequestParam Long idItemCardapio, @RequestParam Long[] idIngredientes)
    { return itemCardapioGateway.AlterarIngredientes(idItemCardapio, idIngredientes);}


}
