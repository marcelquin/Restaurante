package App.Config.Fornecedor;

import App.RestAPI.Infra.Gateway.FornecedorGateway;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorDelete;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorGet;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorPost;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FornecedorConfig {
    /*@Bean
    UseCaseFornecedorGet useCaseFornecedorGet(FornecedorGateway fornecedorGateway)
    { return new UseCaseFornecedorGet(fornecedorGateway);}
    @Bean
    UseCaseFornecedorPost useCaseFornecedorPost(FornecedorGateway fornecedorGateway)
    { return new UseCaseFornecedorPost(fornecedorGateway);}
    @Bean
    UseCaseFornecedorPut useCaseFornecedorPut(FornecedorGateway fornecedorGateway)
    { return new UseCaseFornecedorPut(fornecedorGateway);}
    @Bean
    UseCaseFornecedorDelete useCaseFornecedorDelete(FornecedorGateway fornecedorGateway)
    { return new UseCaseFornecedorDelete(fornecedorGateway);}*/

}
