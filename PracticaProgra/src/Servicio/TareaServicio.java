/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

/**
 *
 * @author TheJPlay2006
 */

import Dominio.Tarea;
import DAO.TareaDAO;

import java.sql.SQLException;
import java.util.List;

public class TareaServicio {
    private TareaDAO tareaDAO;

    public void eliminarTarea(int id) throws SQLException {
    tareaDAO.eliminarTarea(id);
}
    
    public void alternarEstadoTarea(int id) throws SQLException {
    tareaDAO.alternarEstado(id);
}
    public TareaServicio(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    public void agregarTarea(Tarea tarea) throws SQLException {
        tarea.validar(); 
        tareaDAO.agregar(tarea);
    }

    public List<Tarea> obtenerTareas() throws SQLException {
        return tareaDAO.listar();
    }
}