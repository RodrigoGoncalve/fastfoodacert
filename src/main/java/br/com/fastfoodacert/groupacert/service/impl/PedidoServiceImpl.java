package br.com.fastfoodacert.groupacert.service.impl;

import br.com.fastfoodacert.groupacert.entities.Cliente;
import br.com.fastfoodacert.groupacert.entities.Pedido;
import br.com.fastfoodacert.groupacert.entities.Produto;
import br.com.fastfoodacert.groupacert.entities.dto.PedidoDTO;
import br.com.fastfoodacert.groupacert.exceptions.ObjectNoutFoundException;
import br.com.fastfoodacert.groupacert.repository.ClienteRepositorie;
import br.com.fastfoodacert.groupacert.repository.PedidoRepositorie;
import br.com.fastfoodacert.groupacert.repository.ProdutoRepositorie;
import br.com.fastfoodacert.groupacert.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepositorie clienteRepositorie;
    private final ProdutoRepositorie produtoRepositorie;
    private final PedidoRepositorie pedidoRepositorie;


    @Override
    @Transactional
    public Pedido criarPedido(PedidoDTO dto) {
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = buscarCliente(idCliente);

        Produto produto = getProduto(dto);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setDataPedido(LocalDate.now());

        return pedidoRepositorie.save(pedido);
    }

    @Override
    public PedidoDTO buscarPedidoId(Integer id) {
        Optional<Pedido> optional = Optional
                .ofNullable(pedidoRepositorie
                        .findById(id).orElseThrow(() ->
                                new ObjectNoutFoundException("Pedido não existe.")));

        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(id);
        dto.setIdCliente(optional.get().getCliente().getId());
        dto.setIdProduto(optional.get().getProduto().getId());
        dto.setDataPedido(optional.get().getDataPedido());

        return dto;
    }

    @Override
    public List<PedidoDTO> buscarTodosPedidos() {

        List<Pedido> listPedido = pedidoRepositorie.findAll();

        return listPedido
                .stream()
                .map(x -> {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setIdPedido(x.getId());
                    dto.setIdCliente(x.getCliente().getId());
                    dto.setIdProduto(x.getProduto().getId());
                    dto.setDataPedido(x.getDataPedido());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void atualizarPedido(Integer id, PedidoDTO dto) {
        PedidoDTO pedidoDTO =  buscarPedidoId(id);

        Cliente cliente = new Cliente();
        cliente.setId(pedidoDTO.getIdCliente());

        Produto produto = getProduto(dto);

        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getIdPedido());
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setDataPedido(pedidoDTO.getDataPedido());

        if (!cliente.getId().equals(dto.getIdCliente())){
            throw new RuntimeException("Este PEDIDO não pode mudar o cliente!");
        }

        pedidoRepositorie.save(pedido);
    }

    @Override
    public void deletarPedido(Integer id) {
       buscarPedidoId(id);
       pedidoRepositorie.deleteById(id);
    }

    private Produto getProduto(PedidoDTO dto) {
        Integer idProduto = dto.getIdProduto();
        return buscarProduto(idProduto);
    }

    private Produto buscarProduto(Integer idProduto) {
        Produto produto = produtoRepositorie
                .findById(idProduto)
                .orElseThrow(() ->
                        new ObjectNoutFoundException("Produto não existe em nosso estoque."));
        ;
        return produto;
    }

    private Cliente buscarCliente(Integer idCliente) {
        return clienteRepositorie
                .findById(idCliente)
                .orElseThrow(() ->
                        new ObjectNoutFoundException("Cliente não existe."));
    }
}
