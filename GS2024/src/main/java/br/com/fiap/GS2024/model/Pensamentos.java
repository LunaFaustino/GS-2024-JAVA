package br.com.fiap.GS2024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_gs_pensamentos")
public class Pensamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A opinião não pode ser nula.")
    @Size(max = 500, message = "A opinião deve ter no máximo 500 caracteres.")
    @Column(name = "ds_opiniao", nullable = false, length = 500)
    private String opiniao;
}
