package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoDelete;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoGet;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoPut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private final UseCaseProdutoGet produtoGet;
    private final UseCaseProdutoPut produtoPut;
    private final UseCaseProdutoDelete produtoDelete;

    public ProdutoController(UseCaseProdutoGet produtoGet, UseCaseProdutoPut produtoPut, UseCaseProdutoDelete produtoDelete) {
        this.produtoGet = produtoGet;
        this.produtoPut = produtoPut;
        this.produtoDelete = produtoDelete;
    }
    @GetMapping("/ListarProdutos")
    public ResponseEntity<List<ProdutoEntity>> ListarProdutos()
    { return produtoGet.ListarProdutos();}
    @GetMapping("/BuscarProdutoPorId")
    public ResponseEntity<ProdutoRecord> BuscarProdutoPorId(@RequestParam Long id)
    { return produtoGet.BuscarProdutoPorId(id);}
    @PutMapping("/AlterarDadosProduto")
    public ResponseEntity<ProdutoRecord> AlterarDadosProduto(@RequestParam Long idProduto, @RequestParam String nome,
                                                             @RequestParam String Descrisao)
    { return produtoPut.AlterarDadosProduto(idProduto, nome, Descrisao);}

    @DeleteMapping("/DeletarProduto")
    public ResponseEntity<ProdutoRecord> DeletarProduto(@RequestParam Long idProduto)
    { return produtoDelete.DeletarProduto(idProduto);}
}
