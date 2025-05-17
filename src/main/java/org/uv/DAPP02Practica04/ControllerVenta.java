package org.uv.DAPP02Practica04;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/ventas")
public class ControllerVenta {
    
    @Autowired
    RepositoryVenta repositoryVenta;
    
    @GetMapping()
    public List<Venta> list() {
        return repositoryVenta.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> get(@PathVariable Long id) {
        Optional<Venta> opt = repositoryVenta.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Venta> put(@PathVariable Long id, @RequestBody Venta input) {
        Optional<Venta> opt = repositoryVenta.findById(id);
        if (opt.isPresent()) {
            Venta ventaExistente = opt.get();

            ventaExistente.setFecha(input.getFecha());
            ventaExistente.setMonto(input.getMonto());
            ventaExistente.setCliente(input.getCliente());
            
            repositoryVenta.save(ventaExistente);
            return ResponseEntity.ok(ventaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Venta> post(@RequestBody Venta input) {
        Venta ventaNueva = repositoryVenta.save(input);
        return ResponseEntity.ok(ventaNueva);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Venta> opt = repositoryVenta.findById(id);
        if (opt.isPresent()) {
            repositoryVenta.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error inesperado en el servidor")
    public void handleError() {
    }
}