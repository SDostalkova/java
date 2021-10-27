package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Štěpánka Dostálková
 *@version    ZS 2020/21
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private ZadavaniDotazu zadDotazu;
    private Hra hra;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */

    public Hra(ZadavaniDotazu zadDotazu) {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, zadDotazu));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZabij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítej ve hře Mumie se vrací!\n \n" 
        + "Postupuj podle pokynů Jamese Falowa abys dokázal najít kopí osudu, zabít mumii a zachránit svět!\n \n" 
        + "Přišel si domů z univerzity, kde učíš archeologii a vidíš na stole dopis. Přišel od tvého dobrého přítele z britské univerzity. Otevřeš dopis a čteš.\n \n" 
        +"Milý hráči, jsem na pokraji svých sil a jsem si jist, že budu mrtev až budeš toto číst, a proto píši tobě, člověku, kterému věřím ze všech nejvíce, jelikož nemám dědice.\n" + 
        "Již po generace se u nás v rodině přenáší odkaz, jsme ochránci kopí, které má největší moc na světě.\n" + 
        "Velký vládce starověkého Egypta se snaží z posledních sil vstřebat jeho sílu, aby mohl žít, bez něj se smí probudit pouze jednou za sto let. \n" +
        "Za 10 dní nastane sté výročí a mumie se ho bude chtít zmocnit. Najdi kopí dřív než banditi, kteří ho chtějí vzbudit. Věří, že budou moci mumii ovládat a sní její moc. \n"
        + "Nenech se zmást. Je to zkáza, která se nedá ovládat." 
        +"Proto musíš mumii zabít dříve než se k ní dostanou banditi."
        + "Kopí je pro mumii výhra ale i prokletí, jelikož jen ono kopí ho může zabít.\n" +
        "Kopí leží u prvního z našeho řádu. Najdi jeho hrobku a dostaneš se dál. Pokud si nebudeš vědět rady, najdi mého přítele Abdula, dalšího ochránce, žije v Káhiře.\n \n"
        + " Tvůj James Falow. \n \n"
        + "V obálce jsi našel i jakýsi přívěsek, má  velice divný tvar. \n \n Moc dlouho neotálej a vydej se na cestu k místu odpočinku prvního ochránce.\n \n" +
        "Pokud si nevíš rady napiš nápověda, objeví se ti manuál jak postupovat. \n \n " +
        herniPlan.getAktualniProstor().dlouhyPopis() + "\n";
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Můžeš si zahrát znova třeba objevíš něco nového!";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *  určuje ukončení hry pomocí prohry či výhry a jejich podmínky
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";

        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            if(herniPlan.jeVyhra()){
                konecHry = true;
                textKVypsani = "Přišel si k mumii a ubodal si ji k smrti svým mocným kopím. Ach, hrdino! Děkujeme ti za tvoje služby. Fakturu nám pošli poštou.";
            }
            if(herniPlan.jeProhra() && !herniPlan.getBatoh().jeVBatohuMisto()) {
                konecHry = true;
                textKVypsani = "Narval si batoh až k prasknutí, poztrácel jsi všechny věci. Příště nesbírej kraviny, ty génie!";
            }
            if(herniPlan.jeProhra() && herniPlan.getAktualniProstor().getNazev().equals( "Hrobka_chránce")){
                konecHry = true;
                textKVypsani = "Nedodržel si osobní prostor staroegyptských svoření. Probudils Anubise z jeho šlofíka. Na spící se nešahá. Stáhl tě sebou do podsvětí.";
            }
            if(herniPlan.jeProhra() && herniPlan.getAktualniProstor().getNazev().equals( "Hrobka_mumie")){
                konecHry = true;
                textKVypsani = "Neměl si mocné Kopí a snažil si se zabít Mumii? Co tě to napadlo.... bez kopí píchat do mumie nemůžeš! Krvelačná mumie na tobě zanechala smrtelné rány.";
            }
        }

        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }

        return textKVypsani;
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }

    public ZadavaniDotazu getZadDotazu(){
        return zadDotazu;
    }

}

