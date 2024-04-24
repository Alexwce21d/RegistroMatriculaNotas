package cEntidad;

public class Estudiante {
    private String nombre;
    private String apellido;
    private String matricula;
    private String nota;
    
    // Constructores y métodos getters y setters
    public Estudiante(String nombre, String apellido, String matricula, String nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.nota = nota;
    }

    public Estudiante(String archivo) {
        String[] campos = archivo.split(",");
        this.nombre = campos[0];
        this.apellido = campos[1];
        this.matricula = campos[2];
        this.nota = campos[3];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String toArchivo() {
        return nombre + "," + apellido + "," + matricula + "," + nota;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", Matrícula: " + matricula + ", Nota: " + nota;
    }
}