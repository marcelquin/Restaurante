package App.RestAPI.Infra.UseCase.Fornecedor;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Gateway.FornecedorGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseFornecedorPut {

    private final FornecedorGateway fornecedorGateway;

    public UseCaseFornecedorPut(FornecedorGateway fornecedorGateway) {
        this.fornecedorGateway = fornecedorGateway;
    }

    public ResponseEntity<FornecedorRecord> AlterarDados(@RequestParam Long idFornecedor, String nome, @RequestParam String razaoSocial,
                                                         @RequestParam Long cnpj)
    { return fornecedorGateway.AlterarDados(idFornecedor, nome, razaoSocial, cnpj);}

    public ResponseEntity<FornecedorRecord> AlterarEndereco(@RequestParam Long idFornecedor,@RequestParam String Logradouro, @RequestParam String numero,
                                                            @RequestParam String bairro, @RequestParam String complemento,
                                                            @RequestParam Long cep, @RequestParam String cidade,
                                                            @RequestParam String Estado )
    { return fornecedorGateway.AlterarEndereco(idFornecedor, Logradouro, numero, bairro, complemento, cep, cidade, Estado);}

    public ResponseEntity<FornecedorRecord> AlterarContato(@RequestParam Long idFornecedor, @RequestParam String email,
                                                           @RequestParam Long telefone, @RequestParam Long telefone2)
    { return fornecedorGateway.AlterarContato(idFornecedor, email, telefone, telefone2);}

}
