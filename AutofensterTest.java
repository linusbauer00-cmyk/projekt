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

}