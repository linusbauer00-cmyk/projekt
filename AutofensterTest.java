package projektarbeit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutofensterTest {
    @Test
    void testInitObjekte(){
        Autofenster fenster = new Autofenster();

        fenster.initObjekte();

        assertEquals(5,fenster.getAutoliste().size());
    }

    void testdurchschnittspreis(){
        Autofenster fenster = new Autofenster();

        fenster.getAutoliste().clear();
        fenster.getAutoliste().add(new Auto("A", "rot", 1000, 10000, false));
        fenster.getAutoliste().add(new Auto("B", "blau", 1200, 20000, true));

        double ergebnis = fenster.durchschnittsPreis();

        assertEquals(15000.0, ergebnis);
    }


}
