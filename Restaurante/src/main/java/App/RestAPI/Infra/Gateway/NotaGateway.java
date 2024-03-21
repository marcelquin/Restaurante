package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Domain.ProdutoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NotaGateway {

    public ResponseEntity<List<NotaGateway>> ListarNotas();

    public ResponseEntity<NotaRecord> BuscaNotaPorId(@RequestParam Long id);

    public ResponseEntity<NotaRecord> NovaNota(@RequestParam Long idFornecedor, @RequestBody ProdutoRequest[] produtoRequest,
                                               @RequestParam Double valorNota, @RequestParam String numeroNota);

    public ResponseEntity<NotaRecord> AlterarNota(@RequestParam Long idNota, @RequestParam String numeroNota);

}
