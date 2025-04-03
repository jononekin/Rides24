package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Eskaera;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ErrEgKon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrEgKon frame = new ErrEgKon();
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
	public ErrEgKon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox Erreserbak = new JComboBox();
		Erreserbak.setBounds(10, 85, 416, 33);
		BLFacade facade = MainGUI.getBusinessLogic();
		/*List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		JcomboBox.removeAllItems();
		for (Bidaiari bidaiari : bidaiariList) {
		    for (Eskaera eskaera : bidaiari.getEskaerak()) {
		        comboBox.addItem(eskaera);
		    }
		}*/
		contentPane.add(Erreserbak);
		
		JLabel Erreserbak_txt = new JLabel("New label");
		Erreserbak_txt.setBounds(191, 36, 49, 14);
		contentPane.add(Erreserbak_txt);
	}
}
