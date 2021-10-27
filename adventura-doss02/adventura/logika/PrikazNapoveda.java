package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Štěpánka Dostálková
 *@version    ZS 2020/21
 *  
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;

    /**
     *  Konstruktor třídy
     *  
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli. 
     */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je zabít mumii a zachránit svět před zlem. K zabití mumie potřebuješ najít Kopí a místo kde se mumie nachází. \n"
        + "\n K hraní hry zadáváš příkazy. piš přesně a neskloňuj názvy věcí, místností a jméma postav, dodržuj velké a malé písmena a pořadí ve kterém slova píšeš."
        + "\n \n"
        + "Můžeš zadat tyto příkazy: "
        + platnePrikazy.vratNazvyPrikazu() + "\n K provedení příkazu zadáš příkaz a název místnosti, předmětu či postavy. U příkazu dej zadávaš příkaz, název věci, kterou předáváš postavě a jméno postavy. \n"
        + "\n Nezapoměň si užít hru! \n";
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
