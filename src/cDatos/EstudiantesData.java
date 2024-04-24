package cDatos;

// Importaciones necesarias
import cEntidad.Estudiante;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstudiantesData {
    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.txt";

    // Métodos para interactuar con el archivo de texto
    public void guardarEstudiante(Estudiante estudiante) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_ESTUDIANTES, true))) {
            writer.write(estudiante.toArchivo() + "\n");
        } catch (IOException e) {
            System.err.println("Error al guardar estudiante: " + e.getMessage());
        }
    }

    public List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ESTUDIANTES))) {
            String linea;
            while ((linea = reader.readLine())!= null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    Estudiante estudiante = new Estudiante(campos[0], campos[1], campos[2], campos[3]);
                    estudiantes.add(estudiante);
                } else {
                    System.out.println("La línea no tiene la estructura correcta: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar estudiantes: " + e.getMessage());
        }
        return estudiantes;
    }

    public Estudiante buscarEstudianteEnArchivo(String matricula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ESTUDIANTES))) {
            String linea;
            while ((linea = reader.readLine())!= null) {
                String[] campos = linea.split(",");
                if (campos.length == 4 && campos[2].equals(matricula)) {
                    return new Estudiante(campos[0], campos[1], campos[2], campos[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar estudiante en archivo: " + e.getMessage());
        }
        return null;
    }

    public void actualizarEstudianteEnArchivo(Estudiante estudiante) {
        List<Estudiante> estudiantes = cargarEstudiantes();
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getMatricula().equals(estudiante.getMatricula())) {
                estudiantes.set(i, estudiante);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_ESTUDIANTES))) {
            for (Estudiante e : estudiantes) {
                writer.write(e.toArchivo() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar estudiante en archivo: " + e.getMessage());
        }
    }

    public void eliminarEstudianteEnArchivo(String matricula) {
        List<Estudiante> estudiantes = cargarEstudiantes();
        estudiantes.removeIf(e -> e.getMatricula().equals(matricula));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_ESTUDIANTES))) {
            for (Estudiante e : estudiantes) {
                writer.write(e.toArchivo() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar estudiante en archivo: " + e.getMessage());
        }
    }
}