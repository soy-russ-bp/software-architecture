# Diagrama de clases del Broker

> El paquete `JavaAPI` entiéndase como `java.*`, clases de la api estándar de Java.


```mermaid
---
title: Broker
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

Broker *-- ExecutorService
Broker ..> ClientHandler
Broker ..> ThreadPoolExecutor
class Broker{
    -int PORT = 1234 $
    -ExecutorService pool $
    -List~Servicio~ servicios $
    +getServicios() Servicio$
    +main() void$
}

Runnable <|.. ClientHandler
ClientHandler o--> Servicio
note for ClientHandler "cuando se ejecute `run()` se ejecutará el servicio deseado"
class ClientHandler{
    -Socket clientSocket
    -ClientHandler(Socket socketClient) Constructor
    +buscarServicioRegistrado(String nombreServicio, List~Servicio~) Servicio
    +registrarServicio(String ip, int puerto, String nombreServicio, numeroVariables)
    +listarServicios(List~Servicio~ servicios) String
    +ejecutarServicio(String nombreServicio, Parametros) String
    +run() void
}

class Servicio{
    String ipServidor
    int puertoServidor
    String nombreServicio
    int numeroParametros
    int identificador
    +ejecutar(Parametros parametros) String
}
```