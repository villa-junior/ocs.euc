package br.edu.ifba.ocs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

                        /* ===== ROTAS PÚBLICAS (VISUALIZAÇÃO) ===== */
                        .requestMatchers(
                                "/",
                                "/login",
                                "/contas/**",
                                "/sobre",
                                "/obras/**",
                                "/pesquisas/**",
                                "/legislacoes/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()


                        .requestMatchers("/pesquisas/cadastrar/**")
                        .hasAnyRole("ADMIN", "PESQUISADOR")


                        .requestMatchers(
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
