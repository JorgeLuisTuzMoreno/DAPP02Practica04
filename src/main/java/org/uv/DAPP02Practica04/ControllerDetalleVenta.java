package org.uv.DAPP02Practica04;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle_venta")
public class ControllerDetalleVenta {

    @Autowired
    RepositoryDetalleVenta repositoryDetalleVenta;

    @GetMapping
    public List<DetalleVenta> list() {
        return repositoryDetalleVenta.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> get(@PathVariable Long id) {
        Optional<DetalleVenta> opt = repositoryDetalleVenta.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> post(@RequestBody DetalleVenta input) {
        DetalleVenta nuevo = repositoryDetalleVenta.save(input);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> put(@PathVariable Long id, @RequestBody DetalleVenta input) {
        Optional<DetalleVenta> opt = repositoryDetalleVenta.findById(id);
        if (opt.isPresent()) {
            DetalleVenta existente = opt.get();
            existente.setIdventa(input.getIdventa());
            existente.setProducto(input.getProducto());
            existente.setPrecio(input.getPrecio());
            existente.setCantidad(input.getCantidad());
            existente.setDescripcion(input.getDescripcion());

            repositoryDetalleVenta.save(existente);
            return ResponseEntity.ok(existente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<DetalleVenta> opt = repositoryDetalleVenta.findById(id);
        if (opt.isPresent()) {
            repositoryDetalleVenta.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error interno del servidor")
    public void handleError() {
    }
}