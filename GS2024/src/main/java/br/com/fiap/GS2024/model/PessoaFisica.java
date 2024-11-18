package br.com.fiap.GS2024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_gs_pf")
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nm_nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Column(name = "ds_cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @Email(message = "Formato de e-mail inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Column(name = "ds_email", nullable = false)
    private String email;

    @Column(name = "ds_telefone")
    private String telefone;

    @Column(name = "ds_energia")
    private String energia;

    @Positive(message = "O valor de metros quadrados deve ser maior que zero.")
    @Column(name = "vl_metros")
    private Double metros;
}
