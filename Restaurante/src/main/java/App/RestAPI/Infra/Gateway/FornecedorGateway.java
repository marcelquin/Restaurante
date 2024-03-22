package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Persistence.Entity.FornecedorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface FornecedorGateway{

    public ResponseEntity<List<FornecedorEntity>> listarFornecedor();

    public ResponseEntity<FornecedorRecord> BuscarFornecedorPorId(@RequestParam Long id);
    public ResponseEntity<FornecedorRecord> NovoFonecedor(@RequestParam String nome, @RequestParam String razaoSocial,
                                                          @RequestParam Long cnpj, @RequestParam String areaAtuacao, @RequestParam LocalDate dataInicioContrato,
                                                          @RequestParam String Logradouro, @RequestParam String numero,
                                                          @RequestParam String bairro, @RequestParam String complemento,
                                                          @RequestParam Long cep, @RequestParam String cidade,
                                                          @RequestParam String Estado, @RequestParam String email,
                                                          @RequestParam Long telefone);

    public ResponseEntity<FornecedorRecord> AlterarDados(@RequestParam Long idFornecedor, String nome, @RequestParam String razaoSocial,
                                                           @RequestParam Long cnpj);

    public ResponseEntity<FornecedorRecord> AlterarEndereco(@RequestParam Long idFornecedor,@RequestParam String Logradouro, @RequestParam String numero,
                                                            @RequestParam String bairro, @RequestParam String complemento,
                                                            @RequestParam Long cep, @RequestParam String cidade,
                                                            @RequestParam String Estado );

    public ResponseEntity<FornecedorRecord> AlterarContato(@RequestParam Long idFornecedor, @RequestParam String email,
                                                           @RequestParam Long telefone);


    public ResponseEntity<FornecedorRecord> DeletarFornecedorPorId(@RequestParam Long id);
}
