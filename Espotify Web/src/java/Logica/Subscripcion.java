/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "subscripciones")
public class Subscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;  // Identificación del cliente (puede ser un email o id)

    @Column(nullable = false)
    private String tipo;  // Tipo de suscripción (semanal, mensual, anual)

    @Column(nullable = false)
    private Date fechaInicio;  // Fecha de inicio de la suscripción

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;  // Estado de la suscripción (Pendiente, Vigente, Cancelada, Vencida)

    @Column(nullable = false)
    private double montoTotal;  // Monto total de la suscripción

    // Constructor vacío para JPA
    public Subscripcion() {}

    // Constructor con parámetros
    public Subscripcion(String cliente, String tipo, Date fechaInicio, Estado estado, double montoTotal) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.montoTotal = montoTotal;
    }

    
    // Enumeración para los posibles estados de la suscripción
    public enum Estado {
        PENDIENTE,
        VIGENTE,
        CANCELADA,
        VENCIDA
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    // Método para calcular automáticamente si la suscripción debe pasar a vencida
public boolean estaVencida(Date fechaActual) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fechaActual); // Establecer la fecha actual

    // Clonar la fecha de inicio para no modificar el original
    Calendar fechaInicioCalendar = Calendar.getInstance();
    fechaInicioCalendar.setTime(this.fechaInicio);

    switch (this.tipo.toLowerCase()) { // Convertimos a minúsculas para evitar problemas de capitalización
        case "semanal":
            fechaInicioCalendar.add(Calendar.WEEK_OF_YEAR, 1); // Añadir 1 semana
            break;
        case "mensual":
            fechaInicioCalendar.add(Calendar.MONTH, 1); // Añadir 1 mes
            break;
        case "anual":
            fechaInicioCalendar.add(Calendar.YEAR, 1); // Añadir 1 año
            break;
        default:
            return false; // Opción no válida
    }

    // Compara la fecha calculada con la fecha actual
    return fechaInicioCalendar.getTime().before(fechaActual); // Retorna true si está vencida
}
    
    
}
