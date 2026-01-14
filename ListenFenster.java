package projektarbeit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListenFenster extends JFrame{
    private JPanel hauptPanel;
    private JLabel gespeicherteAutosLabel;
    private JLabel filterLabel;
    private JComboBox filtercomboBox1;
    private JLabel auswahlLabel;
    private JComboBox auswahlcomboBox2;
    private JButton zurueckbutton1;
    private JTable gespeichertetable1;
    private JComboBox sortiercomboBox1;
    private JLabel sortierLabel;
    private ArrayList<Auto> autoliste;

    public ListenFenster(ArrayList<Auto> autoliste) {
        this.autoliste = autoliste;
        setTitle("Gespeicherte Liste");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setContentPane(hauptPanel);


        tabellenUpdate(this.autoliste);


        filtercomboBox1.addActionListener(new ActionListener() { //ActionListener, damit per Auswahl der Filter die Auswahl-ComboBox anders befüllt wird
            @Override
            public void actionPerformed(ActionEvent e) {
                String auswahl = (String) filtercomboBox1.getSelectedItem();
                auswahlcomboBox2.removeAllItems();

                if("Farbe".equals(auswahl)) {
                    auswahlcomboBox2.addItem("Schwarz");
                    auswahlcomboBox2.addItem("Weiß");
                    auswahlcomboBox2.addItem("Rot");
                    auswahlcomboBox2.addItem("Orange");
                    auswahlcomboBox2.addItem("Grün");
                } else if("Bezeichnung".equals(auswahl)) {
                    auswahlcomboBox2.addItem("A");
                    auswahlcomboBox2.addItem("B");
                    auswahlcomboBox2.addItem("C");
                    auswahlcomboBox2.addItem("D");
                    auswahlcomboBox2.addItem("E");
                    auswahlcomboBox2.addItem("F");
                    auswahlcomboBox2.addItem("G");
                    auswahlcomboBox2.addItem("H");
                    auswahlcomboBox2.addItem("I");
                    auswahlcomboBox2.addItem("J");
                    auswahlcomboBox2.addItem("K");
                    auswahlcomboBox2.addItem("L");
                    auswahlcomboBox2.addItem("M");
                    auswahlcomboBox2.addItem("N");
                    auswahlcomboBox2.addItem("O");
                    auswahlcomboBox2.addItem("P");
                    auswahlcomboBox2.addItem("Q");
                    auswahlcomboBox2.addItem("R");
                    auswahlcomboBox2.addItem("S");
                    auswahlcomboBox2.addItem("T");
                    auswahlcomboBox2.addItem("U");
                    auswahlcomboBox2.addItem("V");
                    auswahlcomboBox2.addItem("W");
                    auswahlcomboBox2.addItem("X");
                    auswahlcomboBox2.addItem("Y");
                    auswahlcomboBox2.addItem("Z");
                }  else if ("Motor".equals(auswahl)) {
                    auswahlcomboBox2.addItem("Elektrisch");
                    auswahlcomboBox2.addItem("Nicht elektrisch");
                } else if ("Alle".equals(auswahl)) {
                    auswahlcomboBox2.addItem("Alle");
                }
            }
        });

        auswahlcomboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtereTabelle();
            }
        });

        if (filtercomboBox1.getItemCount() > 0) {
            filtercomboBox1.setSelectedIndex(0);
        }
        setVisible(true);
        filtercomboBox1.setSelectedIndex(0);
        auswahlcomboBox2.setSelectedIndex(0);
        zurueckbutton1.addActionListener(new ActionListener() { // ActionListener für den Zurück-Button, der Listenfenster schließt
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); //schließt das Listenfenster
            }
        });


        sortiercomboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtereTabelle();
            }
        });
    }

    private void filtereTabelle() { //Methode zum filtern und sortieren der Liste
        String filterKategorie = (String) filtercomboBox1.getSelectedItem(); //Strings müssen erstellt werden damit Filterung und Sortierung funktionieren
        String filterWert = (String) auswahlcomboBox2.getSelectedItem();
        String sortierung = (String) sortiercomboBox1.getSelectedItem();

        if(filterWert == null) return;

        ArrayList<Auto> gefilterteListe = new ArrayList<>(); // neue Liste um die Autos speichern zu können

        for(Auto auto : autoliste) { //Nachfolgender Code filtert per Auswahl in der Auswahl-ComboBox die gespeicherten Autos
            boolean passt = false;

            if("Farbe".equals(filterKategorie)) {
                passt = auto.getFarbe().equalsIgnoreCase(filterWert);
            } else if("Bezeichnung".equals(filterKategorie)) {
                passt = auto.getBezeichnung().toLowerCase().startsWith(filterWert.toLowerCase());
            } else if("Motor".equals(filterKategorie)) {
                boolean filterElektrisch = filterWert.equalsIgnoreCase("elektrisch");
                passt = auto.getElektrisch() == filterElektrisch;
            } else if("Alle".equals(filterKategorie)) { //Damit man auch alles Autos ungefiltert anzeigen lassen kann
                passt = true;
            }

            if(passt) gefilterteListe.add(auto);
        }

        Collections.sort(gefilterteListe, new Comparator<Auto>() { //Nachfolgender Code sortiert die angezeigten gespeicherten Autos
            @Override
            public int compare(Auto a1, Auto a2) {
                switch(sortierung){
                    case "Preis aufsteigend": return Double.compare(a1.getPreis(), a2.getPreis());
                    case "Preis absteigend": return Double.compare(a2.getPreis(), a1.getPreis());
                    case "Gewicht aufsteigend": return Double.compare(a1.getGewicht(), a2.getGewicht());
                    case "Gewicht absteigend": return Double.compare(a2.getGewicht(), a1.getGewicht());
                    default: return 0;
                }
            }
        });
        tabellenUpdate(gefilterteListe);
    }

    public void tabellenUpdate(ArrayList<Auto> liste) { //Methode aktualisiert die Liste aktualisiert; Diese Methode funktioniert nur mit der Methode filteretabelle und stellt die Autos aus der Liste "gefilterte Liste" auf der Tabelle da
        String[] columnnames = {"Farbe", "Preis(€)", "Gewicht(kg)", "Motor", "Bezeichnung"}; // gibt der Tabelle ihre Spaltennamen
        DefaultTableModel model = new DefaultTableModel(columnnames, 0);

        for(Auto a : liste) { //ordnet jeder Spalte die Attribute der Autos zu
            Object[] rowData = {
                    a.getFarbe(),
                    a.getPreis(),
                    a.getGewicht(),
                    a.getElektrisch() ? "Elektrisch" : "nicht elektrisch",
                    a.getBezeichnung()
            };
            model.addRow(rowData);
        }
        gespeichertetable1.setModel(model);
    }


    public static void main (String[] args) {
        Autofenster autofenster = new Autofenster();
        ListenFenster fenster = new ListenFenster(autofenster.getAutoliste());
        fenster.setVisible(true);



    }


}
