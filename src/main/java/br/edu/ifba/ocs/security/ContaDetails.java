package br.edu.ifba.ocs.security;

import br.edu.ifba.ocs.model.Conta;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ContaDetails implements UserDetails {

    private final Conta conta;

    public ContaDetails(Conta conta) {
        this.conta = conta;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + conta.getPerfil().name().toUpperCase()
                )
        );
    }

    @Override
    public String getPassword() {
        return conta.getSenhaHash();
    }

    @Override
    public String getUsername() {
        return conta.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Conta getConta() {
        return conta;
    }
}
