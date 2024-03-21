package App.Config.Nota;

import App.RestAPI.Infra.Gateway.NotaGateway;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasGet;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPost;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotaConfig {
    /*@Bean
    UseCaseNotasGet useCaseNotasGet(NotaGateway notaGateway)
    { return new UseCaseNotasGet(notaGateway);}
    @Bean
    UseCaseNotasPost useCaseNotasPost(NotaGateway notaGateway)
    { return new UseCaseNotasPost(notaGateway);}

    @Bean
    UseCaseNotasPut useCaseNotasPut(NotaGateway notaGateway)
    { return new UseCaseNotasPut(notaGateway);}*/
}
