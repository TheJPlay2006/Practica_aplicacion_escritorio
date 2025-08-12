package DAO;

import Dominio.Tarea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    private Connection conexion;

    public TareaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para crear la tabla si no existe
    public void crearTablaSiNoExiste() throws SQLException {
        String sql = """
            IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Tarea' AND xtype='U')
            CREATE TABLE Tarea (
                id INT IDENTITY(1,1) PRIMARY KEY,
                titulo NVARCHAR(255) NOT NULL,
                prioridad INT CHECK (prioridad IN (1, 2, 3)),
                estado BIT DEFAULT 0,
                especial BIT DEFAULT 0,
                fecha DATE,
                eliminado BIT DEFAULT 0,
                creado_en DATETIME DEFAULT GETDATE(),
                actualizado_en DATETIME DEFAULT GETDATE()
            );
        """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
        }
    }

    // Método para agregar una tarea
    public void agregar(Tarea tarea) throws SQLException {
        String sql = "INSERT INTO Tarea (titulo, prioridad, especial, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, tarea.getTitulo());
            pstmt.setInt(2, tarea.getPrioridad());
            pstmt.setBoolean(3, tarea.isEspecial());
            pstmt.setDate(4, tarea.getFecha() != null ? Date.valueOf(tarea.getFecha()) : null);
            pstmt.executeUpdate();
        }
    }

    // Método para restaurar una tarea eliminada
    public void restaurarTarea(int id) throws SQLException {
        String sql = "UPDATE Tarea SET eliminado = 0 WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Método para obtener una tarea por su ID
    public Tarea obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Tarea WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tarea(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("prioridad"),
                    rs.getBoolean("estado"),
                    rs.getBoolean("especial"),
                    rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null
                );
            }
            return null; // Retorna null si no se encuentra la tarea
        }
    }

    // Método para listar todas las tareas activas
    public List<Tarea> listar() throws SQLException {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM Tarea WHERE eliminado = 0 ORDER BY id";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tarea tarea = new Tarea(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("prioridad"),
                    rs.getBoolean("estado"),
                    rs.getBoolean("especial"),
                    rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null
                );
                tareas.add(tarea);
            }
        }
        return tareas;
    }

    // Método para alternar el estado de una tarea
    public void alternarEstado(int id) throws SQLException {
        String sql = "UPDATE Tarea SET estado = ~estado WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar una tarea (eliminación lógica)
    public void eliminarTarea(int id) throws SQLException {
        String sql = "UPDATE Tarea SET eliminado = 1 WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}