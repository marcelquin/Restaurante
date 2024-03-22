package App.RestAPI.Infra.UseCase.Nota;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Gateway.NotaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseNotasPost {

    private final NotaGateway notaGateway;

    public UseCaseNotasPost(NotaGateway notaGateway) {
        this.notaGateway = notaGateway;
    }

    public ResponseEntity<NotaRecord> NovaNota(@RequestParam Long idFornecedor, @RequestBody ProdutoRequest[] produtoRequest,
                                               @RequestParam Double valorNota, @RequestParam String numeroNota, @RequestParam Double porcentegemLucroProduto)
    { return notaGateway.NovaNota(idFornecedor, produtoRequest, valorNota, numeroNota, porcentegemLucroProduto);}
}
