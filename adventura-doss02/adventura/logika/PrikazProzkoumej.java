/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * tělo příkazu
     * prozkoumáva věc, zda v ní náhodou není ukrytá další věc 
     * pokud je věc hádanka, vypíše se něco jinýho
     * prozkoumat můžeme věci, které máme buď v batohu, nebo jsou v místnosti ve které se nacházíme
     * pokud prozkoumáme sochu - socha oživne a zabije, prohra ve hře
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc), tak ....
            return "Co mám prozkoumat?";
        }

        String kProzkoumani = parametry[0];

        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(kProzkoumani.equals("hádanka")){

            return "Nedokážeš to přečíst, je to napsané v mrtvém jazyce. Jdi za Abdulem jestli to náhodou neumí přeložit.";
        }
        if(!aktualniProstor.obsahujeVec(kProzkoumani)){
            if(batoh.jeVBratohu(kProzkoumani)){
                Vec prozkoumana = batoh.obsahujeVecNazev(kProzkoumani);
                prozkoumana.prozkoumano(true);           
                return  prozkoumana.popisProzkoumej();
            }
            else {        
                return "Nic takového u okolo nevidím. Koukni se pořádně.";
            }
        }  
        else {
            Vec prozkoumana = aktualniProstor.najdiVecVeVeci(kProzkoumani);

            if(prozkoumana.getNazev().equals("socha")){
                plan.setProhra();
                return "Sáhl si na sochu a socha ožila! V mžiku tě zabila.. Ty neumíš číst? Jasně bylo u vchodu napsáno, že nemáš šahat na co nemáš";
            }
            else{

                prozkoumana.prozkoumano(true);           
                return  prozkoumana.popisProzkoumej();

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
