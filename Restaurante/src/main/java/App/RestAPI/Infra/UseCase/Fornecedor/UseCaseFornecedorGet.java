package App.RestAPI.Infra.UseCase.Fornecedor;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Gateway.FornecedorGateway;
import App.RestAPI.Infra.Persistence.Entity.FornecedorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseFornecedorGet {

    private final FornecedorGateway fornecedorGateway;

    public UseCaseFornecedorGet(FornecedorGateway fornecedorGateway) {
        this.fornecedorGateway = fornecedorGateway;
    }

    public ResponseEntity<List<FornecedorEntity>> listarFornecedor()
    { return fornecedorGateway.listarFornecedor();}

    public ResponseEntity<FornecedorRecord> BuscarFornecedorPorId(@RequestParam Long id)
    { return fornecedorGateway.BuscarFornecedorPorId(id);}
}
