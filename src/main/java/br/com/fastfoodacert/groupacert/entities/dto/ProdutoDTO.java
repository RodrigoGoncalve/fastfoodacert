package br.com.fastfoodacert.groupacert.entities.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;

    @NotEmpty(message = "Campo DESCRICAO é requerido")
    @Length(min = 3, max =100, message = "O campo DESCRICAO deve ter entre 3 a 100 caracteres")
    private String descricao;

    @NotNull(message = "Campo PRECO é requerido")
    private BigDecimal preco;

    @NotNull(message = "Campo QUANTIDADE é requerido")
    private Integer quantidade;
}
