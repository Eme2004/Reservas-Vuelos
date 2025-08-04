🛫 Sistema de Reservas de Vuelo
📋 Descripción del Proyecto
Sistema completo de reservas de vuelo desarrollado en Java con interfaz gráfica Swing que permite a los usuarios buscar vuelos, realizar reservas, gestionar itinerarios y consultar vuelos reales en tiempo real desde OpenSky Network API.
🎯 Objetivo Técnico
Desarrollar un sistema integral de reservas de vuelo empleando Java con interfaz gráfica (Swing), manejo de eventos, conexión a base de datos relacional e integración de datos en tiempo real vía internet.
✨ Características Principales

✅ Gestión de Vuelos: Registro, búsqueda y administración de vuelos
✅ Sistema de Reservas: Crear, visualizar, modificar y cancelar reservas
✅ Búsqueda Avanzada: Filtros por aerolínea, origen, destino, fecha, precio
✅ Gestión de Usuarios: Registro y autenticación básica
✅ Validaciones: Prevención de reservas duplicadas y validaciones de horarios
✅ Manejo de Excepciones: Try-catch completo para robustez

🌟 Funcionalidades Adicionales (Puntos Extra)

✅ Integración API Real: Consulta de vuelos reales desde OpenSky Network
✅ Exportación PDF: Generación de reportes de reservas en PDF
✅ Sincronización en Tiempo Real: Datos actualizados de vuelos comerciales
✅ Interfaz Profesional: Diseño moderno con componentes Swing avanzados

🛠️ Tecnologías Utilizadas
Lenguajes y Frameworks

Java 8+ - Lenguaje de programación principal
Java Swing - Interfaz gráfica de usuario
JDBC - Conectividad con base de datos

Base de Datos

PostgreSQL 16+ - Base de datos relacional principal
pgAdmin 4 - Herramienta de administración de BD

Librerías Externas

iText 5.5.13 - Generación de documentos PDF
JSON 20230227 - Procesamiento de datos JSON para API
PostgreSQL JDBC Driver - Conector de base de datos

Herramientas de Desarrollo

NetBeans IDE - Entorno de desarrollo
Apache Ant - Herramienta de construcción
Git/GitHub - Control de versiones

🏗️ Arquitectura del Sistema
📁 Estructura de Paquetes
SistemaVuelos/
├── 📁 Source Packages
│   ├── 📦 Conexion/
│   │   └── ConexionBD.java      # Gestión de conexión a BD
│   ├── 📦 DAO/
│   │   ├── ReservaDAO.java      # Operaciones CRUD reservas  
│   │   ├── ReservaInfo.java     # Información de reservas
│   │   ├── VueloDAO.java        # Operaciones CRUD vuelos
│   │   └── usuarioDAO.java      # Operaciones CRUD usuarios
│   ├── 📦 Imagenes/
│   │   └── vuelo.png            # Recursos gráficos
│   ├── 📦 POJO/
│   │   ├── OpenSkyIntegration.java  # Integración API OpenSky
│   │   ├── Reservas.java        # POJO para reservas
│   │   ├── usuario.java         # POJO para usuarios
│   │   └── Vuelo.java           # POJO para vuelos
│   ├── 📦 Vista/
│   │   ├── Formulario.java      # Formulario principal
│   │   ├── Inicio.java          # Ventana de inicio
│   │   ├── InicioSesion.java    # Login de usuarios
│   │   ├── MisReservas.java     # Gestión de reservas personales
│   │   ├── Reserva.java         # Interfaz de reserva
│   │   ├── Ventanaprincipal.java    # Ventana principal del sistema
│   │   ├── VentanaBusqueda.java     # Búsqueda avanzada de vuelos
│   │   ├── VentanaReservas.java     # Gestión completa de reservas
│   │   └── VuelosReales.java        # Consulta de vuelos reales API
│   └── 📦 test/
│       └── PruebaConexion.java  # Pruebas de conectividad BD
├── 📁 Test Packages
├── 📁 Libraries
│   ├── itextpdf-5.5.13.jar     # Generación PDF
│   ├── json-20230227.jar       # Procesamiento JSON
│   └── postgresql-42.7.1.jar   # Conector PostgreSQL
└── 📁 Test Libraries
🎨 Patrones de Diseño Implementados

DAO (Data Access Object) - Paquete DAO/ con separación de lógica de acceso a datos
POJO (Plain Old Java Object) - Paquete POJO/ con objetos simples para entidades
MVC (Model-View-Controller) - Separación clara entre Vista, DAO y POJOs
Singleton - Gestión única de conexión a BD en ConexionBD.java

📊 Modelo de Base de Datos
Tablas Principales
🛩️ Tabla: vuelos
sqlCREATE TABLE vuelos (
    id_vuelo SERIAL PRIMARY KEY,
    numero_vuelo VARCHAR(10) NOT NULL UNIQUE,
    aerolinea VARCHAR(50) NOT NULL,
    origen VARCHAR(50) NOT NULL,
    destino VARCHAR(50) NOT NULL,
    fecha_salida DATE NOT NULL,
    hora_salida TIME NOT NULL,
    fecha_llegada DATE NOT NULL,
    hora_llegada TIME NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    asientos_disponibles INTEGER NOT NULL,
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);
👤 Tabla: usuarios
sqlCREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
🎫 Tabla: reservas
sqlCREATE TABLE reservas (
    id_reserva SERIAL PRIMARY KEY,
    id_usuario INTEGER REFERENCES usuarios(id_usuario),
    id_vuelo INTEGER REFERENCES vuelos(id_vuelo),
    cantidad_pasajeros INTEGER NOT NULL,
    fecha_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado_reserva VARCHAR(20) DEFAULT 'CONFIRMADA',
    precio_total DECIMAL(10,2) NOT NULL
);
🚀 Instalación y Configuración
📋 Prerrequisitos

Java JDK 8 o superior
PostgreSQL 16+
NetBeans IDE (recomendado)
Git

📥 Clonar el Repositorio
bashgit clone https://github.com/tu-usuario/sistema-reservas-vuelo.git
cd sistema-reservas-vuelo
🗄️ Configuración de Base de Datos

Crear Base de Datos:

sqlCREATE DATABASE sistema_reservas_vuelo;

Ejecutar Scripts de Creación:

bashpsql -U postgres -d sistema_reservas_vuelo -f scripts/crear_tablas.sql
psql -U postgres -d sistema_reservas_vuelo -f scripts/datos_prueba.sql

Configurar Conexión:
Editar Conexion/ConexionBD.java:

javaprivate static final String URL = "jdbc:postgresql://localhost:5432/sistema_reservas_vuelo";
private static final String USUARIO = "tu_usuario";
private static final String PASSWORD = "tu_password";
📚 Dependencias
Descargar JARs Necesarios:

PostgreSQL JDBC: postgresql-42.7.1.jar ✅ (Ya incluido)
iText PDF: itextpdf-5.5.13.jar ✅ (Ya incluido)
JSON Processing: json-20230227.jar ✅ (Ya incluido)

Tu Estructura de Librerías Actual:
Libraries/
├── itextpdf-5.5.13.jar     ✅ Generación PDF
├── json-20230227.jar       ✅ Procesamiento JSON  
└── postgresql-42.7.1.jar   ✅ Conector PostgreSQL
⚙️ Compilación con Ant
bash# Compilar proyecto
ant compile

# Crear JAR ejecutable
ant jar

# Ejecutar aplicación
ant run
📱 Manual de Usuario
🏠 Ventana Principal
La aplicación inicia con Ventanaprincipal.java que proporciona un menú principal con acceso a:

🔍 Buscar Vuelos (VentanaBusqueda.java): Interfaz de búsqueda avanzada
🎫 Mis Reservas (MisReservas.java): Gestión de reservas personales
✈️ Vuelos Reales (VuelosReales.java): Consulta de vuelos en tiempo real
👤 Inicio Sesión (InicioSesion.java): Autenticación de usuarios
📝 Formulario (Formulario.java): Registro de nuevos usuarios

🔍 Búsqueda de Vuelos

Seleccionar criterios de búsqueda (origen, destino, fecha)
Aplicar filtros adicionales (precio, aerolínea, horario)
Visualizar resultados en tabla interactiva
Seleccionar vuelo y proceder a reserva

🎫 Realizar Reserva

Seleccionar vuelo deseado
Ingresar datos del pasajero
Confirmar cantidad de asientos
Validar información y confirmar reserva
Generar comprobante en PDF

📊 Gestión de Reservas

Ver Reservas: Lista completa de reservas activas
Modificar: Cambiar fecha o cantidad de pasajeros
Cancelar: Anular reserva con confirmación
Exportar PDF: Generar reporte de todas las reservas

🌐 Vuelos Reales (Integración OpenSky)
Implementado en POJO/OpenSkyIntegration.java y Vista/VuelosReales.java:

Sincronizar: Obtener vuelos comerciales en tiempo real desde OpenSky Network
Visualizar: Tabla con callsign, rutas, altitud, velocidad
Exportar: Generar PDF con datos de vuelos reales
Actualizar: Refrescar datos automáticamente

🧪 Pruebas y Validaciones
✅ Casos de Prueba Implementados

Validación de Datos: Campos obligatorios y formatos
Reservas Duplicadas: Prevención de doble reserva
Disponibilidad: Verificación de asientos disponibles
Fechas: Validación de fechas futuras y horarios
Conexión BD: Manejo de errores de conectividad
API Externa: Timeout y manejo de errores de red

🔒 Seguridad

SQL Parametrizado: Prevención de inyección SQL
Validación de Entrada: Sanitización de datos de usuario
Manejo de Excepciones: Try-catch exhaustivo
Conexiones Seguras: Cierre automático de recursos

📸 Capturas de Pantalla
🏠 Ventana de Inicio
Pantalla principal con opciones de registro, login y salida
<img width="474" height="381" alt="image" src="https://github.com/user-attachments/assets/d2a3aca8-f82f-40ae-aee6-f1f60f9558be" />


🔍 Búsqueda de Vuelos
Interfaz de búsqueda avanzada con filtros
<img width="1063" height="537" alt="image" src="https://github.com/user-attachments/assets/2498d10a-a658-4175-81c7-1fbac17d9b15" />

🎫 Gestión de Reservas
Panel de administración de reservas personales
<img width="382" height="293" alt="image" src="https://github.com/user-attachments/assets/64e3d19b-0358-4146-be3d-bb8ee6233e2d" />

✈️ Vuelos Reales en Tiempo Real
Integración con OpenSky Network API
<img width="883" height="586" alt="image" src="https://github.com/user-attachments/assets/f057873d-118a-41dd-8ab4-6fcca6e07101" />

🔑 Inicio de Sesión
Autenticación de usuarios registrados
<img width="592" height="488" alt="image" src="https://github.com/user-attachments/assets/5c7ffa66-f50f-4bb8-9b24-ff05b6d8a1ef" />

📄 Reporte PDF Generado
Ejemplo de exportación de reservas a PDF
<img width="781" height="414" alt="image" src="https://github.com/user-attachments/assets/7b9f0cd4-1bee-4848-8bf0-e4590c618ecd" />
<img width="755" height="583" alt="image" src="https://github.com/user-attachments/assets/fb7a99d2-63fb-403f-a98b-8ec33c8bbc95" />


[Emesis Mairena Sevilla] - Desarrollador Principal

Implementación de interfaz gráfica
Integración con base de datos
Sistema de reservas core
Documentación



📝 Commits Realizados
✅ Initial commit - Estructura básica del proyecto
✅ Implementación modelo de datos y POJOs
✅ Creación de DAOs y conexión a PostgreSQL
✅ Desarrollo de interfaz principal con Swing
✅ Sistema de búsqueda y filtros avanzados
✅ Implementación de gestión de reservas
✅ Integración con OpenSky Network API
✅ Generación de reportes PDF con iText
✅ Validaciones y manejo de excepciones
✅ Documentación completa y screenshots
🔧 Resolución de Problemas
🐛 Problemas Comunes
Error de Conexión a BD:
Solución: Verificar que PostgreSQL esté ejecutándose
         Confirmar credenciales en ConexionBD.java
ClassNotFoundException para JDBC:
Solución: Verificar que postgresql-42.7.1.jar esté en Libraries/
         Reiniciar NetBeans si es necesario
         Verificar configuración del classpath
Error en API OpenSky:
Solución: Verificar conexión a internet
         La API puede tener límites de rate
📚 Referencias y Documentación
📖 Documentación Oficial

Java Swing Tutorial
PostgreSQL Documentation
iText PDF Documentation
OpenSky Network API

🔗 Enlaces Útiles

JDBC Tutorial
Maven vs Ant
Git Best Practices

📄 Licencia
Este proyecto está desarrollado con fines académicos como parte de la práctica de Programación en Java.
📞 Contacto
Para preguntas o sugerencias sobre el proyecto:

Email: [emmairenase@est.utn.ac.cr]
GitHub: [https://github.com/Eme2004/Reservas-Vuelos.git]


🏆 Criterios de Evaluación Cumplidos

✅ Implementación completa y funcional
✅ Uso correcto de Java Swing y diseño modular
✅ Conexión y manipulación de datos en BD
✅ Documentación clara del código y README
✅ Buenas prácticas de programación
✅ Innovación en interfaz y funcionalidades
✅ Integración web con API externa (Puntos Extra)
✅ Exportación a PDF (Puntos Extra)


Última actualización: [03/08/2025]
