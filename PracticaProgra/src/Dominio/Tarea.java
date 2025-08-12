/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author TheJPlay2006
 */

import java.time.LocalDate;

public class Tarea {
    private int id;
    private String titulo;
    private int prioridad; 
    private boolean estado;
    private boolean especial;
    private LocalDate fecha;
    private boolean eliminado;

    // Constructor, getters y setters
    public Tarea(String titulo, int prioridad, boolean especial, LocalDate fecha) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.especial = especial;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Métodos de validación
    public void validar() throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3.");
        }
    }
}