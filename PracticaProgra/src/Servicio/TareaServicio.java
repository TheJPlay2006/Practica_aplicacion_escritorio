/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

/**
 *
 * @author TheJPlay2006
 */

import DAO.TareaDAO;
import Dominio.Tarea;
import java.sql.SQLException;
import java.util.List;

public class TareaServicio {
    private TareaDAO tareaDAO;

    // Constructor que recibe una instancia de TareaDAO
    public TareaServicio(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    // Método para agregar una tarea
    public void agregarTarea(Tarea tarea) throws SQLException {
        // Validar la tarea antes de agregarla
        tarea.validar();
        tareaDAO.agregar(tarea);
    }

    // Método para obtener todas las tareas activas
    public List<Tarea> obtenerTareas() throws SQLException {
        return tareaDAO.listar();
    }

    // Método para alternar el estado de una tarea
    public void alternarEstadoTarea(int id) throws SQLException {
        tareaDAO.alternarEstado(id);
    }

    // Método para eliminar una tarea (eliminación lógica)
    public void eliminarTarea(int id) throws SQLException {
        tareaDAO.eliminarTarea(id);
    }

    // Método para restaurar una tarea eliminada
    public void restaurarTarea(int id) throws SQLException {
        tareaDAO.restaurarTarea(id);
    }

    // Método para obtener una tarea por su ID
    public Tarea obtenerTareaPorId(int id) throws SQLException {
        return tareaDAO.obtenerPorId(id);
    }
}