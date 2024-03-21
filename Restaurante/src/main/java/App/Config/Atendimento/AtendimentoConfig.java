package App.Config.Atendimento;

import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAendimentoDelete;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAendimentoGet;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAendimentoPost;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAendimentoPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtendimentoConfig {

    /*@Bean
    UseCaseAendimentoGet useCaseAendimentoGet(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAendimentoGet(atendimentoGateway);}
    @Bean
    UseCaseAendimentoPost useCaseAendimentoPost(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAendimentoPost(atendimentoGateway);}
    @Bean
    UseCaseAendimentoPut useCaseAendimentoPut(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAendimentoPut(atendimentoGateway);}
    @Bean
    UseCaseAendimentoDelete useCaseAendimentoDelete(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAendimentoDelete(atendimentoGateway);}*/
}
