package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.dto.CadastroContaDTO;
import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Perfil;
import br.edu.ifba.ocs.repository.ContaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final PasswordEncoder passwordEncoder;

    public ContaService(ContaRepository contaRepository,
                        PasswordEncoder passwordEncoder) {
        this.contaRepository = contaRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void cadastrar(CadastroContaDTO dto) {

        if (contaRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Conta conta = new Conta();
        conta.setNome(dto.getNome());
        conta.setEmail(dto.getEmail());
        conta.setInstituicao(dto.getInstituicao());
        conta.setSenhaHash(passwordEncoder.encode(dto.getSenha()));


        if (dto.getPerfilDesejado() == Perfil.pesquisador) {

            conta.setPerfil(Perfil.visitante);
            conta.setValidado(false);
        } else {
            conta.setPerfil(dto.getPerfilDesejado());
            conta.setValidado(true);
        }

        contaRepository.save(conta);
    }


    public List<Conta> listarPesquisadoresPendentes() {
        return contaRepository.findByPerfilAndValidadoFalse(Perfil.visitante);
    }


    @Transactional
    public void aprovarPesquisador(UUID idConta) {

        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() ->
                        new RuntimeException("Conta não encontrada"));

        conta.setPerfil(Perfil.pesquisador);
        conta.setValidado(true);
    }



    @Transactional
    public void rejeitarPesquisador(UUID idConta) {

        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() ->
                        new RuntimeException("Conta não encontrada"));

        contaRepository.delete(conta);
    }
}
