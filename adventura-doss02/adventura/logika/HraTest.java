package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží k otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {

    private DotazyDoTestu dotazy = new DotazyDoTestu();
    private Hra hra1 = new Hra(dotazy);
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {

    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        dotazy.pridejOdpoved("Gíza");
        hra1.zpracujPrikaz("jdi Hrobka_ochránce");
        assertEquals("Hrobka_ochránce", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi Abdulův_přístřešek");

        assertEquals("Abdulův_přístřešek", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());

    }
    
    @Test 
    public void testVyhra(){
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber přívěsek");
        dotazy.pridejOdpoved("Gíza");
        hra1.zpracujPrikaz("jdi Hrobka_ochránce");
        assertEquals("Hrobka_ochránce", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej sarkofág");
        hra1.zpracujPrikaz("seber kopí");
        hra1.zpracujPrikaz("seber hádanka");
        hra1.zpracujPrikaz("prozkoumej hádanka");
        hra1.zpracujPrikaz("jdi Abdulův_přístřešek");
        assertEquals("Abdulův_přístřešek", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Abdul");
        hra1.zpracujPrikaz("dej hádanka Abdul");
        dotazy.pridejOdpoved("Sfinga");
        hra1.zpracujPrikaz("jdi Vstup_do_hrobky_mumie");
        assertEquals("Vstup_do_hrobky_mumie", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("odemkni Hrobka_mumie");
        hra1.zpracujPrikaz("jdi Hrobka_mumie");
        assertEquals("Hrobka_mumie", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zabij Mumie");
        assertTrue(hra1.getHerniPlan().jeVyhra());
    }
    
    @Test
    public void testProhra(){
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber přívěsek");
        dotazy.pridejOdpoved("Gíza");
        hra1.zpracujPrikaz("jdi Hrobka_ochránce");
        assertEquals("Hrobka_ochránce", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej socha");
        assertTrue(hra1.getHerniPlan().jeProhra());
    }
    
    @Test
    public void testProhra2(){
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber přívěsek");
        dotazy.pridejOdpoved("Gíza");
        hra1.zpracujPrikaz("jdi Hrobka_ochránce");
        assertEquals("Hrobka_ochránce", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej sarkofág");
        hra1.zpracujPrikaz("seber hádanka");
        hra1.zpracujPrikaz("prozkoumej hádanka");
        hra1.zpracujPrikaz("jdi Abdulův_přístřešek");
        assertEquals("Abdulův_přístřešek", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Abdul");
        hra1.zpracujPrikaz("dej hádanka Abdul");
        dotazy.pridejOdpoved("Sfinga");
        hra1.zpracujPrikaz("jdi Vstup_do_hrobky_mumie");
        assertEquals("Vstup_do_hrobky_mumie", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("odemkni Hrobka_mumie");
        hra1.zpracujPrikaz("jdi Hrobka_mumie");
        assertEquals("Hrobka_mumie", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zabij Mumie");
        assertTrue(hra1.getHerniPlan().jeProhra());
    }
}
