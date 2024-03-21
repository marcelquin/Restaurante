package App.RestAPI.Infra.UseCase.Fornecedor;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Gateway.FornecedorGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseFornecedorDelete {

    private final FornecedorGateway fornecedorGateway;

    public UseCaseFornecedorDelete(FornecedorGateway fornecedorGateway) {
        this.fornecedorGateway = fornecedorGateway;
    }

    public ResponseEntity<FornecedorRecord> DeletarFornecedorPorId(@RequestParam Long id)
    { return fornecedorGateway.DeletarFornecedorPorId(id);}
}
