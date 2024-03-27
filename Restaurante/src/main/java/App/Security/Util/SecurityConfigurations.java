package App.Security.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/diagrama.pdf").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() //hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/atendimento/**").hasRole("ATENDIMENTO")
                        .requestMatchers(HttpMethod.GET, "/atendimento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/atendimento/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.POST, "/atendimento/**").hasRole("ATENDIMENTO")
                        .requestMatchers(HttpMethod.POST, "/atendimento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/AdicionarItemCardapio/").hasRole("ATENDIMENTO")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/AdicionarItemCardapio/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/IniciarPedido/").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/PedidoPronto/").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/PedidoEntregue/").hasRole("ATENDIMENTO")
                        .requestMatchers(HttpMethod.PUT, "/atendimento/PedidoFinalizado/").hasRole("ATENDIMENTO")
                        .requestMatchers(HttpMethod.GET, "/itemCardapio/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.POST, "/itemCardapio/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.PUT, "/itemCardapio/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.GET, "/ingrediente/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.POST, "/ingrediente/**").hasRole("COZINHA")
                        .requestMatchers(HttpMethod.PUT, "/ingrediente/**").hasRole("COZINHA")
                        .anyRequest().hasRole("ADMIN")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
