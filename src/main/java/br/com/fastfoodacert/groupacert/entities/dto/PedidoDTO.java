package br.com.fastfoodacert.groupacert.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @JsonProperty("pedido_id")
    private Integer idPedido;

    @JsonProperty("cliente_id")
    private Integer idCliente;

    @JsonProperty("produto_id")
    private Integer idProduto;

    @JsonProperty("data_pedido")
    private LocalDate dataPedido;

}
