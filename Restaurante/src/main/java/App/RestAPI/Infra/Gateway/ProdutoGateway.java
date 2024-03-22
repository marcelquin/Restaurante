package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProdutoGateway {

    public ResponseEntity<List<ProdutoEntity>> ListarProdutos();

    public ResponseEntity<ProdutoRecord> BuscarProdutoPorId(@RequestParam Long id);
    public ResponseEntity<ProdutoRecord> AlterarDadosProduto(@RequestParam Long idProduto, @RequestParam String nome,
                                                             @RequestParam String Descrisao);

    public ResponseEntity<ProdutoRecord> DeletarProduto(@RequestParam Long idProduto);
}
