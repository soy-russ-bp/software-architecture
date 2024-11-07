package ada8.servidor.infraestructura.servicios;

import java.io.*;
import java.util.HashMap;
/*
    Clase que provee de metodos para manejar archivos txt donde se encuentran pares de datos
 */
public class BaseDatosImpl implements BaseDatos {

    @Override
    public Object leerBaseDatos(String url) {
        HashMap<String,String> baseDatos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            String linea = br.readLine();
            while (linea != null) {
                String[] valores = linea.split(",");
                String parUno = valores[0];
                String ParDos = valores[1];
                baseDatos.put(parUno, ParDos);
            }
        }catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return baseDatos;
    }

    @Override
    public void actualizarBaseDatos(String url,Object datos) {
        HashMap<String,String> nuevaBaseDatos = datos instanceof HashMap ? (HashMap<String,String>)datos : null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url))) {
            assert nuevaBaseDatos != null;
            for (String parUno : nuevaBaseDatos.keySet()) {
                bw.write(parUno + "," + nuevaBaseDatos.get(parUno));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarDato(String url,Object dato) {
        String nuevoDato = (String) dato;
        System.out.println("dato a agregar: " + nuevoDato);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url, true))) {
            bw.write(nuevoDato);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
        System.out.println("dato agregado: " + nuevoDato);
    }
}
