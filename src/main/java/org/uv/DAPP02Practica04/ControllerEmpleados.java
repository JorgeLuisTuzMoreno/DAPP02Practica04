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
@RequestMapping("/empleados")
public class ControllerEmpleados {
    
    @Autowired
    RepositoryEmpleado repositoryEmpleado;
    
    @GetMapping()
    public List<Empleado> list() {
        repositoryEmpleado.findAll();
        List<Empleado> lstEmpleados = repositoryEmpleado.findAll();

        return lstEmpleados;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable Long id) {
        Optional<Empleado> opt=repositoryEmpleado.findById(id);
        if(opt.isPresent()){
            Empleado emp=opt.get();
            return ResponseEntity.ok(emp);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado input) {
    Optional<Empleado> opt = repositoryEmpleado.findById(id);
    if (opt.isPresent()) {
        Empleado empExistente = opt.get();
        
        empExistente.setNombre(input.getNombre());
        empExistente.setDireccion(input.getDireccion());
        empExistente.setTelefono(input.getTelefono());
        
        repositoryEmpleado.save(empExistente);
        return ResponseEntity.ok(empExistente);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    
    @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado input) {
        Empleado empNew = repositoryEmpleado.save(input);
        return ResponseEntity.ok(empNew);
    }
    
   @DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
    if (repositoryEmpleado.existsById(id)) {
        repositoryEmpleado.deleteById(id);
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}