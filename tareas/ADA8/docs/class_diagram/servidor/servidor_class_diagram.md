# Diagrama de clases del Servidor

> El paquete `JavaAPI` entiéndase como `java.*`, clases de la api estándar de Java.

```mermaid
---  
title: Servidor

---

classDiagram

namespace JavaAPI{
    class Executor
    class ExecutorService
    class Runnable
    class AbstractExecutorService
    class ThreadPoolExecutor
}
Executor <|-- ExecutorService
class ExecutorService{
    <<interfaz>>
    execute(Runnable command) void
}
Executor --> Runnable
class Executor{
    <<interfaz>>
    execute(Runnable command) void
}
class Runnable{
    <<interfaz>>
    +run() void
}
AbstractExecutorService <|-- ThreadPoolExecutor
class ThreadPoolExecutor
ExecutorService <|-- AbstractExecutorService
class AbstractExecutorService{
    <<abstract>>
}
ServidorImpl *-- ExecutorService
ServidorImpl ..> ClientHandler
ServidorImpl ..> ThreadPoolExecutor
Servidor <|.. ServidorImpl
class ServidorImpl{
    -int PORT = 1234 $
    -ExecutorService pool $
    -String IP_BROKER $
    -int PUERTO_BROKER $
    
    +main() void$
}

class Servidor{
    <<abstract>>
    -List~Servicio~ servicios 
    + registrarServiciosAlBroker() void 
    + getServicios() List~Servicio~
}

Servicio --> Parametros
Servicio --> Mensaje
class Servicio{
    <<abstract>>
    -int identificador
    - String archivoURL
    +ejecutar(Parametros parametros) Mensaje
}
class Parametros{
    <<interfaz>>
}
class ServicioContarVotos
class ServicioListarEventos
class ServicioVotar
class ServicioRegistrarEvento
Servicio <|.. ServicioContarVotos
Servicio <|.. ServicioListarEventos
Servicio <|.. ServicioVotar
Servicio <|.. ServicioRegistrarEvento
ServicioContarVotos --> ParametrosContarVotos
ServicioListarEventos --> ParametrosListarEventos
ServicioVotar --> ParametrosVotar
ServicioRegistrarEvento --> ParametrosRegistrarEvento

 ParametrosContarVotos ..|> Parametros
 ParametrosListarEventos ..|> Parametros
 ParametrosVotar ..|> Parametros
 ParametrosRegistrarEvento ..|> Parametros

Runnable <|.. ClientHandler
ClientHandler o--> Servicio
ClientHandler --> MensajeMapper
ClientHandler --> Mensaje
ClientHandler --> Servidor
note for ClientHandler "cuando se ejecute `run()` se ejecutará el servicio deseado"
class ClientHandler{
    -Servidor servidor
    -Socket clientSocket
    -ClientHandler(Socket socketClient, Servidor servidor) Constructor
    +listarServicios(String palabraClave) Mensaje
    +ejecutarServicio(String nombreServicio, Parametros parametros) Mensaje
    +run() void
}

Mensaje --> Variable
class Mensaje{

    String servicio
    int numeroVariables
    List~Variable~ contenido
}

class Variable{
    String variable
    String valor
}

MensajeMapper --> Mensaje
class MensajeMapper{
    +deJsonAObjeto(String) Mensaje
    +deObjetoAJson(Mensaje) String
}
```