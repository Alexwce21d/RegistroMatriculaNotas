package cNegocio;

// Importaciones necesarias
import cEntidad.Estudiante;
import cDatos.EstudiantesData;
import java.util.List;

public class EstudiantesBusiness {
    // Instancia de la clase EstudiantesData para interactuar con la capa de datos
    private EstudiantesData estudiantesData;
    
    // Constructor de la clase
    public EstudiantesBusiness() {
        estudiantesData = new EstudiantesData();
    }
    
    // Métodos para realizar operaciones de negocio
    public void registrarEstudiante(Estudiante estudiante) {
        estudiantesData.guardarEstudiante(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantesData.cargarEstudiantes();
    }

    public Estudiante buscarEstudiantePorMatricula(String matricula) {
        return estudiantesData.buscarEstudianteEnArchivo(matricula);
    }

    public void actualizarNotaEstudiante(String matricula, String nota) {
        Estudiante estudiante = buscarEstudiantePorMatricula(matricula);
        if (estudiante!= null) {
            estudiante.setNota(nota);
            estudiantesData.actualizarEstudianteEnArchivo(estudiante);
        }
    }

    public void eliminarEstudiante(String matricula) {
        estudiantesData.eliminarEstudianteEnArchivo(matricula);
    }
}