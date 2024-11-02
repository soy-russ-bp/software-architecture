package ada8.servidor;

import ada8.servidor.infraestructura.parametros.ParametrosContarVotos;
import ada8.servidor.infraestructura.servicios.ServicioContarVotos;
import ada8.servidor.dominio.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Servicio servicio = new ServicioContarVotos();

        servicio.ejecutar(new ParametrosContarVotos()); // ejemplo
        
        System.out.println( "Hello World!" );
    }
}
