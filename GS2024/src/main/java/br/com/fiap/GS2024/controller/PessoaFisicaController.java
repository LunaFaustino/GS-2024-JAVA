package br.com.fiap.GS2024.controller;

import br.com.fiap.GS2024.model.PessoaFisica;
import br.com.fiap.GS2024.service.PessoaFisicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/pessoas-fisicas")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping
    public ResponseEntity<EntityModel<PessoaFisica>> criar(@Valid @RequestBody PessoaFisica pessoaFisica) {
        PessoaFisica novaPessoa = pessoaFisicaService.salvar(pessoaFisica);

        EntityModel<PessoaFisica> resource = EntityModel.of(novaPessoa,
                linkTo(methodOn(PessoaFisicaController.class).buscarPorId(novaPessoa.getId())).withSelfRel(),
                linkTo(methodOn(PessoaFisicaController.class).listarTodos()).withRel("todas-pessoas-fisicas"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PessoaFisica>> buscarPorId(@PathVariable Long id) {
        Optional<PessoaFisica> pessoa = pessoaFisicaService.buscarPorId(id);

        if (pessoa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<PessoaFisica> resource = EntityModel.of(pessoa.get(),
                linkTo(methodOn(PessoaFisicaController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(PessoaFisicaController.class).listarTodos()).withRel("todas-pessoas-fisicas"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<PessoaFisica>>> listarTodos() {
        List<EntityModel<PessoaFisica>> pessoas = pessoaFisicaService.listarTodos().stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(PessoaFisicaController.class).buscarPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(PessoaFisicaController.class).listarTodos()).withRel("todas-pessoas-fisicas")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PessoaFisica>> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisica pessoaAtualizada) {
        try {
            PessoaFisica pessoa = pessoaFisicaService.atualizar(id, pessoaAtualizada);

            EntityModel<PessoaFisica> resource = EntityModel.of(pessoa,
                    linkTo(methodOn(PessoaFisicaController.class).buscarPorId(pessoa.getId())).withSelfRel(),
                    linkTo(methodOn(PessoaFisicaController.class).listarTodos()).withRel("todas-pessoas-fisicas"));

            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pessoaFisicaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
