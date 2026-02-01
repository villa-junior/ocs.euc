package br.edu.ifba.ocs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;


@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. REGRAS RESTRITIVAS (ADMIN/PESQUISADOR)
                        .requestMatchers(
                                "/obras/cadastrar/**",
                                "/obras/editar/**",
                                "/obras/excluir/**",
                                "/admin/**",
                                "/autoras/cadastrar/**",
                                "/autoras/editar/**",
                                "/autoras/excluir/**",
                                "/legislacoes/cadastrar/**",
                                "/legislacoes/editar/**",
                                "/legislacoes/excluir/**"
                        ).hasRole("ADMIN")

                        // Protege o método POST de salvar as obras
                        .requestMatchers(HttpMethod.POST, "/obras/**").hasRole("ADMIN")

                        .requestMatchers(
                                "/pesquisas/cadastrar/**",
                                "/pesquisas/editar/**",
                                "/pesquisas/excluir/**"
                        ).hasAnyRole("ADMIN", "PESQUISADOR")

                        // 2. REGRAS PÚBLICAS
                        .requestMatchers(
                                "/",
                                "/login",
                                "/sobre",
                                "/contas/nova",
                                "/contas/salvar",
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/error",
                                "/legislacoes",
                                "/pesquisas/public/**",
                                "/obras",             // Listagem geral pública
                                "/obras/categoria/**" // Listagem por categoria pública
                        ).permitAll()

                        .anyRequest().authenticated()
                ) // Fim do authorizeHttpRequests
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
