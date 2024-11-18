package br.com.fiap.GS2024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_gs_pj")
public class PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A razão social não pode estar vazia")
    @Size(max = 100, message = "A razão social deve ter no máximo 100 caracteres.")
    @Column( name = "nm_razaoSocial", nullable = false, length = 100)
    private String razaoSocial;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Column(name = "ds_cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ds_telefone")
    private String telefone;

    @Column(name = "ds_energia")
    private String energia;

    @Positive(message = "O valor de metros quadrados deve ser maior que zero.")
    @Column(name = "val_metros")
    private Double metros;
}
