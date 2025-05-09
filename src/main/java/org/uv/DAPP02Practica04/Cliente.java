package org.uv.DAPP02Practica04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")

public class Cliente {

    @Id
    @Column
    @GeneratedValue(generator = "clientes_clave_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clientes_clave_seq", sequenceName = "clientes_clave_seq", initialValue = 1, allocationSize = 1)

    private Long clave;

    @Column
    private String nombre;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}