package br.com.fastfoodacert.groupacert.service.impl;

import br.com.fastfoodacert.groupacert.entities.dto.ClienteDTO;
import br.com.fastfoodacert.groupacert.entities.Cliente;
import br.com.fastfoodacert.groupacert.exceptions.ObjectNoutFoundException;
import br.com.fastfoodacert.groupacert.repository.ClienteRepositorie;
import br.com.fastfoodacert.groupacert.service.ServiceCliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ServiceCliente {

    private final ClienteRepositorie repositorie;

    private final ModelMapper mapper;

    @Override
    public Cliente buscarClienteId(Integer id){
        Optional<Cliente> cliente = repositorie.findById(id);
        return cliente.orElseThrow(() -> new ObjectNoutFoundException(
                "Cliente não encontrado na base de dados"));
    }

    @Override
    public Cliente cadastrarCliente(ClienteDTO dto) {
        findByEmail(dto);
        return repositorie.save(mapper.map(dto, Cliente.class));
    }

    @Override
    public List<Cliente> buscarTodosClientes (){
        return repositorie.findAll();
    }

    @Override
    @Transactional
    public void atualizarCliente(Integer id, ClienteDTO dto) {
        buscarClienteId(id);
        dto.setId(id);
        repositorie.save(mapper.map(dto, Cliente.class));
    }

    @Override
    public void deletarCliente(Integer id){
        buscarClienteId(id);
        repositorie.deleteById(id);
    }

    private void findByEmail(ClienteDTO dto){
        Optional<Cliente> optional = repositorie.findByEmail(dto.getEmail());
        if (optional.isPresent()){
            throw new ObjectNoutFoundException("Cliente já existe na base de dados.");
        }
    }
}


