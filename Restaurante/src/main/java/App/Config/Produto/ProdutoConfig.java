package App.Config.Produto;

import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoDelete;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoGet;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

   /* @Bean
    UseCaseProdutoGet useCaseProdutoGet(ProdutoGateway produtoGateway)
    { return new UseCaseProdutoGet(produtoGateway);}
    @Bean
    UseCaseProdutoPut useCaseProdutoPut(ProdutoGateway produtoGateway)
    { return new UseCaseProdutoPut(produtoGateway);}
    @Bean
    UseCaseProdutoDelete useCaseProdutoDelete(ProdutoGateway produtoGateway)
    { return new UseCaseProdutoDelete(produtoGateway);}*/

}
