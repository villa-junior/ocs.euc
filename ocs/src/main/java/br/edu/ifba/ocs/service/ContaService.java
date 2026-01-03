package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.dto.CadastroContaDTO;
import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Perfil;
import br.edu.ifba.ocs.repository.ContaRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;


@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final PasswordEncoder passwordEncoder;

    public ContaService(ContaRepository contaRepository,
                        PasswordEncoder passwordEncoder) {
        this.contaRepository = contaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrar(CadastroContaDTO dto) {

        if (contaRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Conta conta = new Conta();
        conta.setNome(dto.getNome());
        conta.setEmail(dto.getEmail());
        conta.setPerfil(dto.getPerfil());
        conta.setInstituicao(dto.getInstituicao());


        conta.setSenhaHash(
                passwordEncoder.encode(dto.getSenha())
        );

        contaRepository.save(conta);
    }
}
