package App.RestAPI.Infra.UseCase.ItemCardapio;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseItemCardapioPost {

    private final ItemCardapioGateway itemCardapioGateway;

    public UseCaseItemCardapioPost(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ResponseEntity<ItemCardarioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                               @RequestParam String descrisao,  @RequestParam Double porcentagemproducao)
    { return itemCardapioGateway.NovoItemCardapio(idIngredientes, nome, descrisao, porcentagemproducao);}
}
