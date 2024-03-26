package App.RestAPI.Bussness;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Exceptions.IllegalValueException;
import App.RestAPI.Infra.Exceptions.NullargumentsException;
import App.RestAPI.Infra.Gateway.NotaGateway;
import App.RestAPI.Infra.Persistence.Entity.FornecedorEntity;
import App.RestAPI.Infra.Persistence.Entity.NotaEntity;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.Persistence.Repository.FornecedorRepository;
import App.RestAPI.Infra.Persistence.Repository.NotaRepository;
import App.RestAPI.Infra.Persistence.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotaService implements NotaGateway {

    private final NotaRepository notaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    private DecimalFormat df= new DecimalFormat("#,####.##");

    public NotaService(NotaRepository notaRepository, FornecedorRepository fornecedorRepository, ProdutoRepository produtoRepository) {
        this.notaRepository = notaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
    }


    @Override
    public ResponseEntity<List<NotaEntity>> ListarNotas()
    {
        try
        {
            return new ResponseEntity<>(notaRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<NotaRecord> BuscaNotaPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                NotaEntity entity = notaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
                NotaRecord response = new NotaRecord(entity.getFornecedor().getNome(), entity.getFornecedor().getRazao_Social(),
                                                     entity.getFornecedor().getCnpj(),entity.getNumeroNota(), df.format(entity.getValorNota()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<NotaRecord> NovaNota(Long idFornecedor, Double valorNota, String numeroNota)
    {
        try
        {
            if(idFornecedor != null && valorNota != null && numeroNota != null)
            {
                FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor).orElseThrow(()-> new EntityNotFoundException());
                NotaEntity entity = new NotaEntity();
                entity.setFornecedor(fornecedor);
                entity.setValorNota(valorNota);
                entity.setNumeroNota(numeroNota);
                entity.setValorFaturado(0.0);
                entity.setTimeStamp(LocalDateTime.now());
                notaRepository.save(entity);
                NotaRecord response = new NotaRecord(entity.getFornecedor().getNome(), entity.getFornecedor().getRazao_Social(),
                                entity.getFornecedor().getCnpj(),entity.getNumeroNota(), df.format(entity.getValorNota()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{throw new NullargumentsException();}

        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<NotaRecord> AlterarNota(Long idNota, String numeroNota)
    {
        try
        {
            if(idNota != null && numeroNota != null)
            {
                NotaEntity entity = notaRepository.findById(idNota).orElseThrow(()-> new EntityNotFoundException());
                entity.setNumeroNota(numeroNota);
                List<String> produtos = new ArrayList<>();
                NotaRecord response = new NotaRecord(entity.getFornecedor().getNome(), entity.getFornecedor().getRazao_Social(),
                        entity.getFornecedor().getCnpj(),entity.getNumeroNota(), df.format(entity.getValorNota()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
