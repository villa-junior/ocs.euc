package br.edu.ifba.ocs.security;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Perfil;
import br.edu.ifba.ocs.repository.ContaRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContaDetailsService implements UserDetailsService {

    private final ContaRepository contaRepository;

    public ContaDetailsService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Conta conta = contaRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Conta não encontrada"));


        if (conta.getPerfil() == Perfil.pesquisador && !conta.isValidado()) {
            throw new DisabledException(
                    "Seu cadastro como pesquisador ainda está em análise pelo administrador"
            );
        }

        return new ContaDetails(conta);
    }
}
