package br.edu.ifba.ocs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


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


                        .requestMatchers(
                                "/",
                                "/login",
                                "/sobre",
                                "/contas/nova",
                                "/contas/salvar",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/pesquisas/public/**",
                                "/legislacoes",
                                "/obras/**",
                                "/error"
                        ).permitAll()


                        .requestMatchers(
                                "/pesquisas/cadastrar/**",
                                "/pesquisas/editar/**",
                                "/pesquisas/excluir/**",
                                "/pesquisas/**"
                        ).hasAnyRole("ADMIN", "PESQUISADOR")


                        .requestMatchers(
                                "/autoras",
                                "/autoras/listar",
                                "/autoras/cadastrar/**",
                                "/legislacoes/cadastrar/**",
                                "/legislacoes/editar/**",
                                "/legislacoes/salvar/**",
                                "/legislacoes/excluir/**",
                                "/autoras/cadastrar/**",
                                "/autoras/editar/**",
                                "/autoras/excluir/**",
                                "/admin/**",
                                "/obras/cadastrar/**",
                                "/obras/editar/**",
                                "/obras/excluir/**"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
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
