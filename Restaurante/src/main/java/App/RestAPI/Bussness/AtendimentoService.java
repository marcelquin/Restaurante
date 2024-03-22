package App.RestAPI.Bussness;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Exceptions.NullargumentsException;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import App.RestAPI.Infra.Persistence.Enum.StatusPedido;
import App.RestAPI.Infra.Persistence.Repository.AtendimentoRepository;
import App.RestAPI.Infra.Persistence.Repository.IngredienteRepository;
import App.RestAPI.Infra.Persistence.Repository.ItemCardapioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtendimentoService implements AtendimentoGateway {

    private final AtendimentoRepository atendimentoRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final IngredienteRepository ingredienteRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ItemCardapioRepository itemCardapioRepository, IngredienteRepository ingredienteRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.ingredienteRepository = ingredienteRepository;
    }


    @Override
    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos()
    {
        try
        {
            return new ResponseEntity<>(atendimentoRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException());
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(Long[] idItemCardapio, Long mesa)
    {
        try
        {
           if(idItemCardapio != null && mesa != null)
           {
               List<ItemCardapioEntity> itemCardapioEntityList = new ArrayList<>();
               List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
               for(Long id : idItemCardapio)
               {
                   ItemCardapioEntity itemCardapio = itemCardapioRepository.findById(id).orElseThrow(()->
                           new EntityNotFoundException());
                   itemCardapioEntityList.add(itemCardapio);
                   ingredienteEntityList.addAll(itemCardapio.getIngredientes());
               }
               Double valorPratofinal = 0.0;
               for (ItemCardapioEntity itemCardapio : itemCardapioEntityList)
               {
                   valorPratofinal += itemCardapio.getValor();
               }
               for(IngredienteEntity ingrediente : ingredienteEntityList)
               {
                   ingrediente.setQuantidade(ingrediente.getQuantidade()-1);
                   ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada()+1);
               }
               AtendimentoEntity entity = new AtendimentoEntity();
               entity.setMesa(mesa);
               entity.setValor(valorPratofinal);
               entity.setTimeStamp(LocalDateTime.now());
               entity.setStatusPedido(StatusPedido.COLETADO);
               entity.setItemsCardapio(itemCardapioEntityList);
               List<String> pedidos = new ArrayList<>();
               entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
               AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(Long idAtendimento, Long[] idItemCardapio)
    {
        try
        {
            if(idAtendimento != null && idItemCardapio != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                List<ItemCardapioEntity> itemCardapioEntityList = new ArrayList<>();
                List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
                for(Long id : idItemCardapio)
                {
                    ItemCardapioEntity item = itemCardapioRepository.findById(id).orElseThrow(()->
                            new EntityNotFoundException());
                    itemCardapioEntityList.add(item);
                }
                    Double valorPratofinal = 0.0;
                for (ItemCardapioEntity itemCardapio : itemCardapioEntityList)
                {
                    valorPratofinal += itemCardapio.getValor();
                    ingredienteEntityList.addAll(itemCardapio.getIngredientes());
                }
                for(IngredienteEntity ingrediente: ingredienteEntityList)
                {
                    ingrediente.setQuantidade(ingrediente.getQuantidade() -1);
                    ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() +1);
                }
                entity.setValor(entity.getValor()+valorPratofinal);
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> IniciarPedido(Long idAtendimento)
    {
        try
        {
            if(idAtendimento != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setStatusPedido(StatusPedido.EM_PROCESSO);
                List<ItemCardapioEntity>itemCardapioEntityList = new ArrayList<>();
                List<IngredienteEntity>ingredienteEntityList = new ArrayList<>();
                itemCardapioEntityList.addAll(entity.getItemsCardapio());
                for(ItemCardapioEntity item : itemCardapioEntityList)
                {
                    ingredienteEntityList.addAll(item.getIngredientes());
                }
                for(IngredienteEntity ingrediente : ingredienteEntityList)
                {
                    ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() -1);
                }
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> PedidoPronto(Long idAtendimento)
    {
        try
        {
            if(idAtendimento != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setStatusPedido(StatusPedido.PRONTO);
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(Long idAtendimento)
    {
        try
        {
            if(idAtendimento != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setStatusPedido(StatusPedido.ENTREGUE_MESA);
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(Long idAtendimento)
    {
        try
        {
            if(idAtendimento != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                entity.setStatusPedido(StatusPedido.FINALIZADO);
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> CancelarPedido(Long idAtendimento)
    {
        try
        {
            if(idAtendimento != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                if(entity.getStatusPedido() == StatusPedido.COLETADO)
                {
                    List<ItemCardapioEntity>itemCardapioEntityList = new ArrayList<>();
                    List<IngredienteEntity>ingredienteEntityList = new ArrayList<>();
                    itemCardapioEntityList.addAll(entity.getItemsCardapio());
                    for(ItemCardapioEntity item : itemCardapioEntityList)
                    {
                        ingredienteEntityList.addAll(item.getIngredientes());
                    }
                    for(IngredienteEntity ingrediente : ingredienteEntityList)
                    {
                        ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() -1);
                        ingrediente.setQuantidade(ingrediente.getQuantidade() + 1);
                    }
                }
                entity.setStatusPedido(StatusPedido.CANCELADO);
                List<String> pedidos = new ArrayList<>();
                entity.getItemsCardapio().forEach(item -> pedidos.add(item.getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(),pedidos, entity.getValor(), entity.getStatusPedido());
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

    @Override
    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(Long id)
    {
        try
        {
           if(id != null)
           {
               if(atendimentoRepository.existsById(id))
               {
                   atendimentoRepository.deleteById(id);
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
