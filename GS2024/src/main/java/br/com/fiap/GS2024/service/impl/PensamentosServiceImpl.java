package br.com.fiap.GS2024.service.impl;

import br.com.fiap.GS2024.model.Pensamentos;
import br.com.fiap.GS2024.repository.PensamentosRepository;
import br.com.fiap.GS2024.service.PensamentosService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PensamentosServiceImpl implements PensamentosService {

    @Autowired
    private PensamentosRepository pensamentosRepository;


    @Override
    public Pensamentos salvar(Pensamentos pensamento) {
        return pensamentosRepository.save(pensamento);
    }

    @Override
    public Optional<Pensamentos> buscarPorId(Long id) {
        return pensamentosRepository.findById(id);
    }

    @Override
    public List<Pensamentos> listarTodos() {
        return pensamentosRepository.findAll();
    }

    @Override
    public Pensamentos atualizar(Long id, Pensamentos pensamentoAtualizado) {
        return pensamentosRepository.findById(id).map(pensamento ->{
            pensamento.setOpiniao(pensamentoAtualizado.getOpiniao());
            return pensamentosRepository.save(pensamento);
        }).orElseThrow(() -> new EntityNotFoundException("Pensamento com id " + id + " não encontrado"));
    }

    @Override
    public void deletar(Long id) {
        if (pensamentosRepository.existsById(id)){
            pensamentosRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Pensamento com id " + id + " não encontrado");
        }
    }
}
