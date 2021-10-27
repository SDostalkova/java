package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Štěpánka Dostálková
 *@version    ZS 2020/21
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private ZadavaniDotazu zadDotazu;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazJdi(HerniPlan plan, ZadavaniDotazu zadDotazu) {
        this.plan = plan;
        this.zadDotazu = zadDotazu;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *  Některé místnosti mohou být zamčené či je potřeba heslo k průchodu do místnosti
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam chceš jít? Musíš zadat jméno místa";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Na tohle místo se nemůžeš vydat.";
        }
        else {
            String vypis="";
            if (sousedniProstor.sOtazkou()) {
                String odpoved = zadDotazu.odpovez(sousedniProstor.getOtazka());
                if (odpoved.trim().equals(sousedniProstor.getOdpoved())) {
                    vypis="Dokázal jsi zjistit kam se máš vydat! Bereš si své věci a vyrážíš na cestu.\n";
                    plan.setAktualniProstor(sousedniProstor);
                    vypis += sousedniProstor.dlouhyPopis();
                }
                else {
                    vypis = "Jsi uprostřed pouště a máš strašnou žízeň, proklínáš se, že jsi hádanku nevyřešit dobře,\n";
                    vypis += " a proto trčíš bůhví kde. Zkus to znovu a lépe.";
                }
            }
            else {
                if (sousedniProstor.jeZamcena()) {
                    return "dveře do místnosti "+sousedniProstor.getNazev()
                    +" jsou zamčené";
                }
                plan.setAktualniProstor(sousedniProstor);
                vypis = sousedniProstor.dlouhyPopis();
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
