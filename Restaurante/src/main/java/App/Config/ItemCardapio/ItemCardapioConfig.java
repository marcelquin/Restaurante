package App.Config.ItemCardapio;

import App.RestAPI.Infra.Gateway.ItemCardapioGateway;
import App.RestAPI.Infra.UseCase.ItemPedido.UseCaseItemCardapioDelete;
import App.RestAPI.Infra.UseCase.ItemPedido.UseCaseItemCardapioPost;
import App.RestAPI.Infra.UseCase.ItemPedido.UseCaseItemCardapioPut;
import App.RestAPI.Infra.UseCase.ItemPedido.UseCaseItemPedidoGet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {
    /*@Bean
    UseCaseItemCardapioDelete useCaseItemCardapioDelete(ItemCardapioGateway itemCardapioGateway)
    { return new UseCaseItemCardapioDelete(itemCardapioGateway);}
    @Bean
    UseCaseItemCardapioPut useCaseItemCardapioPut(ItemCardapioGateway itemCardapioGateway)
    { return new UseCaseItemCardapioPut(itemCardapioGateway);}
    @Bean
    UseCaseItemCardapioPost useCaseItemCardapioPost(ItemCardapioGateway itemCardapioGateway)
    { return new UseCaseItemCardapioPost(itemCardapioGateway);}
    @Bean@Bean
    UseCaseItemPedidoGet useCaseItemPedidoGet(ItemCardapioGateway itemCardapioGateway)
    { return new UseCaseItemPedidoGet(itemCardapioGateway);}*/
}
