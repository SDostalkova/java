/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class BatohTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    @Test
    public void testPridejVec()
    {
        Batoh batoh1 = new Batoh ();
        Vec vec1 = new Vec ("Boty", true, false);

        assertEquals(true, batoh1.pridejVec(vec1));
        assertEquals(true, batoh1.jeVBratohu("Boty"));
    }

    @Test
    public void testJeVBatohuMisto(){
        Batoh batoh1 = new Batoh();

        Vec vec1 = new Vec ("vec1",true, false);

        Vec vec2 = new Vec ("vec2",true, false);

        Vec vec3 = new Vec ("vec3",true, false);

        Vec vec4 = new Vec ("vec4",true, false);

        batoh1.pridejVec(vec1);
        batoh1.pridejVec(vec2);
        batoh1.pridejVec(vec3);
        batoh1.pridejVec(vec4);

        assertEquals(false, batoh1.jeVBatohuMisto());
    }
}
