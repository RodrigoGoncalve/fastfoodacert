package br.com.fastfoodacert.groupacert.controller;

import br.com.fastfoodacert.groupacert.entities.dto.ClienteDTO;
import br.com.fastfoodacert.groupacert.entities.Cliente;
import br.com.fastfoodacert.groupacert.service.ServiceCliente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ServiceCliente service;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes(){
        List<Cliente> lista = service.buscarTodosClientes();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.buscarClienteId(id));
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ClienteDTO> cadasttrarCliente(@Valid @RequestBody ClienteDTO dto){
        service.cadastrarCliente(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,@Valid @RequestBody ClienteDTO dto) {
        service.atualizarCliente(id, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Integer id){
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
