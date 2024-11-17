package br.com.fiap.GS2024.service;

import br.com.fiap.GS2024.model.Pensamentos;

import java.util.List;
import java.util.Optional;

public interface PensamentosService {

    Pensamentos salvar(Pensamentos pensamento);

    Optional<Pensamentos> buscarPorId(Long id);

    List<Pensamentos> listarTodos();

    Pensamentos atualizar(Long id, Pensamentos pensamentoAtualizado);

    void deletar(Long id);
}
