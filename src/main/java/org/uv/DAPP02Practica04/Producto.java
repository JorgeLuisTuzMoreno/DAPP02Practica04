package org.uv.DAPP02Practica04;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productos_clave_seq")
    @SequenceGenerator(name = "productos_clave_seq", sequenceName = "productos_clave_seq", allocationSize = 1)
    private Long clave;

    @Column
    private String nombre;

    @Column
    private float precio;

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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
