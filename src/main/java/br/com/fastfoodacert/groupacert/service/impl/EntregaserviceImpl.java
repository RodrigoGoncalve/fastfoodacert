package br.com.fastfoodacert.groupacert.service.impl;

import br.com.fastfoodacert.groupacert.entities.Entrega;
import br.com.fastfoodacert.groupacert.entities.Pedido;
import br.com.fastfoodacert.groupacert.entities.dto.EntregaDTO;
import br.com.fastfoodacert.groupacert.exceptions.ObjectNoutFoundException;
import br.com.fastfoodacert.groupacert.repository.EntregaRepositorie;
import br.com.fastfoodacert.groupacert.repository.PedidoRepositorie;
import br.com.fastfoodacert.groupacert.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregaserviceImpl implements EntregaService {

    private final EntregaRepositorie entregaRepositorie;

    private final PedidoRepositorie pedidoRepositorie;

    @Override
    public Entrega cadastrarEntrega(EntregaDTO dto) {
        Pedido pedido = buscarPedido(dto.getPedido());

        Entrega entrega = new Entrega();
        entrega.setPedido(pedido);

        return entregaRepositorie.save(entrega);
    }

    @Override
    public EntregaDTO buscarEntregaPorId(Integer id) {

        Optional<Entrega> optional = getEntrega(id);

        EntregaDTO entrega = new EntregaDTO();
        entrega.setId(id);
        entrega.setPedido(optional.get().getPedido().getId());

        return entrega;
    }

    @Override
    public List<EntregaDTO> buscarTodasEntregas() {
        List<Entrega> entregas = entregaRepositorie.findAll();

        return entregas
                .stream()
                .map(x -> {
                    EntregaDTO response = new EntregaDTO();
                    response.setId(x.getId());
                    response.setPedido(x.getPedido().getId());
                    return response;
                }).collect(Collectors.toList());

    }

    @Override
    public void deletarEntrega(Integer id) {
        getEntrega(id);
        entregaRepositorie.deleteById(id);
    }

    @Override
    public void atualizarEntrega(Integer id, EntregaDTO dto) {
        getEntrega(id);
        Pedido pedido = new Pedido();
        pedido.setId(dto.getPedido());

        Entrega entrega = new Entrega();
        entrega.setId(id);
        entrega.setPedido(pedido);
        entregaRepositorie.save(entrega);
    }

    private Optional<Entrega> getEntrega(Integer id) {
        return Optional.ofNullable(entregaRepositorie
                .findById(id)
                .orElseThrow(() ->
                        new ObjectNoutFoundException("Entrega não encontrado! ")));
    }

    private Pedido buscarPedido(Integer idPedido) {
        return pedidoRepositorie
                .findById(idPedido)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));
    }
}
