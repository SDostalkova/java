package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Štěpánka Dostálková
 * @version ZS 2020/21
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private String otazka;
    private String odpoved;
    private ArrayList<Vec> seznamVeci = new ArrayList<Vec>();
    private Boolean zamcena = false;
    private Vec klic;
    private ArrayList<Postava> seznamPostav = new ArrayList<Postava>();
    private Postava postava;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();

    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru " + popis + ".\n \n"
        + popisVychodu() + " \n"
        + " Věci v místnosti: " + seznamVeci()
        + "\n Postava v místnosti: " +seznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Místa kam se můžeš vydat:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev() + ",";
            if (sousedni.jeZamcena()) {
                vracenyText += "(zamknuto)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * nastavuje otázku a odpověd pro přechod mezi místnostmi
     */
    public void nastavOtazku(String otazka, String odpoved) {
        this.otazka = otazka;
        this.odpoved = odpoved;
    }

    public String getOtazka() {
        return otazka;
    }

    public String getOdpoved() {
        return odpoved;
    }

    public boolean sOtazkou() { //určuje zda prostor má nějaké omezení k průchodu do další místnosti - nějakou hádanku
        return otazka != null;
    }

    /**
     * vloží věc do místnosti
     */
    public void vlozVec(Vec neco) {
        seznamVeci.add(neco);
    }

    /**
     * zjistí zda je v místnosti daná věc,
     * když není v místnosti prohledá věci jestli neukrývají další věci (v truhle(sarkofág))
     */
    public boolean obsahujeVec(String nazev) {
        if (najdiVecVMistnosti(nazev) == null) { // vec není v místnosti
            for (Vec vec : seznamVeci) {    // prohledávám věci v místnosti
                if (vec.obsahujeVec(nazev)) { // když nějaká věc obsahuje věc
                    return true;
                }
            }
            return false;  // věc není ani v prozkoumaných věcech (truhlách)
        }
        else {
            return true;
        }

    }

    /**
     * nejdříve zjistí zda je věz v místnosti, pokud v místnosti je(není null) a je prenositelna, tak vybere vec a odebere ji z místnosti,
     * pokud věc není v místnosti, pak se prohledájí věci jestli neobsahují další (sarkofág obsahuje kopí)
     */
    public Vec vyberVec (String nazev) {
        Vec vybranaVec = najdiVecVMistnosti(nazev);
        if (vybranaVec != null && vybranaVec.jePrenositelna()) {
            seznamVeci.remove(vybranaVec);
        }
        else {
            for (Vec vec : seznamVeci) { // hledám, zda není v nějaké věci
                vybranaVec=vec.vyberVec(nazev);
                if (vybranaVec != null) {
                    break;
                }
            }
        }
        return vybranaVec;
    }

    /**
     * hledá věc v další věci, pokud v té věci není jiná věc, vrátí původní věc
     */
    public Vec najdiVecVeVeci(String nazev) {
        Vec hledanaVec = najdiVecVMistnosti(nazev);
        if (hledanaVec == null){

            for (Vec vec : seznamVeci) { // hledám, zda není v nějaké věci
                hledanaVec=vec.vecVeVeci(nazev);
                if (hledanaVec != null) {
                    break;
                }
            }
        }
        return hledanaVec;
    }

    /**
     * prohledá seznam věcí v místnosti, pokud najde schodu názvů mezi hledanou věcí a věcí v místnosti -> vypíše se 
     */
    public Vec najdiVecVMistnosti(String nazev) {
        Vec vec = null;
        for (Vec neco : seznamVeci) {
            if (neco.getNazev().equals(nazev)) {
                vec = neco;
                break;
            }
        }
        return vec;
    }

    /**
     * vrátí seznam věcí, které jsou v místnosti 
     */
    private String seznamVeci() {
        String seznam = "";
        for ( Vec neco : seznamVeci ) {
            seznam = seznam + neco.getNazev()+", ";
        }
        return seznam;
    } 

    /**
     * vrátí seznam postav v místosti
     */
    private String seznamPostav() {
        String seznam = "";
        for (Postava nejaka : seznamPostav ) {
            seznam = seznam + nejaka.getJmeno()+", ";
        }
        return seznam;
    }

    /**
     * vrací zda je prostor zamčený nebo ne
     */
    public Boolean jeZamcena(){
        return zamcena;
    }

    /**
     * nastavuje zamčení či odemčení místnosti
     */
    public void zamknout(Boolean zamceno){
        this.zamcena = zamceno;
    }

    /**
     * prohledá seznam postav v místnosti, pokud najde schodu názvů mezi hledanou postavou a postavou v místnosti -> vypíše se 
     */

    public Postava najdiPostavuVMistnosti(String jmeno) {
        Postava postava = null;
        for (Postava nejaka : seznamPostav) {
            if (nejaka.getJmeno().equals(jmeno)) {
                postava = nejaka;

            }

        }

        return postava;
    }

    /**
     * vrací zda je v místnosti postava, kterou hledáme
     */
    public boolean obsahujePostavu(String jmeno) {
        if (najdiPostavuVMistnosti(jmeno) != null) { // postava není v místnosti
            return true; 
        }
        else {
            return false;
        }

    }

    /**
     * vkládá postavu do místnosti
     */
    public void vlozPostavu (Postava nejaka) {
        seznamPostav.add(nejaka);
    }

    public Postava getPostava(){
        return this.postava;
    }

    /**
     * nastavuje klíč, pro odemčení místnosti
     */
    public void nastavKlic(Vec klic) {
        this.klic = klic;
    }

    public Vec getKlic() {
        return klic;
    }

}

