package org.uv.DAPP02Practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ControllerProductos {

    @Autowired
    RepositoryProducto repositoryProducto;

    @GetMapping
    public List<Producto> list() {
        return repositoryProducto.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Long id) {
        Optional<Producto> opt = repositoryProducto.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> post(@RequestBody Producto input) {
        Producto nuevo = repositoryProducto.save(input);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> put(@PathVariable Long id, @RequestBody Producto input) {
        Optional<Producto> opt = repositoryProducto.findById(id);
        if (opt.isPresent()) {
            Producto existente = opt.get();
            existente.setNombre(input.getNombre());
            existente.setPrecio(input.getPrecio());
            repositoryProducto.save(existente);
            return ResponseEntity.ok(existente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repositoryProducto.existsById(id)) {
            repositoryProducto.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleError() {
    }
}
