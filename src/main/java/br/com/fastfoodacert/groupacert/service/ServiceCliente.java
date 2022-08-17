package br.com.fastfoodacert.groupacert.service;

import br.com.fastfoodacert.groupacert.entities.dto.ClienteDTO;
import br.com.fastfoodacert.groupacert.entities.Cliente;

import java.util.List;

public interface ServiceCliente {

    Cliente buscarClienteId(Integer id);

    List<Cliente> buscarTodosClientes ();

    void atualizarCliente(Integer id, ClienteDTO dto);

    void deletarCliente(Integer id);

    Cliente cadastrarCliente(ClienteDTO cliente);
}
