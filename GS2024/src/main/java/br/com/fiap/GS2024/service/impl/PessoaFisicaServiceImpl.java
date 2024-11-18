package br.com.fiap.GS2024.service.impl;

import br.com.fiap.GS2024.model.PessoaFisica;
import br.com.fiap.GS2024.repository.PessoaFisicaRepository;
import br.com.fiap.GS2024.service.PessoaFisicaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaServiceImpl  implements PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public PessoaFisica salvar(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public Optional<PessoaFisica> buscarPorId(Long id) {
        return pessoaFisicaRepository.findById(id);
    }

    @Override
    public List<PessoaFisica> listarTodos() {
        return pessoaFisicaRepository.findAll();
    }

    @Override
    public PessoaFisica atualizar(Long id, PessoaFisica pessoaAtualizada) {
        return pessoaFisicaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setCpf(pessoaAtualizada.getCpf());
            pessoa.setEmail(pessoaAtualizada.getEmail());
            pessoa.setTelefone(pessoaAtualizada.getTelefone());
            pessoa.setEnergia(pessoaAtualizada.getEnergia());
            pessoa.setMetros(pessoaAtualizada.getMetros());
            return pessoaFisicaRepository.save(pessoa);
        }).orElseThrow(() -> new EntityNotFoundException("Pessoa Física com ID " + id + " não encontrada"));
    }

    @Override
    public void deletar(Long id) {
        if (pessoaFisicaRepository.existsById(id)) {
            pessoaFisicaRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Pessoa Física com ID " + id + " não encontrada");
        }
    }
}
