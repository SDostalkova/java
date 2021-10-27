/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 *  Třída PrikazZabij implementuje pro hru příkaz zabij.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class PrikazZabij implements IPrikaz {
    private static final String NAZEV = "zabij";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazZabij (HerniPlan plan) {
        this.plan = plan;

    }
    
    /**
     * tělo příkazu zabij
     * pokud nejsi v místnosti Hrobky mumie, nezabiješ nikoho - vypíše se 
     * pokud nemáš kopí při zabíjení mumie - mumie zabije tebe 
     * pokud máš kopí a jsi v místnosti Hrobky mumie tak vyhraješ hru
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc), tak ....
            return "Koho chceš zabít?";
        }

        
        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();

        if(aktualniProstor.getNazev() != "Hrobka_mumie") {
            return "Tady mumie není, musíš najít nejdříve její polohu.";

        }  
        else {
            if(!batoh.jeVBratohu("kopí")){
                plan.setProhra();
                return "Neměl si Kopí a tak když se mumie probouzela byl si hned její první oběť";           
            }
            else {

                plan.setVyhra();
                return "Zabil jsi mumii a zachránil svět! ";
            }
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
