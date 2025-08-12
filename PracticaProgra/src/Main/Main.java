package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author TheJPlay2006
 */

import DAO.TareaDAO;
import Servicio.TareaServicio;
import gui.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class Main {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName=AgendaDB;"
            + "integratedSecurity=true;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void main(String[] args) {
        try {
            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(URL);
            TareaDAO tareaDAO = new TareaDAO(conexion);

            // Crear la tabla si no existe
            tareaDAO.crearTablaSiNoExiste();

            // Inicializar capas
            TareaServicio tareaService = new TareaServicio(tareaDAO);

            // Abrir la GUI
            SwingUtilities.invokeLater(() -> {
                gui ventana = new gui(tareaService);
                ventana.setVisible(true); // Hacer visible la ventana
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}