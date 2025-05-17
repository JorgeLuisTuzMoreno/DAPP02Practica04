package org.uv.DAPP02Practica04;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @Column
    @GeneratedValue(generator = "detalle_venta_idrow_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "detalle_venta_idrow_seq", sequenceName = "detalle_venta_idrow_seq", initialValue = 1, allocationSize = 1)
    private Long idrow;

    @Column(name = "idventa")
    private Integer idventa;

    @Column
    private Integer producto;

    @Column
    private BigDecimal precio;

    @Column
    private BigDecimal cantidad;

    @Column
    private String descripcion;

    // Getters y Setters

    public Long getIdrow() {
        return idrow;
    }

    public void setIdrow(Long idrow) {
        this.idrow = idrow;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}