package br.com.fastfoodacert.groupacert.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo NOME é requerido")
    @Length(min = 3, max =100, message = "O campo NOME deve ter entre 3 a 100 caracteres")
    private String nome;

    @NotEmpty(message = "Campo CPF é requerido")
    @Length(min = 11, max = 11, message = "O campo CPF deve ter entre 11 caracteres")
    private String cpf;

    @NotEmpty(message = "Campo EMAIL é requerido")
    @Email(message = "Este não é um formato de e-mail válido")
    private String email;

    @NotEmpty(message = "Campo ENDERECO é requerido")
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
