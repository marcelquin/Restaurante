package App.RestAPI.Infra.Gateway;


import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IngredienteGateway {

    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes();

    public ResponseEntity<IngredienteRecord> BuscaIngredientePorId(@RequestParam Long id);
    public ResponseEntity<IngredienteRecord> NovoIngrediente(@RequestParam Long idProduto, @RequestParam String nome, @RequestParam String descrisao,
                                                             @RequestParam Double Quantidade, @RequestParam Double QuantidadeProduto);

    public ResponseEntity<IngredienteRecord> AdicionarIngredienteEstoque(@RequestParam Long idIngrediente, @RequestParam Double Quantidade);



    public ResponseEntity<IngredienteRecord> ALterarIngrediente(@RequestParam Long idIngrediente,@RequestParam Long idProduto, Double Quantidade, @RequestParam Double QuantidadeProduto);
    public ResponseEntity<IngredienteRecord> DeletarIngrediente(@RequestParam Long id);
}
