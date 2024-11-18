package br.com.fiap.GS2024.controller;

import br.com.fiap.GS2024.model.Pensamentos;
import br.com.fiap.GS2024.service.PensamentosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pensamentos")
public class PensamentosController {

    @Autowired
    private PensamentosService pensamentosService;

    @PostMapping
    public ResponseEntity<Pensamentos> criar(@Valid @RequestBody Pensamentos pensamento) {
        Pensamentos salvo = pensamentosService.salvar(pensamento);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pensamentos>> buscarPorId(@PathVariable Long id) {
        Optional<Pensamentos> pensamento = pensamentosService.buscarPorId(id);
        if (pensamento.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        EntityModel<Pensamentos> recurso = EntityModel.of(
                pensamento.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PensamentosController.class).buscarPorId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PensamentosController.class).listarTodos()).withRel("todos-pensamentos")
        );

        return ResponseEntity.ok(recurso);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Pensamentos>>> listarTodos(){
        List<EntityModel<Pensamentos>> pensamentos = pensamentosService.listarTodos().stream()
                .map(p -> EntityModel.of(
                        p,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PensamentosController.class).buscarPorId(p.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pensamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pensamentos> atualizar(@PathVariable Long id, @RequestBody Pensamentos pensamentoAtualizado) {
        try {
            Pensamentos atualizado = pensamentosService.atualizar(id, pensamentoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pensamentosService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
