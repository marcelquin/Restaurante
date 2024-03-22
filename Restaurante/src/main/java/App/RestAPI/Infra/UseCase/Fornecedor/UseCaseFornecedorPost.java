package App.RestAPI.Infra.UseCase.Fornecedor;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Gateway.FornecedorGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class UseCaseFornecedorPost {

    private final FornecedorGateway fornecedorGateway;

    public UseCaseFornecedorPost(FornecedorGateway fornecedorGateway) {
        this.fornecedorGateway = fornecedorGateway;
    }

    public ResponseEntity<FornecedorRecord> NovoFonecedor(@RequestParam String nome, @RequestParam String razaoSocial,
                                                          @RequestParam Long cnpj, @RequestParam String areaAtuacao, @RequestParam LocalDate dataInicioContrato,
                                                          @RequestParam String Logradouro, @RequestParam String numero,
                                                          @RequestParam String bairro, @RequestParam String complemento,
                                                          @RequestParam Long cep, @RequestParam String cidade,
                                                          @RequestParam String Estado, @RequestParam String email,
                                                          @RequestParam Long telefone)
    { return fornecedorGateway.NovoFonecedor(nome, razaoSocial, cnpj, areaAtuacao, dataInicioContrato, Logradouro, numero, bairro, complemento, cep, cidade, Estado, email, telefone);}

}
