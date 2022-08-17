package br.com.fastfoodacert.groupacert.service;

import br.com.fastfoodacert.groupacert.entities.Pedido;
import br.com.fastfoodacert.groupacert.entities.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    Pedido criarPedido(PedidoDTO dto);

    PedidoDTO buscarPedidoId(Integer id);

    List<PedidoDTO> buscarTodosPedidos();

    void atualizarPedido(Integer id, PedidoDTO dto);

    void deletarPedido(Integer id);
}
