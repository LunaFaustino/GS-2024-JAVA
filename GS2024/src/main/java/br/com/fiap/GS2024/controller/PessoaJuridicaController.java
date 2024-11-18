package br.com.fiap.GS2024.controller;

import br.com.fiap.GS2024.model.PessoaJuridica;
import br.com.fiap.GS2024.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pessoas-juridicas")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping
    public ResponseEntity<EntityModel<PessoaJuridica>> criar(@Valid @RequestBody PessoaJuridica pessoaJuridica) {
        PessoaJuridica novaPessoa = pessoaJuridicaService.salvar(pessoaJuridica);

        EntityModel<PessoaJuridica> resource = EntityModel.of(novaPessoa,
                linkTo(methodOn(PessoaJuridicaController.class).buscarPorId(novaPessoa.getId())).withSelfRel(),
                linkTo(methodOn(PessoaJuridicaController.class).listarTodos()).withRel("todas-pessoas-juridicas"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PessoaJuridica>> buscarPorId(@PathVariable Long id) {
        Optional<PessoaJuridica> pessoa = pessoaJuridicaService.buscarPorId(id);

        if (pessoa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<PessoaJuridica> resource = EntityModel.of(pessoa.get(),
                linkTo(methodOn(PessoaJuridicaController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(PessoaJuridicaController.class).listarTodos()).withRel("todas-pessoas-juridicas"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<PessoaJuridica>>> listarTodos() {
        List<EntityModel<PessoaJuridica>> pessoas = pessoaJuridicaService.listarTodos().stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(PessoaJuridicaController.class).buscarPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(PessoaJuridicaController.class).listarTodos()).withRel("todas-pessoas-juridicas")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PessoaJuridica>> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaJuridica pessoaAtualizada) {
        try {
            PessoaJuridica pessoa = pessoaJuridicaService.atualizar(id, pessoaAtualizada);

            EntityModel<PessoaJuridica> resource = EntityModel.of(pessoa,
                    linkTo(methodOn(PessoaJuridicaController.class).buscarPorId(pessoa.getId())).withSelfRel(),
                    linkTo(methodOn(PessoaJuridicaController.class).listarTodos()).withRel("todas-pessoas-juridicas"));

            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pessoaJuridicaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
