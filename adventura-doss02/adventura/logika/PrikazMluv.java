/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     * tělo příkazu mluv
     * pokud osoba se kterou chceme mluvit je v místnosti, vypíše text
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc), tak ....
            return "Ským mám mluvit?";
        }

        String mluviciPostava = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        if(!aktualniProstor.obsahujePostavu(mluviciPostava)){

            return  " Máš vidiny? nikdo takový tu není.";

        }  
        else {
            Postava aktivni = aktualniProstor.najdiPostavuVMistnosti(mluviciPostava);
            return "Otočí se k tobě a řekne: \n"
            + aktivni.getMluv();

        }
     
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}