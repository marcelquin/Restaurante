package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.CardapioRecord;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemCardapioGateway {

    public ResponseEntity<List<ItemCardapioEntity>> ListarPratos();
    public ResponseEntity<CardapioRecord> BuscarItemCardapioPorId();
    public ResponseEntity<CardapioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                    @RequestParam String descrisao);

    public ResponseEntity<CardapioRecord> EditarItemCardapio(@RequestParam Long idPrato, @RequestParam String nome,
                                                      @RequestParam String descrisao);

    public ResponseEntity<CardapioRecord> AlterarIngredientes(@RequestParam Long idPrato, @RequestParam Long[] idIngredientes);
    public ResponseEntity<CardapioRecord> ExcluirItemCardapio(@RequestParam Long idPrato);


}
