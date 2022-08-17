package br.com.fastfoodacert.groupacert.controller;

import br.com.fastfoodacert.groupacert.entities.Entrega;
import br.com.fastfoodacert.groupacert.entities.dto.EntregaDTO;
import br.com.fastfoodacert.groupacert.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entrega")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService service;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer cadastrarEntrega(@Valid  @RequestBody EntregaDTO dto){
        Entrega entrega = service.cadastrarEntrega(dto);
        return entrega.getId();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscarEntregasPoId(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.buscarEntregaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EntregaDTO>> buscarTodosEntregas(){
        return ResponseEntity.ok().body(service.buscarTodasEntregas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntregaDTO> deletar(@PathVariable Integer id){
        service.deletarEntrega(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaDTO> atualizarEntrega(@PathVariable Integer id,@Valid @RequestBody EntregaDTO dto){
        service.atualizarEntrega(id, dto);
        return ResponseEntity.noContent().build();
    }
}
