package App.RestAPI.Bussness;

import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import App.RestAPI.Infra.Persistence.Repository.IngredienteRepository;
import App.RestAPI.Infra.Persistence.Repository.ItemCardapioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemCardapioService implements ItemCardapioGateway {

    private final IngredienteRepository ingredienteRepository;

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioService(IngredienteRepository ingredienteRepository, ItemCardapioRepository itemCardapioRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Override
    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio()
    {
        try
        {
            return new ResponseEntity<>(itemCardapioRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
    @Override
    public ResponseEntity<ItemCardarioRecord> BuscarItemCardapioPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                ItemCardapioEntity entity = itemCardapioRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException());
                ItemCardarioRecord response = new ItemCardarioRecord(entity.getNome(), entity.getDescrisao(), entity.getValor());
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
    public ResponseEntity<ItemCardarioRecord> NovoItemCardapio(Long[] idIngredientes, String nome, String descrisao, Double porcentagemproducao)
    {
        try
        {
            if(idIngredientes != null && nome != null && descrisao != null)
            {
                ItemCardapioEntity entity = new ItemCardapioEntity();
                entity.setNome(nome);
                entity.setDescrisao(descrisao);
                List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
                Double valorItemCardapio = 0.0;
                for(Long item : idIngredientes)
                {
                    IngredienteEntity ingrediente = ingredienteRepository.findById(item).orElseThrow(()->
                            new EntityNotFoundException());
                    valorItemCardapio += ingrediente.getProduto().getValor();
                    ingredienteEntityList.add(ingrediente);
                }
                entity.setValor(valorItemCardapio);
                entity.setTimeStamp(LocalDateTime.now());
                ItemCardarioRecord response = new ItemCardarioRecord(entity.getNome(), entity.getDescrisao(), entity.getValor());
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
    public ResponseEntity<ItemCardarioRecord> EditarItemCardapio(Long idItemCardapio, String nome, String descrisao)
    {
        try
        {
            if(idItemCardapio != null && nome != null && descrisao != null)
            {
                ItemCardapioEntity entity = itemCardapioRepository.findById(idItemCardapio).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setNome(nome);
                entity.setDescrisao(descrisao);
                entity.setTimeStamp(LocalDateTime.now());
                ItemCardarioRecord response = new ItemCardarioRecord(entity.getNome(), entity.getDescrisao(), entity.getValor());
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
    public ResponseEntity<ItemCardarioRecord> AlterarIngredientes(Long idItemCardapio, Long[] idIngredientes)
    {
        try
        {
            if(idIngredientes != null && idIngredientes != null)
            {
                ItemCardapioEntity entity = itemCardapioRepository.findById(idItemCardapio).orElseThrow(()->
                        new EntityNotFoundException());
                List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
                for(Long item : idIngredientes)
                {
                    IngredienteEntity ingrediente = ingredienteRepository.findById(item).orElseThrow(()->
                            new EntityNotFoundException());
                    ingredienteEntityList.add(ingrediente);
                }
                entity.setIngredientes(ingredienteEntityList);
                entity.setTimeStamp(LocalDateTime.now());
                ItemCardarioRecord response = new ItemCardarioRecord(entity.getNome(), entity.getDescrisao(), entity.getValor());
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
    public ResponseEntity<ItemCardarioRecord> ExcluirItemCardapio(Long idItemCardapio)
    {
        try
        {
            if(idItemCardapio != null)
            {
                if(itemCardapioRepository.existsById(idItemCardapio))
                {
                    itemCardapioRepository.deleteById(idItemCardapio);
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
