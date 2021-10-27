/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazOdemkni představují ...
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, je potřeba zjistit, zda jsem v místnosti
     *                    vedle místnosti, která se má odemknout
     */    
    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  tělo příkazu
     *  Provádí příkaz "odemkni". Zjišťuji, zda z aktuální místnosti je
     *  východ do zadané místnosti. Pokud místnost existuje a je zamčená,
     *  tak se zjistí, zda v batohu je potřebný klíč. Pokud ano, odemknu
     *  místnost.
     *
     *@param parametry - jako  parametr obsahuje jméno místnosti,
     *                         do které se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "Co mám odemknout? Musíš zadat jméno místnosti";
        }

        String prostor = parametry[0];

        // hledám zadanou místnost mezi východy
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);
        Batoh batoh = plan.getBatoh();
        if (sousedniProstor == null) {
            return "Odsud nevedou dveře do místnosti "+ prostor +" !";
        }
        else {
            if (sousedniProstor.jeZamcena() ) {
                if (batoh.jeVBratohu(sousedniProstor.getKlic().getNazev())) {
                    sousedniProstor.zamknout(false);
                    return "Podařilo se ti otevřít dveře do místnosti "
                    + prostor + ". Nyní je cesta volná.";
                }
                else {
                    return "Pro odemčení dveří do "+ prostor +" potřebuješ mít "
                    + "u sebe "+ sousedniProstor.getKlic().getNazev();
                }
            }
            else {
                return "Místnost "+prostor+" již byla odemčená!!!";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
