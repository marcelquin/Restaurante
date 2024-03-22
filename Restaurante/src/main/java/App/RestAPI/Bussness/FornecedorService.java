package App.RestAPI.Bussness;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Gateway.FornecedorGateway;
import App.RestAPI.Infra.Persistence.Entity.FornecedorEntity;
import App.RestAPI.Infra.Persistence.Repository.ContatoRepository;
import App.RestAPI.Infra.Persistence.Repository.EnderecoRepository;
import App.RestAPI.Infra.Persistence.Repository.FornecedorRepository;
import App.RestAPI.Infra.Persistence.Util.ContatoEntity;
import App.RestAPI.Infra.Persistence.Util.EnderecoEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FornecedorService implements FornecedorGateway {

    private final FornecedorRepository fornecedorRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, EnderecoRepository enderecoRepository, ContatoRepository contatoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.enderecoRepository = enderecoRepository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    public ResponseEntity<List<FornecedorEntity>> listarFornecedor()
    {
        try
        {
            return new ResponseEntity<>(fornecedorRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<FornecedorRecord> BuscarFornecedorPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                FornecedorEntity entity = fornecedorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());

                FornecedorRecord response = new FornecedorRecord(entity.getNome(), entity.getRazao_Social(), entity.getCnpj(),
                                                                 entity.getAreaAtuacao(),entity.getDataInicioContrato(),entity.getContato().getEmail(),
                                                                 entity.getContato().getTelefone());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<FornecedorRecord> NovoFonecedor(String nome, String razaoSocial, Long cnpj, String areaAtuacao,
                                                          LocalDate dataInicioContrato, String Logradouro, String numero,
                                                          String bairro, String complemento, Long cep, String cidade,
                                                          String Estado, String email, Long telefone)
    {
        try
        {
            if(nome != null && razaoSocial != null && cnpj != null && areaAtuacao != null &&
            dataInicioContrato!= null && Logradouro != null && numero != null && bairro != null &&
            complemento != null && cep != null && cidade != null && Estado != null && email != null && telefone != null)
            {
                FornecedorEntity entity = new FornecedorEntity();
                EnderecoEntity endereco = new EnderecoEntity();
                ContatoEntity contato = new ContatoEntity();
                contato.setEmail(email);
                contato.setTelefone(telefone);
                contato.setTimeStamp(LocalDateTime.now());
                //save
                entity.setContato(contato);
                endereco.setLogradouro(Logradouro);
                endereco.setNumero(numero);
                endereco.setBairro(bairro);
                endereco.setComplemento(complemento);
                endereco.setCep(cep);
                endereco.setCidade(cidade);
                endereco.setEstado(email);
                endereco.setTimeStamp(LocalDateTime.now());
                //save
                entity.setEndereco(endereco);
                entity.setNome(nome);
                entity.setRazao_Social(razaoSocial);
                entity.setCnpj(cnpj);
                entity.setAreaAtuacao(areaAtuacao);
                entity.setDataInicioContrato(dataInicioContrato);
                entity.setTimeStamp(LocalDateTime.now());
                //save
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<FornecedorRecord> AlterarDados(Long idFornecedor, String nome, String razaoSocial, Long cnpj)
    {
        try
        {
            if(idFornecedor != null)
            {
                    FornecedorEntity entity = fornecedorRepository.findById(idFornecedor).orElseThrow(()-> new EntityNotFoundException());
                    entity.setNome(nome);
                    entity.setRazao_Social(razaoSocial);
                    entity.setCnpj(cnpj);
                    FornecedorRecord response = new FornecedorRecord(entity.getNome(), entity.getRazao_Social(), entity.getCnpj(),
                            entity.getAreaAtuacao(),entity.getDataInicioContrato(),entity.getContato().getEmail(),
                            entity.getContato().getTelefone());
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<FornecedorRecord> AlterarEndereco(Long idFornecedor, String Logradouro, String numero, String bairro, String complemento, Long cep, String cidade, String Estado)
    {
        try
        {
            if(idFornecedor != null)
            {
                FornecedorEntity entity = fornecedorRepository.findById(idFornecedor).orElseThrow(()-> new EntityNotFoundException());
                EnderecoEntity endereco = enderecoRepository.findById(entity.getEndereco().getId()).orElseThrow(() -> new EntityNotFoundException());
                endereco.setLogradouro(Logradouro);
                endereco.setNumero(numero);
                endereco.setBairro(bairro);
                endereco.setCep(cep);
                endereco.setCidade(cidade);
                endereco.setEstado(Estado);
                endereco.setComplemento(complemento);
                endereco.setTimeStamp(LocalDateTime.now());
                FornecedorRecord response = new FornecedorRecord(entity.getNome(), entity.getRazao_Social(), entity.getCnpj(),
                        entity.getAreaAtuacao(),entity.getDataInicioContrato(),entity.getContato().getEmail(),
                        entity.getContato().getTelefone());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<FornecedorRecord> AlterarContato(Long idFornecedor, String email, Long telefone)
    {
        try
        {
            if(idFornecedor != null)
            {
                FornecedorEntity entity = fornecedorRepository.findById(idFornecedor).orElseThrow(()-> new EntityNotFoundException());
                ContatoEntity contato = contatoRepository.findById(entity.getContato().getId()).orElseThrow(() -> new EntityNotFoundException());
                contato.setEmail(email);
                contato.setTelefone(telefone);
                contato.setTimeStamp(LocalDateTime.now());
                FornecedorRecord response = new FornecedorRecord(entity.getNome(), entity.getRazao_Social(), entity.getCnpj(),
                        entity.getAreaAtuacao(),entity.getDataInicioContrato(),entity.getContato().getEmail(),
                        entity.getContato().getTelefone());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<FornecedorRecord> DeletarFornecedorPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(fornecedorRepository.existsById(id))
                {
                    fornecedorRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }

            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
