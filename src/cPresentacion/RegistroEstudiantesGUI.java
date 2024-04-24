package cPresentacion;

// Importaciones necesarias
import cEntidad.Estudiante;
import cNegocio.EstudiantesBusiness;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;



public class RegistroEstudiantesGUI extends JFrame {
    // Campos de texto para ingresar información del estudiante
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField matriculaField;
    private JTextField notaField;
    // Botones para realizar acciones
    private JButton registrarButton;
    private JButton mostrarButton;
    private JButton limpiarButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    
    // Tabla para mostrar estudiantes
    private JTable estudiantesTable;
    
    // Instancia de la clase EstudiantesBusiness para interactuar con la capa de negocio
    private EstudiantesBusiness estudiantesBusiness;
    
    // Constructor de la clase
    public RegistroEstudiantesGUI() {
        // Inicializar componentes gráficos
        super("Registro de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Crear panel para campos de texto y botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Agregar campos de texto y botones al panel
        gbc.insets = new Insets(5, 5, 5, 5);

        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        matriculaField = new JTextField(20);
        notaField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(apellidoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(matriculaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Nota:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(notaField, gbc);

        JPanel buttonPanel = new JPanel();
        registrarButton = new JButton("Registrar");
        mostrarButton = new JButton("Mostrar");
        limpiarButton = new JButton("Limpiar");
        buscarButton = new JButton("Buscar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");
        buttonPanel.add(registrarButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(limpiarButton);
        buttonPanel.add(buscarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstudiantes();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });
        
        // Agregar tabla para mostrar estudiantes
        estudiantesTable = new JTable();
        add(new JScrollPane(estudiantesTable), BorderLayout.EAST);
        
        // Inicializar instancia de EstudiantesBusiness
        estudiantesBusiness = new EstudiantesBusiness();

        pack();
        setVisible(true);
    }
    // Métodos para realizar acciones
    // Validar campos de texto y registrar estudiante
    private void registrarEstudiante() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String matricula = matriculaField.getText();
        String nota = notaField.getText();

        if (validarCampos(nombre, apellido, matricula, nota)) {
            Estudiante estudiante = new Estudiante(nombre, apellido, matricula, nota);
            estudiantesBusiness.registrarEstudiante(estudiante);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Campos vacíos o nota inválida");
        }
    }

    private boolean validarCampos(String nombre, String apellido, String matricula, String nota) {
        if (nombre.isEmpty() || apellido.isEmpty() || matricula.isEmpty() || nota.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(nota);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
     // Mostrar estudiantes en la tabla
    private void mostrarEstudiantes() {
        estudiantesTable.setModel(new EstudiantesTableModel(estudiantesBusiness.getEstudiantes()));
    }
    
    // Limpiar campos de texto
    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        matriculaField.setText("");
        notaField.setText("");
    }
    
    // Buscar estudiante por matrícula
    private void buscarEstudiante() {
        String matricula = matriculaField.getText();
        Estudiante estudiante = estudiantesBusiness.buscarEstudiantePorMatricula(matricula);
        if (estudiante!= null) {
            nombreField.setText(estudiante.getNombre());
            apellidoField.setText(estudiante.getApellido());
            notaField.setText(estudiante.getNota());
        } else {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
        }
    }
    
    // Actualizar nota de estudiante
    private void actualizarEstudiante() {
        String matricula = matriculaField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String nota = notaField.getText();

        Estudiante estudiante = new Estudiante(nombre, apellido, matricula, nota);
        estudiantesBusiness.actualizarNotaEstudiante(matricula, nota);

        nombreField.setText("");
        apellidoField.setText("");
        matriculaField.setText("");
        notaField.setText("");
    }
    
    // Eliminar estudiante por matrícula
    private void eliminarEstudiante() {
        String matricula = matriculaField.getText();
        estudiantesBusiness.eliminarEstudiante(matricula);

        nombreField.setText("");
        apellidoField.setText("");
        matriculaField.setText("");
        notaField.setText("");
    }

    public static void main(String[] args) {
        new RegistroEstudiantesGUI();
    }
}

// Clase interna para el modelo de tabla
class EstudiantesTableModel extends AbstractTableModel {
    private List<Estudiante> estudiantes;

    public EstudiantesTableModel(List<Estudiante> estudiantes) {
       this.estudiantes = estudiantes;
    }

    @Override
    public int getRowCount() {
        return estudiantes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estudiante estudiante = estudiantes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return estudiante.getNombre();
            case 1:
                return estudiante.getApellido();
            case 2:
                return estudiante.getMatricula();
            case 3:
                return estudiante.getNota();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Matrícula";
            case 3:
                return "Nota";
            default:
                return null;
        }
    }
}
