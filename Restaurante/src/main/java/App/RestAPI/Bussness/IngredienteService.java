package App.RestAPI.Bussness;


import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Exceptions.InsufficientQuantityException;
import App.RestAPI.Infra.Exceptions.NullargumentsException;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.Persistence.Repository.IngredienteRepository;
import App.RestAPI.Infra.Persistence.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngredienteService implements IngredienteGateway {

    private final ProdutoRepository produtoRepository;
    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(ProdutoRepository produtoRepository, IngredienteRepository ingredienteRepository) {
        this.produtoRepository = produtoRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes()
    {
        try
        {
            return new ResponseEntity<>(ingredienteRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }


    @Override
    public ResponseEntity<IngredienteRecord> BuscaIngredientePorId(Long id)
    {
        try
        {
            if (id != null)
            {
                IngredienteEntity entity = ingredienteRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException());
                IngredienteRecord response = new IngredienteRecord(entity.getNome(), entity.getDescrisao(),
                                                                    entity.getQuantidade(), entity.getQuantidadeReservada());
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
    public ResponseEntity<IngredienteRecord> NovoIngrediente(Long idProduto, String nome, String descrisao, Double Quantidade, Double QuantidadeProduto)
    {
        try
        {
            if (idProduto != null && Quantidade != null)
            {
                ProdutoEntity produto = produtoRepository.findById(idProduto).orElseThrow(()->
                        new EntityNotFoundException());
                Double quantidade = Quantidade * QuantidadeProduto;
                if(quantidade < produto.getQuantidade())
                {
                    produto.setQuantidade(produto.getQuantidade()-quantidade);
                    IngredienteEntity entity = new IngredienteEntity();
                    entity.setNome(nome);
                    entity.setDescrisao(descrisao);
                    entity.setProduto(produto);
                    entity.setQuantidade(quantidade);
                    entity.setQuantidadeProduto(QuantidadeProduto);
                    entity.setTimeStamp(LocalDateTime.now());
                    IngredienteRecord response = new IngredienteRecord(entity.getNome(), entity.getDescrisao(),
                            entity.getQuantidade(), entity.getQuantidadeReservada());
                }
                else{throw new InsufficientQuantityException();}
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
    public ResponseEntity<IngredienteRecord> ALterarIngrediente(Long idIngrediente, Long idProduto, Double Quantidade, Double QuantidadeProduto)
    {
        try
        {
            if (idIngrediente != null && idProduto!= null && Quantidade != null && QuantidadeProduto != null)
            {
                IngredienteEntity entity = ingredienteRepository.findById(idIngrediente).orElseThrow(()->
                        new EntityNotFoundException());
                ProdutoEntity produto = produtoRepository.findById(entity.getProduto().getId()).orElseThrow(()->
                        new EntityNotFoundException());
                Double quantidade = Quantidade * QuantidadeProduto;
                if(quantidade < produto.getQuantidade())
                {
                    produto.setQuantidade(produto.getQuantidade()-quantidade);
                    entity.setProduto(produto);
                    entity.setQuantidade(quantidade);
                    entity.setQuantidadeProduto(QuantidadeProduto);
                    entity.setTimeStamp(LocalDateTime.now());
                    IngredienteRecord response = new IngredienteRecord(entity.getNome(), entity.getDescrisao(),
                            entity.getQuantidade(), entity.getQuantidadeReservada());
                }
                else{throw new InsufficientQuantityException();}

                IngredienteRecord response = new IngredienteRecord(entity.getNome(), entity.getDescrisao(),
                        entity.getQuantidade(), entity.getQuantidadeReservada());
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
    public ResponseEntity<IngredienteRecord> DeletarIngrediente(Long id)
    {
        try
        {
            if (id != null)
            {
                if(ingredienteRepository.existsById(id))
                {
                    ingredienteRepository.deleteById(id);
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
