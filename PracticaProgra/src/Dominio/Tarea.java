
package Dominio; 

import java.time.LocalDate;


public class Tarea {
    private int id;
    private String titulo;
    private int prioridad; // 1=Alta, 2=Media, 3=Baja
    private boolean estado; // false=Pendiente, true=Hecho
    private boolean especial; // true=★, false=Sin marca
    private LocalDate fecha;

    // Constructor completo
    public Tarea(int id, String titulo, int prioridad, boolean estado, boolean especial, LocalDate fecha) {
        this.id = id;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
        this.especial = especial;
        this.fecha = fecha;
    }

    // Constructor simplificado para nuevas tareas
    public Tarea(String titulo, int prioridad, boolean especial, LocalDate fecha) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.especial = especial;
        this.fecha = fecha;
        this.estado = false; // Por defecto, la tarea está pendiente
    }

    // Validación de datos
    public void validar() throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3.");
        }
    }

    // Getters y setters
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
}