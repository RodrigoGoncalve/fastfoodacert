package br.com.fastfoodacert.groupacert.service;

import br.com.fastfoodacert.groupacert.entities.Entrega;
import br.com.fastfoodacert.groupacert.entities.dto.EntregaDTO;

import java.util.List;

public interface EntregaService {

    Entrega cadastrarEntrega(EntregaDTO entrega);

    EntregaDTO buscarEntregaPorId(Integer id);

    List <EntregaDTO> buscarTodasEntregas();

    void deletarEntrega(Integer id);

    void atualizarEntrega(Integer id, EntregaDTO dto);
}
