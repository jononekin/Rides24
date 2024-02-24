package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class DiruaSartuGUI extends JFrame {

      private JPanel contentPane;
      private JLabel JLabelDiruaSartu= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.DiruaSartu"));
      private JRadioButton rdbtnAtera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Atera"));
      private JRadioButton rdbtnSartu = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Sartu"));
      private JTextArea JTextAreaDiruKop = new JTextArea();
      
      /**
       * Launch the application.
       */
      public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        try {
                              DiruaSartuGUI frame = new DiruaSartuGUI();
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
      public DiruaSartuGUI() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            
            
            JLabelDiruaSartu.setBounds(128, 43, 143, 14);
            contentPane.add(JLabelDiruaSartu);
            
            
            rdbtnAtera.setBounds(35, 94, 109, 23);
            contentPane.add(rdbtnAtera);
            
            
            rdbtnSartu.setBounds(235, 94, 109, 23);
            contentPane.add(rdbtnSartu);
            
            
            JTextAreaDiruKop.setBounds(77, 189, 229, 22);
            contentPane.add(JTextAreaDiruKop);
      }
}