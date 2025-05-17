package org.uv.DAPP02Practica04;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @Column
    @GeneratedValue(generator = "ventas_clave_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ventas_clave_seq", sequenceName = "ventas_clave_seq", initialValue = 1, allocationSize = 1)
    private Long clave;

    @Column
    private LocalDate fecha;

    @Column
    private BigDecimal monto;

    @Column
    private Integer cliente;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}