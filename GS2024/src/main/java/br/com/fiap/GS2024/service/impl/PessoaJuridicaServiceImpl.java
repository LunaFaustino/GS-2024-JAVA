package br.com.fiap.GS2024.service.impl;

import br.com.fiap.GS2024.model.PessoaJuridica;
import br.com.fiap.GS2024.repository.PessoaJuridicaRepository;
import br.com.fiap.GS2024.service.PessoaJuridicaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    public PessoaJuridica salvar(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public Optional<PessoaJuridica> buscarPorId(Long id) {
        return pessoaJuridicaRepository.findById(id);
    }

    @Override
    public List<PessoaJuridica> listarTodos() {
        return pessoaJuridicaRepository.findAll();
    }

    @Override
    public PessoaJuridica atualizar(Long id, PessoaJuridica pessoaAtualizada) {
        return pessoaJuridicaRepository.findById(id).map(pessoa ->{
           pessoa.setRazaoSocial(pessoaAtualizada.getRazaoSocial());
           pessoa.setCnpj(pessoaAtualizada.getCnpj());
           pessoa.setEmail(pessoaAtualizada.getEmail());
           pessoa.setTelefone(pessoaAtualizada.getTelefone());
           pessoa.setEnergia(pessoaAtualizada.getEnergia());
           pessoa.setMetros(pessoaAtualizada.getMetros());
           return pessoaJuridicaRepository.save(pessoa);
        }).orElseThrow(() -> new EntityNotFoundException("Pessoa Jurídica com ID " + id + " não encontrada"));
    }

    @Override
    public void deletar(Long id) {
        if (pessoaJuridicaRepository.existsById(id)) {
            pessoaJuridicaRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Pessoa Jurídica com ID " + id + " não encontrada");
        }
    }
}
