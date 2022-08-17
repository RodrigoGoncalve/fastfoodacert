package br.com.fastfoodacert.groupacert.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo DESCRICAO é requerido")
    @Length(min = 3, max =100, message = "O campo DESCRICAO deve ter entre 3 a 100 caracteres")
    private String descricao;

    @NotNull(message = "Campo PRECO é requerido")
    private BigDecimal preco;

    @NotNull(message = "Campo QUANTIDADE é requerido")
    private Integer quantidade;

}
