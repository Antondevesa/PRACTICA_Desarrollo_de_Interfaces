# Practica Recuperación - Diseño de Interfaces

Aplicación de escritorio desarrollada en **JavaFX** para la gestión de un catálogo de vehículos y usuarios. Este proyecto implementa un patrón de arquitectura en capas (DAO, Servicios, Controladores) y conexión a base de datos MySQL.

## ✨ Funcionalidades Principales

* **🔐 Autenticación y Seguridad:**
    * Sistema de Login y Registro de nuevos usuarios.
    * Contraseñas encriptadas (Hasheadas) mediante utilidad de seguridad.
    * Gestión de sesiones globales para recordar al usuario activo.
* **🚗 Catálogo de Vehículos (CRUD):**
    * Visualización de coches mediante un sistema de tarjetas (`Cards`) dinámicas.
    * Filtro de búsqueda en tiempo real.
    * Opción para añadir nuevos vehículos al catálogo (Solo Administradores).
    * Sistema de "Favoritos": Los usuarios pueden votar su coche preferido, destacando visualmente el más popular de la comunidad.
* **👥 Gestión de Usuarios:**
    * Panel de administración para ver y gestionar los usuarios registrados en el sistema.
    * Asignación de roles (ADMIN / USER).

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java
* **Interfaz Gráfica:** JavaFX (FXML + CSS personalizado)
* **Gestor de Dependencias:** Maven
* **Base de Datos:** MySQL
* **Patrones de Diseño:** Singleton (Base de datos y Sesiones), DAO (Data Access Object), MVC (Model-View-Controller adaptado).

## 🚀 Instalación y Despliegue

Para ejecutar este proyecto en local, sigue estos pasos:

1.  **Base de Datos:**
    * Abre tu gestor de base de datos (MySQL Workbench, DBeaver, phpMyAdmin, etc.).
    * Importa el script `recuperacion_db.sql` incluido en la raíz del proyecto para generar las tablas (`User`, `Car`) y los datos de prueba.
    * Asegúrate de que las credenciales en la clase `DatabaseConnection.java` coinciden con tu configuración local de MySQL (usuario y contraseña).

2.  **Ejecución:**
    * Abre el proyecto en IntelliJ IDEA (o tu IDE preferido).
    * Refresca las dependencias de Maven.
    * Ejecuta la clase principal: `MainApp.java`.

## 📁 Estructura del Proyecto

El código está organizado en una arquitectura de n-capas para asegurar su escalabilidad y fácil mantenimiento:
* `controller`: Controladores de las vistas FXML.
* `dao`: Clases encargadas de las consultas SQL directas a la base de datos.
* `model`: Clases modelo (Car, User, Role).
* `service`: Lógica de negocio intermediaria entre Controladores y DAO.
* `util`: Herramientas globales (AppView, SessionManager, SecurityUtil, DatabaseConnection).

---
**Autor:** Antonio Devesa Varela
**Asignatura:** Desarrollo de Interfaces
**Curso:** DAM 2º 