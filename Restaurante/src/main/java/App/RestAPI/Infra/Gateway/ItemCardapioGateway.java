package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemCardapioGateway {

    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio();
    public ResponseEntity<ItemCardarioRecord> BuscarItemCardapioPorId(@RequestParam Long id);
    public ResponseEntity<ItemCardarioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                    @RequestParam String descrisao, @RequestParam Double porcentagemproducao);

    public ResponseEntity<ItemCardarioRecord> EditarItemCardapio(@RequestParam Long idItemCardapio, @RequestParam String nome,
                                                      @RequestParam String descrisao);

    public ResponseEntity<ItemCardarioRecord> AlterarIngredientes(@RequestParam Long idItemCardapio, @RequestParam Long[] idIngredientes);
    public ResponseEntity<ItemCardarioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio);


}
