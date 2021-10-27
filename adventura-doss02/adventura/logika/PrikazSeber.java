package logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Štěpánka Dostálková
 *@version    ZS 2020/21
 */
class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * tělo příkazu seber
     * zjistí zda je v batohu místo na sbíranou věc, pokud není - hra skončí - batoh je přeplněn a prasnul -> ztráta věcí nemůžeš pokračovat
     * když je v batohu místo tak zjistí zda věc, kterou chceme sebrat, v místnosti je
     * pokud je odebere se z místnosti a přidá se do batohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc), tak ....
            return "Co mám sebrat?";
        }

        String sbiranaVec = parametry[0];

        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(!batoh.jeVBatohuMisto()){
            plan.setProhra();
            return "Batoh už máš narvaný k prasknutí. Příště nesbírej kraviny, ty génie.";

        }  
        else {

            Vec pridavana = aktualniProstor.vyberVec(sbiranaVec); //tuhle metodu musíte přidat - odeberete z místnosti a přidáte do batohu
            if(pridavana == null){
                return "Je vidět, že pracuješ hlavou a ne rukama. Tohle vzít nemůžeš. Možná někde jinde." ;
            }
            else{
                batoh.pridejVec(pridavana);
                return "Věc byla přidána do batohu.\n"
                + "V batohu teď máš " + batoh.getObsahBatohu();
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
