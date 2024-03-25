package App.Config.Atendimento;

import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoDelete;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoGet;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPost;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtendimentoConfig {

    @Bean
    UseCaseAtendimentoGet useCaseAtendimentoGet(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAtendimentoGet(atendimentoGateway);}
    @Bean
    UseCaseAtendimentoPost useCaseAtendimentoPost(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAtendimentoPost(atendimentoGateway);}
    @Bean
    UseCaseAtendimentoPut useCaseAtendimentoPut(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAtendimentoPut(atendimentoGateway);}
    @Bean
    UseCaseAtendimentoDelete useCaseAtendimentoDelete(AtendimentoGateway atendimentoGateway)
    { return new UseCaseAtendimentoDelete(atendimentoGateway);}
}
