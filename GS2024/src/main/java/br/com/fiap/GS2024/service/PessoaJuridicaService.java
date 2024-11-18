package br.com.fiap.GS2024.service;

import br.com.fiap.GS2024.model.PessoaJuridica;

import java.util.List;
import java.util.Optional;

public interface PessoaJuridicaService {

    PessoaJuridica salvar(PessoaJuridica pessoaJuridica);

    Optional<PessoaJuridica> buscarPorId(Long id);

    List<PessoaJuridica> listarTodos();

    PessoaJuridica atualizar(Long id, PessoaJuridica pessoaAtualizada);

    void deletar(Long id);
}
