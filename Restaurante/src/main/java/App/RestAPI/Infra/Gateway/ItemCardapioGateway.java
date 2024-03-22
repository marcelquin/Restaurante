package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemCardapioGateway {

    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio();
    public ResponseEntity<CardapioRecord> BuscarItemCardapioPorId(@RequestParam Long id);
    public ResponseEntity<CardapioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                    @RequestParam String descrisao);

    public ResponseEntity<CardapioRecord> EditarItemCardapio(@RequestParam Long idItemCardapio, @RequestParam String nome,
                                                      @RequestParam String descrisao);

    public ResponseEntity<CardapioRecord> AlterarIngredientes(@RequestParam Long idItemCardapio, @RequestParam Long[] idIngredientes);
    public ResponseEntity<CardapioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio);


}
