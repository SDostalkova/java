/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 *  Třída PrikazDej implementuje pro hru příkaz dej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class PrikazDej implements IPrikaz {
    private static final String NAZEV = "dej";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     * tělo příkazu dej
     * příkaz dej se skládá ze 3 částí - příkaz - věc - postava
     * pokud v místnosti není osoba, kterou chceme něco dát - vypíše se 
     * pokud nemám věc v batohu, kterou chci předat - vypíše se 
     * pokud věc, kterou nabízím, je věcí, kterou osoba chce, odstraní se věc z batohu 
     * poté se vypíše řeč postavy, buď po nebo před výměnou , podle toho jestli výměna proběhla 
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 ) {
            // pokud chybí druhé slovo a (věc), tak ....
            return "co mám dát a komu?";
        }

        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        String nabidka = parametry[0];
        String mluviciPostava = parametry[1];

        if(!aktualniProstor.obsahujePostavu(mluviciPostava)){
            return "Tato postava v místnosti není.";
        }

        else {
            String vypis = "";
            Postava aktivni = aktualniProstor.najdiPostavuVMistnosti(mluviciPostava);
            if (!batoh.jeVBratohu(nabidka)){
                return "Nabízenou věc nemáš!";
            }
            else {

                if (nabidka.equals(aktivni.getCoChce().getNazev())) {
                    aktivni.probehlaVymena = true;
                    batoh.odeberVec(nabidka);
                    vypis = aktivni.getMluv();
                }
                else{
                    vypis = "S tímhle ti neporadím, to není můj obor. Našel jsi jěště něco jiného? třeba ti poradím s tím.";
                }

            }
            return vypis;   
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
