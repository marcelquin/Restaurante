package App.RestAPI.Bussness;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Exceptions.EntityNotFoundException;
import App.RestAPI.Infra.Exceptions.NullargumentsException;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import App.RestAPI.Infra.Persistence.Entity.PedidoEntity;
import App.RestAPI.Infra.Persistence.Enum.StatusPedido;
import App.RestAPI.Infra.Persistence.Repository.AtendimentoRepository;
import App.RestAPI.Infra.Persistence.Repository.IngredienteRepository;
import App.RestAPI.Infra.Persistence.Repository.ItemCardapioRepository;
import App.RestAPI.Infra.Persistence.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AtendimentoService implements AtendimentoGateway {

    private final AtendimentoRepository atendimentoRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PedidoRepository pedidoRepository;

    private DecimalFormat df= new DecimalFormat("#,####.##");

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ItemCardapioRepository itemCardapioRepository, IngredienteRepository ingredienteRepository, PedidoRepository pedidoRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.pedidoRepository = pedidoRepository;
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
                entity.getItems().forEach(item -> pedidos.add(item.getItemCardapio().getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),pedidos, df.format(entity.getValor()), entity.getStatusPedido());
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
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(Long mesa, Long numeroPessoas)
    {
        try
        {
           if(mesa != null && numeroPessoas != null)
           {
               AtendimentoEntity entity = new AtendimentoEntity();
               entity.setNumeroPessoas(numeroPessoas);
               entity.setMesa(mesa);
               entity.setTimeStamp(LocalDateTime.now());
               entity.setValor(0.0);
               atendimentoRepository.save(entity);
               List<String> pedidos = new ArrayList<>();
               entity.getItems().forEach(item -> pedidos.add(item.getItemCardapio().getNome()));
               AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),pedidos, df.format(entity.getValor()), entity.getStatusPedido());
               return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(Long idAtendimento, Long idItemCardapio, Double quantidade)
    {
        try
        {
            if(idAtendimento != null && idItemCardapio != null && quantidade != null)
            {
                AtendimentoEntity entity = atendimentoRepository.findById(idAtendimento).orElseThrow(()->
                        new EntityNotFoundException());
                Double valorPedidos = 0.0;
                ItemCardapioEntity itemCardapio = itemCardapioRepository.findById(idItemCardapio).orElseThrow(()->
                        new EntityNotFoundException());
                PedidoEntity pedido = new PedidoEntity();
                pedido.setItemCardapio(itemCardapio);
                pedido.setQuantidade(quantidade);
                valorPedidos = itemCardapio.getValor() * quantidade;
                pedidoRepository.save(pedido);
                List<PedidoEntity> pedidos = new ArrayList<>();
                pedidos.add(pedido);
                entity.getItems().addAll(pedidos);
                entity.setStatusPedido(StatusPedido.COLETADO);
                entity.setValor(entity.getValor() + valorPedidos);
                //
                List<ItemCardapioEntity> itemCardapioEntityList = new ArrayList<>();
                List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
                entity.getItems().forEach(item -> itemCardapioEntityList.add(item.getItemCardapio()));
                for(ItemCardapioEntity itemC : itemCardapioEntityList)
                {
                    ingredienteEntityList.addAll(itemC.getIngredientes());
                }
                for(IngredienteEntity ingrediente : ingredienteEntityList)
                {
                    ingrediente.setQuantidade(ingrediente.getQuantidade() - quantidade );
                    ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() + quantidade);
                }
                //
                List<String> itemPedidos = new ArrayList<>();
                entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
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
                if(entity.getStatusPedido() == StatusPedido.COLETADO &&
                   entity.getStatusPedido() != StatusPedido.EM_PROCESSO &&
                   entity.getStatusPedido() != StatusPedido.PRONTO &&
                   entity.getStatusPedido() != StatusPedido.ENTREGUE_MESA &&
                   entity.getStatusPedido() != StatusPedido.FINALIZADO &&
                   entity.getStatusPedido() != StatusPedido.CANCELADO)
                {
                    entity.setStatusPedido(StatusPedido.EM_PROCESSO);
                    //
                    List<ItemCardapioEntity> itemCardapioEntityList = new ArrayList<>();
                    List<IngredienteEntity> ingredienteEntityList = new ArrayList<>();
                    Double numeroPratos = 0.0;
                    for(PedidoEntity pedido : entity.getItems())
                    {
                        numeroPratos += pedido.getQuantidade();
                    }
                    entity.getItems().forEach(item -> itemCardapioEntityList.add(item.getItemCardapio()));
                    for(ItemCardapioEntity itemC : itemCardapioEntityList)
                    {
                        ingredienteEntityList.addAll(itemC.getIngredientes());
                    }
                    for(IngredienteEntity ingrediente : ingredienteEntityList)
                    {
                        ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() + numeroPratos);
                    }
                    //
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
                if(entity.getStatusPedido() != StatusPedido.COLETADO &&
                        entity.getStatusPedido() == StatusPedido.EM_PROCESSO &&
                        entity.getStatusPedido() != StatusPedido.PRONTO &&
                        entity.getStatusPedido() != StatusPedido.ENTREGUE_MESA &&
                        entity.getStatusPedido() != StatusPedido.FINALIZADO &&
                        entity.getStatusPedido() != StatusPedido.CANCELADO)
                {
                    entity.setStatusPedido(StatusPedido.PRONTO);
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
                if(entity.getStatusPedido() != StatusPedido.COLETADO &&
                        entity.getStatusPedido() != StatusPedido.EM_PROCESSO &&
                        entity.getStatusPedido() == StatusPedido.PRONTO &&
                        entity.getStatusPedido() != StatusPedido.ENTREGUE_MESA &&
                        entity.getStatusPedido() != StatusPedido.FINALIZADO &&
                        entity.getStatusPedido() != StatusPedido.CANCELADO)
                {
                    entity.setStatusPedido(StatusPedido.ENTREGUE_MESA);
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
                if(entity.getStatusPedido() != StatusPedido.COLETADO &&
                        entity.getStatusPedido() != StatusPedido.EM_PROCESSO &&
                        entity.getStatusPedido() != StatusPedido.PRONTO &&
                        entity.getStatusPedido() == StatusPedido.ENTREGUE_MESA &&
                        entity.getStatusPedido() != StatusPedido.FINALIZADO &&
                        entity.getStatusPedido() != StatusPedido.CANCELADO)
                {
                    entity.setStatusPedido(StatusPedido.FINALIZADO);
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
                    List<PedidoEntity> pedidoEntityList = new ArrayList<>();
                    List<ItemCardapioEntity>itemCardapioEntityList = new ArrayList<>();
                    List<IngredienteEntity>ingredienteEntityList = new ArrayList<>();
                    pedidoEntityList.addAll(entity.getItems());
                    Double numeropratos=0.0;
                    for(PedidoEntity pedido : pedidoEntityList)
                    {
                        itemCardapioEntityList.add(pedido.getItemCardapio());
                        numeropratos =+ pedido.getQuantidade();
                    }
                    for(ItemCardapioEntity itemC : itemCardapioEntityList)
                    {
                        ingredienteEntityList.addAll(itemC.getIngredientes());
                    }
                    for(IngredienteEntity ingrediente :ingredienteEntityList)
                    {
                       ingrediente.setQuantidadeReservada(ingrediente.getQuantidadeReservada() - numeropratos);
                    }
                    entity.setStatusPedido(StatusPedido.CANCELADO);
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {
                    entity.setStatusPedido(StatusPedido.CANCELADO);
                    List<String> itemPedidos = new ArrayList<>();
                    entity.getItems().forEach(item -> itemPedidos.add(item.getItemCardapio().getNome()));
                    AtendimentoRecord response = new AtendimentoRecord(entity.getMesa(), entity.getNumeroPessoas(),itemPedidos, df.format(entity.getValor()), entity.getStatusPedido());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
