package Tiradas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class CalculadoraTiradasApp extends JFrame {

	private static final int COSTE_TIRADA = 160;
	private static final int SASEGURADO = 90;
	private static final int SPROMOASEGURADO = 180;

	private JComboBox<String> idiomaBox;
	private JTextField peliculasField;
	private JTextArea resultadoArea;
	private JButton calcularBtn;
	private JButton actualizarBtn;

	// URL del repositorio o de la página de descargas de GitHub
	private static final String GITHUB_URL = "https://github.com/pamygam/PolycromeCalculatorForZ.Z.Z";

	public CalculadoraTiradasApp() {
		setTitle("Calculadora de Tiradas");
		setSize(400, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Panel superior (idioma)
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Idioma / Language:"));
		idiomaBox = new JComboBox<>(new String[]{"Español", "English"});
		topPanel.add(idiomaBox);
		add(topPanel, BorderLayout.NORTH);

		// Panel central (entrada y botones)
		JPanel centerPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		centerPanel.add(new JLabel("Películas / Polys:"));
		peliculasField = new JTextField();
		centerPanel.add(peliculasField);

		calcularBtn = new JButton("Calcular / Calculate");
		centerPanel.add(calcularBtn);

		actualizarBtn = new JButton("Actualizar / Update");
		centerPanel.add(actualizarBtn);

		add(centerPanel, BorderLayout.CENTER);

		// Área de resultados
		resultadoArea = new JTextArea();
		resultadoArea.setEditable(false);
		resultadoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(resultadoArea, BorderLayout.SOUTH);

		// Acciones de botones
		calcularBtn.addActionListener(e -> calcular());
		actualizarBtn.addActionListener(e -> abrirGitHub());
	}

	// Método de cálculo
	private void calcular() {
		boolean esEspanol = idiomaBox.getSelectedIndex() == 0;

		try {
			int peliculas = Integer.parseInt(peliculasField.getText());
			int tiradas = peliculas / COSTE_TIRADA;
			int sobrante = peliculas % COSTE_TIRADA;

			StringBuilder resultado = new StringBuilder();

			if (esEspanol) {
				resultado.append("Resultados:\n");
				resultado.append("Tiradas posibles: ").append(tiradas).append("\n");
				resultado.append("Películas sobrantes: ").append(sobrante).append("\n");

				if (tiradas >= SASEGURADO) {
					resultado.append("Tienes un S asegurado\n");
				} else {
					resultado.append("No tienes S asegurado\n");
				}

				if (tiradas >= SPROMOASEGURADO) {
					resultado.append("Tienes el promocional asegurado\n");
				}
			} else {
				resultado.append("Results:\n");
				resultado.append("Possible pulls: ").append(tiradas).append("\n");
				resultado.append("Remaining polys: ").append(sobrante).append("\n");

				if (tiradas >= SASEGURADO) {
					resultado.append("You have a guaranteed S\n");
				} else {
					resultado.append("You do not have a guaranteed S\n");
				}

				if (tiradas >= SPROMOASEGURADO) {
					resultado.append("You have the promotional guaranteed\n");
				}
			}

			resultadoArea.setText(resultado.toString());

		} catch (NumberFormatException ex) {
			if (idiomaBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Introduce un número válido");
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a valid number");
			}
		}
	}

	private void abrirGitHub() {
		try {
			Desktop.getDesktop().browse(new URI(GITHUB_URL));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se pudo abrir la página de GitHub / Cannot open GitHub page");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CalculadoraTiradasApp().setVisible(true));
	}
	
	
}