package App.Config.Ingrediente;

import App.RestAPI.Infra.Gateway.IngredienteGateway;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteDelete;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteGet;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePost;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredienteConfig {

    @Bean
    UseCaseIngredienteGet useCaseIngredienteGet(IngredienteGateway ingredienteGateway)
    { return new UseCaseIngredienteGet(ingredienteGateway);}
    @Bean
    UseCaseIngredientePost useCaseIngredientePost(IngredienteGateway ingredienteGateway)
    { return new UseCaseIngredientePost(ingredienteGateway);}

    @Bean
    UseCaseIngredientePut useCaseIngredientePut(IngredienteGateway ingredienteGateway)
    { return new UseCaseIngredientePut(ingredienteGateway);}
    @Bean
    UseCaseIngredienteDelete useCaseIngredienteDelete(IngredienteGateway ingredienteGateway)
    { return new UseCaseIngredienteDelete(ingredienteGateway);}

}
