package projektarbeit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import java.util.ArrayList;

public class Autofenster extends JFrame{
    private JPanel hauptPanel;
    private JLabel farbeLabel;
    private JComboBox farbeComboBox1;
    private JLabel gewichtLabel;
    private JTextField gewichtTextField1;
    private JLabel elektrischLabel;
    private JRadioButton jaradioButton1;
    private JRadioButton neinradioButton2;
    private JLabel preisLabel;
    private JButton speichernbutton1;
    private JTextField preistextField1;
    private JTextField bezeichnungtextField1;
    private JLabel bezeichnungLabel;
    private JButton durchschnittbutton1;
    private JButton listebutton2;
    private ArrayList<Auto> autoliste;
    public ArrayList <Auto> getAutoliste() {
        return autoliste;
    }



    public Autofenster() { //Konstruktor
        autoliste = new ArrayList<Auto>();
        initObjekte();


        setTitle("Auto Liste");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setContentPane(hauptPanel);
        setVisible(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jaradioButton1);
        buttonGroup.add(neinradioButton2);

        speichernbutton1.addActionListener(new ActionListener() { //Die vom User erstellten Objekte werden gespeichert
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String bezeichnung = bezeichnungtextField1.getText();
                    String farbe = farbeComboBox1.getSelectedItem().toString();
                    double gewicht = Double.parseDouble(gewichtTextField1.getText());
                    double preis = Double.parseDouble(preistextField1.getText());
                    boolean elektrisch;
                    if (jaradioButton1.isSelected()) {
                        elektrisch = true;

                    } else {
                        elektrisch = false;
                    }
                    Auto auto = new Auto(bezeichnung, farbe, gewicht, preis, elektrisch);

                    autoliste.add(auto);
                    System.out.println("Auto gespeichert: " + bezeichnung);
                    System.out.println("Gespeicherte Autos: " + autoliste.size());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie gültige Zahlen ein", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE); //Damit falsche Eingaben nicht gespeichert werden können
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        durchschnittbutton1.addActionListener(new ActionListener() { //Durchschnittspreis berechnen
            @Override
            public void actionPerformed(ActionEvent e) {
                double summe = 0.0;
                for (Auto auto : autoliste){
                    summe += auto.getPreis();
                }
                double durchschnittspreis = summe / autoliste.size();
                JOptionPane.showMessageDialog(null, durchschnittspreis, "Der Durchschnittspreis beträgt", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        listebutton2.addActionListener(new ActionListener() { // Listenfenster wird geöffnet
            @Override
            public void actionPerformed(ActionEvent e) {
                ListenFenster fenster = new ListenFenster(autoliste);
                fenster.setVisible(true);
                //dispose();

            }
        });
    }


    public static void main(String[] args) {

        new Autofenster();


    }
    public void initObjekte(){ //erstellt 5 Objekte und speichert diese direkt

        autoliste.clear(); //damit wenn die Methode mehrmals aufgerufen wird, dass trotzdem nur 5 Objekte gespeichert sind
        this.getAutoliste().add(new Auto("Audi","weiß",2000,45000, false));
        this.getAutoliste().add(new Auto("Opel","orange",1300,12000, true));
        this.getAutoliste().add(new Auto("BMW","grün",2400,56000, false));
        this.getAutoliste().add(new Auto("Volvo","schwarz",2100,30000, true));
        this.getAutoliste().add(new Auto("Toyota","rot",2010,50000, false));

    }


}
