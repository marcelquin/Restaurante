package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePost;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioDelete;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioPost;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioPut;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemPedidoGet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itemCardapio")
public class ItemCardapioController {

    private final UseCaseItemPedidoGet itemPedidoGet;
    private final UseCaseItemCardapioPost itemCardapioPost;
    private final UseCaseItemCardapioPut itemCardapioPut;
    private final UseCaseItemCardapioDelete itemCardapioDelete;

    public ItemCardapioController(UseCaseItemPedidoGet itemPedidoGet, UseCaseItemCardapioPost itemCardapioPost, UseCaseItemCardapioPut itemCardapioPut, UseCaseItemCardapioDelete itemCardapioDelete) {
        this.itemPedidoGet = itemPedidoGet;
        this.itemCardapioPost = itemCardapioPost;
        this.itemCardapioPut = itemCardapioPut;
        this.itemCardapioDelete = itemCardapioDelete;
    }

    @GetMapping("/ListarItemCardapio")
    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio()
    { return itemPedidoGet.ListarItemCardapio();}

    @GetMapping("/BuscarItemCardapioPorId")
    public ResponseEntity<ItemCardarioRecord> BuscarItemCardapioPorId(@RequestParam Long id)
    { return itemPedidoGet.BuscarItemCardapioPorId(id);}

    @PostMapping("/NovoItemCardapio")
    public ResponseEntity<ItemCardarioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                               @RequestParam String descrisao, @RequestParam Double porcentagemproducao)
    { return itemCardapioPost.NovoItemCardapio(idIngredientes, nome, descrisao, porcentagemproducao);}
    @PutMapping("/EditarItemCardapio")
    public ResponseEntity<ItemCardarioRecord> EditarItemCardapio(@RequestParam Long idItemCardapio, @RequestParam String nome,
                                                                 @RequestParam String descrisao)
    { return itemCardapioPut.EditarItemCardapio(idItemCardapio, nome, descrisao);}
    @PutMapping("/AlterarIngredientes")
    public ResponseEntity<ItemCardarioRecord> AlterarIngredientes(@RequestParam Long idItemCardapio, @RequestParam Long[] idIngredientes)
    { return itemCardapioPut.AlterarIngredientes(idItemCardapio, idIngredientes);}

    @DeleteMapping("/ExcluirItemCardapio")
    public ResponseEntity<ItemCardarioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio)
    { return itemCardapioDelete.ExcluirItemCardapio(idItemCardapio);}
}
