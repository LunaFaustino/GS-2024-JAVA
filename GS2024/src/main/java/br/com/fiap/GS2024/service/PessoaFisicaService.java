package br.com.fiap.GS2024.service;

import br.com.fiap.GS2024.model.PessoaFisica;

import java.util.List;
import java.util.Optional;

public interface PessoaFisicaService {

    PessoaFisica salvar(PessoaFisica pessoaFisica);

    Optional<PessoaFisica> buscarPorId(Long id);

    List<PessoaFisica> listarTodos();

    PessoaFisica atualizar(Long id, PessoaFisica pessoaAtualizada);

    void deletar(Long id);
}
