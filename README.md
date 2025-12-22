<div align="center">

# 🎓 SISTEMA DE GESTIÓN UNIVERSITARIA (RMI)

### Trabajo Final - Desarrollo de Aplicaciones Distribuidas (DAD)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![GitKraken](https://img.shields.io/badge/GitKraken-179287?style=for-the-badge&logo=gitkraken&logoColor=white)
![RMI](https://img.shields.io/badge/Java_RMI-Remote-red?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-En_Desarrollo-yellow?style=for-the-badge)

<br>

<p align="center">
  <b>Una solución distribuida basada en invocación remota de objetos.</b><br>
  Abstracción de red mediante Java RMI, uso de interfaces compartidas y persistencia en memoria.
</p>

</div>

---

## 📋 Descripción del Proyecto

Este proyecto implementa la segunda fase de la gestión universitaria, evolucionando de Sockets a **Java RMI (Remote Method Invocation)**. El objetivo es abstraer la complejidad de la comunicación por red, permitiendo que las sedes (Clientes) invoquen métodos directamente sobre un objeto remoto alojado en el servidor central.

A diferencia de la versión anterior, aquí no se gestionan tramas de texto manuales; se comparte una **Interfaz Remota** (`IGestionUniversidad`) que actúa como contrato entre cliente y servidor.

---

## ⚙️ Arquitectura RMI

El sistema utiliza la arquitectura estándar de RMI, donde el servidor registra un objeto que implementa la lógica de negocio y el cliente obtiene una referencia (Stub) para usarlo como si fuera local.

<div align="center">

| Componente | Puerto | Descripción |
| :---: | :---: | :--- |
| **RMI Registry** | `1099` | Servicio de directorio donde el servidor publica el objeto remoto. |
| **Interfaz** | `N/A` | Contrato común (`IGestionUniversidad`) conocido por cliente y servidor. |
| **Servidor** | `Dinámico` | Implementa la interfaz (`UnicastRemoteObject`) y gestiona los datos. |
| **Cliente** | `Dinámico` | Realiza `lookup` para obtener el Stub y llamar a los métodos. |

</div>

### 🔄 Flujo de Ejecución
1.  **Start:** El servidor inicia el `LocateRegistry` en el puerto 1099.
2.  **Bind:** El servidor instancia `GestionUniversidadImpl` y lo registra con un nombre (ej. "Universidad").
3.  **Lookup:** El cliente contacta con el registro y descarga el *Stub* del objeto remoto.
4.  **Invoke:** El cliente llama a métodos como `addTitulacion()` de forma transparente.
5.  **Response:** El servidor ejecuta la lógica y devuelve el resultado (o lanza `RemoteException`).

---

## 🚀 Funcionalidades (Entrega Enero)

El sistema cumple con los requisitos de la entrega de enero, gestionando las siguientes entidades mediante el objeto remoto:

### 🎓 Gestión de Títulos
- [x] **Añadir Título:** `addTitulacion(Titulacion t)`
- [x] **Actualizar:** `updateTitulacion(Titulacion t)`
- [x] **Consultar:** `getTitulacion(String id)`
- [x] **Eliminar:** `removeTitulacion(String id)`
- [x] **Listar Todos:** `listTitulaciones()`

### 📚 Gestión de Asignaturas
- [x] **Añadir Asignatura:** `addAsignatura(Asignatura a)`
- [x] **Vincular a Título:** `addAsignaturaATitulo(...)`
- [x] **Eliminar:** `removeAsignatura(String id)`
- [x] **Listar:** `listAsignaturas()`

### 📝 Gestión de Matrículas
- [x] **Matricular Alumno:** `addMatricula(Matricula m)`
- [x] **Modificar:** `updateMatricula(Matricula m)`
- [x] **Consultar:** `getMatricula(String id)`

---

## 🛠️ Tecnologías y Herramientas

<div align="center">

| Tecnología | Uso en el proyecto |
| :--- | :--- |
| **Java RMI** | Middleware para invocación remota de métodos. |
| **Interfaces Java** | Definición del contrato de servicios distribuidos. |
| **UnicastRemoteObject** | Exportación de objetos remotos puntuales. |
| **Clases de Dominio** | Librería `.jar` externa para interoperabilidad. |
| **Java Collections** | Gestión de datos en memoria (`ArrayList`). |

</div>

---

## 👥 Autores

<div align="center">

  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/EnriqueBDeL">
          <img src="https://github.com/EnriqueBDeL.png" width="100px;" alt="Foto Enrique"/><br>
          <sub><b>EnriqueBDeL</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/Agata-gp">
          <img src="https://github.com/Agata-gp.png" width="100px;" alt="Foto Agata"/><br>
          <sub><b>Agata-gp</b></sub>
        </a>
      </td>
    </tr>
  </table>

  <br>
  <i>[ Desarrollado para la asignatura Desarrollo de Aplicaciones Distribuidas - UCAM ]</i>
  
</div>
