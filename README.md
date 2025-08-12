# 📋 Gestor de Tareas

Una aplicación de escritorio moderna para gestionar tareas utilizando Java Swing, JDBC y SQL Server.

![Java](https://img.shields.io/badge/Java-17+-orange)
![SQL Server](https://img.shields.io/badge/SQL%20Server-2019+-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## 📖 Descripción del Proyecto

El **Gestor de Tareas** es una aplicación de escritorio diseñada para ayudar a los usuarios a organizar y gestionar sus tareas diarias de manera eficiente. La aplicación ofrece una interfaz intuitiva y funciones completas de gestión de tareas.

### Características principales:
- ✅ Crear tareas con título, prioridad y fecha
- 📋 Listar y visualizar tareas en tabla organizada
- 🔄 Alternar estados (Pendiente/Completado)
- ⭐ Marcar tareas como especiales
- 🗑️ Eliminación lógica de tareas
- ↩️ Deshacer eliminaciones
- 🎯 Sistema de prioridades (1-3)
- 📅 Fechas opcionales para tareas

### Tecnologías utilizadas:
- **Java 17+** - Lenguaje de programación principal
- **Swing** - Framework para interfaz gráfica
- **JDBC** - Conectividad con base de datos
- **SQL Server** - Sistema de gestión de base de datos
- **Apache Ant** - Herramienta de construcción

## 📋 Tabla de Contenido

1. [Requisitos](#-requisitos)
2. [Instalación](#-instalación)
3. [Configuración](#%EF%B8%8F-configuración)
4. [Ejecución](#%EF%B8%8F-ejecución)
5. [Funcionalidades](#-funcionalidades)
6. [Estructura del Proyecto](#-estructura-del-proyecto)
7. [Diagrama UML](#-diagrama-uml)
8. [Contribuciones](#-contribuciones)
9. [Licencia](#-licencia)

## 🔧 Requisitos

### Software requerido:
- **Java Development Kit (JDK) 17+** - [Descargar aquí](https://www.oracle.com/java/technologies/downloads/)
- **SQL Server 2019+** - [Descargar aquí](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)
- **SQL Server Management Studio (SSMS)** - [Descargar aquí](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms)

### Driver de base de datos:
- **Microsoft JDBC Driver para SQL Server** - [Descargar aquí](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server)

### Requisitos del sistema:
- Sistema operativo: Windows 10/11, macOS, Linux
- RAM: 4GB mínimo (8GB recomendado)
- Espacio en disco: 500MB

## 📦 Instalación

1. **Clonar el repositorio:**
```bash
git clone https://github.com/TheJPlay2006/Practica_aplicacion_escritorio.git
cd Practica_aplicacion_escritorio
```

2. **Descargar dependencias:**
   - Descarga el driver JDBC de SQL Server
   - Coloca el archivo `.jar` en la carpeta `lib/` del proyecto

## ⚙️ Configuración

### 1. Configurar la Base de Datos

Ejecuta el siguiente script en **SQL Server Management Studio (SSMS)**:

```sql
-- Crear la base de datos
CREATE DATABASE AgendaDB;
GO

-- Usar la base de datos
USE AgendaDB;
GO

-- Crear la tabla de tareas
CREATE TABLE Tarea (
    id INT IDENTITY(1,1) PRIMARY KEY,
    titulo NVARCHAR(255) NOT NULL,
    prioridad INT CHECK (prioridad IN (1, 2, 3)) NOT NULL,
    estado BIT DEFAULT 0,
    especial BIT DEFAULT 0,
    fecha DATE NULL,
    eliminado BIT DEFAULT 0,
    creado_en DATETIME DEFAULT GETDATE(),
    actualizado_en DATETIME DEFAULT GETDATE()
);
GO
```

### 2. Configurar la Conexión

En la clase `Main.java`, ajusta la URL de conexión según tu configuración:

**Para autenticación integrada de Windows:**
```java
private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
        + "databaseName=AgendaDB;"
        + "integratedSecurity=true;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";
```

**Para autenticación SQL Server:**
```java
private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
        + "databaseName=AgendaDB;"
        + "user=tu_usuario;"
        + "password=tu_contraseña;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";
```

### 3. Configurar el IDE

- Abre el proyecto en tu IDE preferido (NetBeans, IntelliJ IDEA, Eclipse)
- Agrega el archivo JAR del driver JDBC a las dependencias del proyecto
- Asegúrate de que Java 17+ esté configurado como JDK del proyecto

## ▶️ Ejecución

### Compilar y ejecutar con Ant:

```bash
# Compilar el proyecto
ant -f "PracticaProgra" -Dnb.internal.action.name=rebuild clean jar

# Ejecutar la aplicación
java -jar dist/PracticaProgra.jar
```

### Ejecutar desde IDE:
1. Abrir el proyecto en tu IDE
2. Ejecutar la clase principal `Main.java`
3. La ventana de la aplicación se abrirá automáticamente

## 🎯 Funcionalidades

### 1. 📝 Crear Tarea
- Ingresa título (obligatorio)
- Selecciona prioridad (1: Baja, 2: Media, 3: Alta)
- Marca como especial (⭐) si es necesario
- Asigna fecha opcional
- Validaciones automáticas de datos

### 2. 📋 Listar Tareas
- Visualización en tabla ordenada
- Muestra solo tareas activas (no eliminadas)
- Información completa de cada tarea
- Indicadores visuales de estado y prioridad

### 3. 🔄 Alternar Estado
- Cambia entre "Pendiente" y "Completado"
- Actualización instantánea en la interfaz
- Registro de fecha de actualización

### 4. 🗑️ Eliminar Tarea
- Eliminación lógica (no física)
- Mantiene historial en base de datos
- Confirmación antes de eliminar

### 5. ↩️ Deshacer Eliminación
- Recupera la última tarea eliminada
- Sistema basado en pila (Stack)
- Función limitada a la sesión actual

## 📁 Estructura del Proyecto

```
Practica_aplicacion_escritorio/
├── src/
│   ├── modelo/
│   │   ├── Tarea.java           # Clase modelo
│   │   └── TareaDAO.java        # Acceso a datos
│   ├── servicio/
│   │   └── TareaServicio.java   # Lógica de negocio
│   ├── vista/
│   │   └── GUI.java             # Interfaz gráfica
│   └── Main.java                # Clase principal
├── lib/
│   └── jdbc-driver.jar          # Driver JDBC
├── database/
│   └── database.sql             # Script de base de datos
├── build.xml                    # Configuración Ant
└── README.md                    # Documentación
```

## 📊 Diagrama UML

### Diagrama de Clases

```
┌─────────────────┐
│     Tarea       │
├─────────────────┤
│ - id: int       │
│ - titulo: String│
│ - prioridad: int│
│ - estado: boolean│
│ - especial: boolean│
│ - fecha: LocalDate│
├─────────────────┤
│ + validar(): void│
│ + toString(): String│
└─────────────────┘
         ↑
         │
┌─────────────────┐      ┌─────────────────┐
│   TareaDAO      │←────→│ TareaServicio   │
├─────────────────┤      ├─────────────────┤
│ + agregar()     │      │ + agregarTarea()│
│ + listar()      │      │ + obtenerTareas()│
│ + alternarEstado()│      │ + eliminarTarea()│
│ + eliminar()    │      │ + deshacerElim()│
└─────────────────┘      └─────────────────┘
                                ↑
                                │
                    ┌─────────────────┐
                    │      GUI        │
                    ├─────────────────┤
                    │ + cargarDatos() │
                    │ + btnAgregar()  │
                    │ + btnEliminar() │
                    │ + btnDeshacer() │
                    └─────────────────┘
```

## 👥 Contribuciones

¡Las contribuciones son bienvenidas! Para contribuir:

1. **Fork** el repositorio
2. Crea una **rama** para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit** tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. **Push** a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un **Pull Request**

### Lineamientos para contribuir:
- Sigue las convenciones de código Java
- Añade comentarios descriptivos
- Incluye pruebas si es aplicable
- Actualiza la documentación si es necesario

## 🐛 Reportar Problemas

Si encuentras un error o tienes una sugerencia, por favor:
1. Revisa los [issues existentes](https://github.com/TheJPlay2006/Practica_aplicacion_escritorio/issues)
2. Crea un nuevo issue con información detallada
3. Incluye pasos para reproducir el problema

## 📄 Licencia

Este proyecto está bajo la **Licencia MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.

```
MIT License

Copyright (c) 2024 TheJPlay2006

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 🙏 Agradecimientos

- **TheJPlay2006** - Desarrollo principal del proyecto
- Comunidad Java - Por las mejores prácticas y recursos
- Microsoft - Por SQL Server y documentación JDBC

## 📞 Contacto

- **GitHub**: [@TheJPlay2006](https://github.com/TheJPlay2006)
- **Proyecto**: [Gestor de Tareas](https://github.com/TheJPlay2006/Practica_aplicacion_escritorio)

---

⭐ **¡Dale una estrella si te resulta útil!** ⭐
