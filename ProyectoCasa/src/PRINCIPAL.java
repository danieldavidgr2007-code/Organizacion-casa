package prueba_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PRINCIPAL extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRINCIPAL frame = new PRINCIPAL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PRINCIPAL() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 647);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Smart Home");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(165, 0, 285, 144);
		contentPane.add(lblNewLabel);
		
		JLabel lblBiemvenidoASmart = new JLabel("Biemvenido a Smart Home realizaremos una simulacion de gasto energetico de tu casa");
		lblBiemvenidoASmart.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiemvenidoASmart.setForeground(new Color(0, 0, 0));
		lblBiemvenidoASmart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBiemvenidoASmart.setBounds(-80, 114, 814, 20);
		contentPane.add(lblBiemvenidoASmart);
		
		JLabel lblImgPequena_2 = new JLabel("");
		lblImgPequena_2.setOpaque(true);
		lblImgPequena_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgPequena_2.setBackground(new Color(154, 205, 50));
		lblImgPequena_2.setBounds(0, 0, 656, 150);
		contentPane.add(lblImgPequena_2);
		
		JButton btnNewButton = new JButton("ENPEZAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.setBackground(new Color(154, 205, 50));
		btnNewButton.setBounds(123, 212, 533, 204);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SALIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNewButton_1.setBackground(new Color(255, 102, 102));
		btnNewButton_1.setBounds(0, 490, 331, 67);
		contentPane.add(btnNewButton_1);
		
		JLabel lblImgPequena = new JLabel("casa Pequeña");
		lblImgPequena.setOpaque(true);
		lblImgPequena.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgPequena.setBackground(new Color(220, 220, 220));
		lblImgPequena.setBounds(123, 36, 81, 67);
		contentPane.add(lblImgPequena);

	}
}
