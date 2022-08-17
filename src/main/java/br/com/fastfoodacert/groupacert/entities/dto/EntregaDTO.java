package br.com.fastfoodacert.groupacert.entities.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDTO {

    private Integer id;
    private Integer pedido;
}
