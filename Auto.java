package projektarbeit;

public class Auto {
    private String bezeichnung;
    private String farbe;
    private double gewicht;
    private double preis;
    private boolean elektrisch;
    public double getPreis(){
        return preis;
    }
    public String getFarbe(){
        return farbe;
    }
    public String getBezeichnung(){
        return bezeichnung;
    }
    public double getGewicht(){
        return gewicht;
    }
    public boolean getElektrisch(){
        return elektrisch;
    }

    public Auto(String bezeichnung, String farbe, double gewicht, double preis, boolean elektrisch) {
        this.bezeichnung = bezeichnung;
        this.farbe = farbe;
        this.gewicht = gewicht;
        this.preis = preis;
        this.elektrisch = elektrisch;
    }
}
