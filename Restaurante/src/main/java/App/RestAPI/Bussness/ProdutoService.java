package App.RestAPI.Bussness;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Exceptions.IllegalValueException;
import App.RestAPI.Infra.Exceptions.NullargumentsException;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.Persistence.Entity.NotaEntity;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;
import App.RestAPI.Infra.Persistence.Repository.NotaRepository;
import App.RestAPI.Infra.Persistence.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final NotaRepository notaRepository;

    private DecimalFormat df= new DecimalFormat("#,####.##");

    public ProdutoService(ProdutoRepository produtoRepository, NotaRepository notaRepository) {
        this.produtoRepository = produtoRepository;
        this.notaRepository = notaRepository;
    }

    @Override
    public ResponseEntity<List<ProdutoEntity>> ListarProdutos()
    {
        try
        {
            return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<ProdutoRecord> BuscarProdutoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                ProdutoEntity entity = produtoRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException());

                ProdutoRecord response = new ProdutoRecord(entity.getNome(), entity.getDescrisao(),
                        entity.getQuantidade(), entity.getUnidadeMedida(), df.format(entity.getValor()));
                return new ResponseEntity<>(response,HttpStatus.OK);
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
    public ResponseEntity<ProdutoRecord> NovoProduto(Long idNota, String nome, String descrisao, Double valorNotaProduto, Double quantidade, UnidadeMedida unidadeMedida,Double porcentegemLucroProduto)
    {
        try
        {
            if(idNota != null && nome != null && descrisao != null && valorNotaProduto != null && quantidade != null && unidadeMedida != null && porcentegemLucroProduto != null)
            {
                NotaEntity nota = notaRepository.findById(idNota).orElseThrow(()->
                        new EntityNotFoundException());
                ProdutoEntity entity = new ProdutoEntity();
                System.out.println("valor nota: "+nota.getValorNota());
                System.out.println("valor faturado: "+nota.getValorFaturado());
                Double notaProduto = valorNotaProduto + nota.getValorFaturado();
                if(notaProduto <= nota.getValorNota())
                {
                    entity.setNome(nome);
                    entity.setDescrisao(descrisao);
                    entity.setQuantidade(quantidade);
                    entity.setUnidadeMedida(unidadeMedida);
                    entity.setTimeStamp(LocalDateTime.now());
                    Double porcentagem = porcentegemLucroProduto /100;
                    Double valorProdutoTotal = valorNotaProduto/quantidade;
                    valorProdutoTotal = (valorProdutoTotal * porcentagem) + valorProdutoTotal;
                    entity.setValor(valorProdutoTotal);
                    nota.setValorFaturado(notaProduto);
                    notaRepository.save(nota);
                    produtoRepository.save(entity);
                    ProdutoRecord response = new ProdutoRecord(entity.getNome(), entity.getDescrisao(),
                            entity.getQuantidade(), entity.getUnidadeMedida(), df.format(entity.getValor()));
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
                else{throw new IllegalValueException();}
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
    public ResponseEntity<ProdutoRecord> AdicioanrEstoque(Long idnota, Long idProduto, Double valorNota, Double quantidade, Double porcentegemLucroProduto)
    {
        try
        {
            if(idnota != null && idProduto != null && quantidade != null && valorNota != null)
            {
                ProdutoEntity entity = produtoRepository.findById(idProduto).orElseThrow(()->
                        new EntityNotFoundException());
                NotaEntity nota = notaRepository.findById(entity.getNotaFiscal().getId()).orElseThrow(()->
                        new EntityNotFoundException());
                Double valorNotaFiscal = nota.getValorNota();
                Double valorNotaFaturado = nota.getValorFaturado();
                Double notaProduto = valorNota + valorNotaFaturado;
                if(notaProduto <= valorNota)
                {
                    entity.setQuantidade(quantidade);
                    entity.setTimeStamp(LocalDateTime.now());
                    Double porcentagem = porcentegemLucroProduto /100;
                    Double valorProdutoTotal = valorNota/quantidade;
                    valorProdutoTotal = (valorProdutoTotal * porcentagem) + valorProdutoTotal;
                    entity.setValor(valorProdutoTotal);
                    nota.setValorFaturado(notaProduto);
                    notaRepository.save(nota);
                    produtoRepository.save(entity);
                    ProdutoRecord response = new ProdutoRecord(entity.getNome(), entity.getDescrisao(),
                            entity.getQuantidade(), entity.getUnidadeMedida(), df.format(entity.getValor()));
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
                else{throw new IllegalValueException();}
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
    public ResponseEntity<ProdutoRecord> AlterarDadosProduto(Long idProduto, String nome, String Descrisao)
    {
        try
        {
            if(idProduto != null)
            {
                ProdutoEntity entity = produtoRepository.findById(idProduto).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setNome(nome);
                entity.setDescrisao(Descrisao);
                ProdutoRecord response = new ProdutoRecord(entity.getNome(), entity.getDescrisao(),
                        entity.getQuantidade(), entity.getUnidadeMedida(), df.format(entity.getValor()));
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else{throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<ProdutoRecord> DeletarProduto(Long idProduto)
    {
        try
        {
            if(idProduto != null)
            {
                if(produtoRepository.existsById(idProduto))
                {
                    produtoRepository.deleteById(idProduto);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
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
