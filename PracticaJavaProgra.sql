-- Crear la base de datos
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'AgendaDB')
BEGIN
    CREATE DATABASE AgendaDB;
END;
GO

-- Usar la base de datos
USE AgendaDB;
GO

-- Crear la tabla Tarea
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Tarea' AND xtype='U')
BEGIN
    CREATE TABLE Tarea (
        id INT IDENTITY(1,1) PRIMARY KEY, -- Clave primaria autoincremental
        titulo NVARCHAR(255) NOT NULL, -- Título de la tarea (no puede ser nulo)
        prioridad INT NOT NULL CHECK (prioridad IN (1, 2, 3)), -- Prioridad: 1=Alta, 2=Media, 3=Baja
        estado BIT DEFAULT 0, -- Estado: 0=Pendiente, 1=Hecho
        especial BIT DEFAULT 0, -- Marca especial (★): 1=Sí, 0=No
        fecha DATE NULL, -- Fecha opcional
        eliminado BIT DEFAULT 0, -- Eliminación lógica: 1=Eliminado, 0=Activo
        creado_en DATETIME DEFAULT GETDATE(), -- Fecha de creación
        actualizado_en DATETIME DEFAULT GETDATE() -- Fecha de última actualización
    );
END;
GO

-- Índice en el campo 'titulo' para búsquedas rápidas
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_Tarea_Titulo')
BEGIN
    CREATE INDEX IX_Tarea_Titulo ON Tarea (titulo);
END;
GO

-- Procedimiento almacenado para insertar una nueva tarea
IF NOT EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'InsertarTarea')
BEGIN
    EXEC('
    CREATE PROCEDURE InsertarTarea
        @titulo NVARCHAR(255),
        @prioridad INT,
        @especial BIT,
        @fecha DATE
    AS
    BEGIN
        INSERT INTO Tarea (titulo, prioridad, especial, fecha)
        VALUES (@titulo, @prioridad, @especial, @fecha);
    END;
    ');
END;
GO

-- Procedimiento almacenado para listar tareas activas
IF NOT EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'ListarTareasActivas')
BEGIN
    EXEC('
    CREATE PROCEDURE ListarTareasActivas
    AS
    BEGIN
        SELECT id, titulo, prioridad, estado, especial, fecha, creado_en, actualizado_en
        FROM Tarea
        WHERE eliminado = 0
        ORDER BY id;
    END;
    ');
END;
GO

-- Procedimiento almacenado para eliminar una tarea (eliminación lógica)
IF NOT EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'EliminarTarea')
BEGIN
    EXEC('
    CREATE PROCEDURE EliminarTarea
        @id INT
    AS
    BEGIN
        UPDATE Tarea
        SET eliminado = 1
        WHERE id = @id;
    END;
    ');
END;
GO

-- Procedimiento almacenado para alternar el estado de una tarea (Hecho/Pendiente)
IF NOT EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'AlternarEstadoTarea')
BEGIN
    EXEC('
    CREATE PROCEDURE AlternarEstadoTarea
        @id INT
    AS
    BEGIN
        UPDATE Tarea
        SET estado = ~estado, -- Alterna entre 0 y 1
            actualizado_en = GETDATE()
        WHERE id = @id;
    END;
    ');
END;
GO


EXEC InsertarTarea @titulo = 'Prueba', @prioridad = 1, @especial = 1, @fecha = '2025-10-01';
