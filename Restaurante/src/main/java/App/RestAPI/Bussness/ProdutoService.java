package App.RestAPI.Bussness;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.Persistence.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
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
                        entity.getQuantidade(), entity.getUnidadeMedida(), entity.getValor());
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
                        entity.getQuantidade(), entity.getUnidadeMedida(), entity.getValor());
            }
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
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
