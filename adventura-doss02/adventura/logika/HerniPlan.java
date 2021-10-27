package logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Štěpánka Dostálková
 *@version    ZS 2020/21
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Batoh batoh;
    private Postava postava;
    private Boolean vyhra = false;
    private Boolean prohra = false;
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();

    }

    public Batoh getBatoh(){
        return batoh;
    }

    public Postava getPostava(){
        return postava;
    }

    public Boolean jeVyhra(){ //udává zda je výhra true nebo false
        return vyhra;
    }

    public void setVyhra(){ //nastavuje vyhru na true
        vyhra = true;
    }

    public Boolean jeProhra(){ // udává zda je prohra true nebo false
        return prohra;
    }

    public void setProhra(){ // nastavuje prohru na true
        prohra = true;
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor domov = new Prostor("Domov","Domov, místo, kde všechno začalo.");
        Prostor hrobkaOchrance = new Prostor("Hrobka_ochránce", "hrobky prvního z našeho řádu. \n Prozkoumej to tu jestli nenajdej nějaké potřebné vodítka, nezapomeň si je vzít sebou. \n \n Ale pozor je tu nápis! NIČEHO SE NEDOTÝKEJ JESTLI NECHCEŠ ZEMŘÍT!\n");
        Prostor abduluvPristresek = new Prostor("Abdulův_přístřešek","staré skoro rozpadlé barabizny, tady by se měl Abdul ukrývat? \n \n Najednou na tebe někdo skočí ze zadu a začne tě škrtit. Jediná slova, která ze sebe vydáš jsou: POSÍLÁ MĚ FALOW! \n \n Přestane tě škrtit, omluví se a podává ti šálek čaje. \n \n");
        Prostor vstupDoHrobky = new Prostor("Vstup_do_hrobky_mumie",", kde se nachází vstup do hrobky. Jsou tu velké kované dveře, jsou zamčené. Zámek vypadá poněkud divně. Je totiž ve tvaru toho přívěsku ochránců! \n \n ");
        Prostor hrobkaFaraona = new Prostor("Hrobka_mumie","Hrobky Velkého Faraona. Dnes již nežije nikdo, kdo by znal jeho pravé jméno. \n Vyprávěli se o něm příběhy, ale lidé se ho báli natolik aby jeho jméno řekli nahlas. Po desítky let už je označován pouze jako Ta Mumie. \n Jeho hrobka je vykládaná zlatem a drahokamy. Poklad, který je poskvrněný krví jeho poddaných. \n ");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        domov.setVychod(hrobkaOchrance);
        domov.setVychod(abduluvPristresek);
        hrobkaOchrance.setVychod(domov);
        hrobkaOchrance.setVychod(abduluvPristresek);
        abduluvPristresek.setVychod(hrobkaOchrance);
        abduluvPristresek.setVychod(vstupDoHrobky);
        vstupDoHrobky.setVychod(abduluvPristresek);
        vstupDoHrobky.setVychod(hrobkaFaraona);
        hrobkaFaraona.setVychod(vstupDoHrobky);

        aktualniProstor = domov;  // hra začíná v domově

        //hádanky přiřazené při přechodu mezi prostory 
        hrobkaOchrance.nastavOtazku("V dopise od Falowa jsi našel indícii, kde by se mohlo nacházet místo odpočinku prvního ochránce. \n Její znění: Pod hvězdami 3 slavní leží, na místě kde písku je jak v moři kapek.. Tam královny je hlídají, neboť jsou poslední z velké sedmy. Tak proroctví zní, tam první z nás leží. \n Musí to být město, jinak to nedavá smysl... ale jaké? \n", "Gíza");
        vstupDoHrobky.nastavOtazku("Vstup do hrobky mumie hlídá strážce, který každý zná, však neví že pod ním vstup se nachazí do jeho hrobky. Z hádanky ze sarkofágu ochránce se dozvíš jaký tvor jí hlídá. \n Jak se tedy strážce hrobky nazývá? \n","Sfinga");

        // přiřazení vězí do místností
        Vec privesek = new Vec ("přívěsek",true, false);
        domov.vlozVec(privesek);

        Vec pistole = new Vec ("pistole", true, false);
        domov.vlozVec(pistole);

        Vec pacidlo = new Vec ("páčidlo", true, false);
        domov.vlozVec(pacidlo);

        Vec stul = new Vec ("stůl", false, false);
        domov.vlozVec(stul);

        Vec sarkofag = new Vec("sarkofág",false, false);
        hrobkaOchrance.vlozVec(sarkofag);

        Vec socha = new Vec("socha",false, false);
        hrobkaOchrance.vlozVec(socha);

        //přidávání věcí do sarkofágu

        Vec kopi = new Vec("kopí",true, false);
        sarkofag.vlozVec(kopi);

        Vec hadanka = new Vec("hádanka", true, false);
        sarkofag.vlozVec(hadanka);

        //zamknutí hrobky faraona a nastaveni klice
        hrobkaFaraona.zamknout(true);
        hrobkaFaraona.nastavKlic(privesek);

        //vytváření postav
        Postava abdul = new Postava ("Abdul");
        abdul.nastavVymenu( hadanka, "Tohle nevím jak vyřešit, to budeš muset jinam", "Měl bych mít překládací destičku, dej mi chvilku na přeložení.", "Našel si něco s čím si nevíš rady? Dej mi to, třeba ti s tím pomůžu.", "Překlad hádanky, kterou si nalezl v sarkofágu je tento: Tam kam se chceš vydat, není radno nalésti, stará pověst o tom povídá. \n Vstup mocný tvor hlídá \n pohled jako samotný Faraon mívá \n však tělo to má jako šelma \n jejíž hříva je k slunci nerozeznatelná \n nos hlava pozbyla \n však nenech se zmást \n každého by vmžiku zabila. \n", false);
        abduluvPristresek.vlozPostavu(abdul);

        Postava mumie = new Postava ("Mumie");
        mumie.nastavVymenu(kopi , "Můžeš mi dát co chceš, stejně tě zabiju.", "Můžeš mi dát co chceš, stejně tě zabiju.", "Zabiju tě!!", "Zajímavá věcička. Ty by ses mi mohl hodit. Ha ha.... ja nikoho nepotřebuju, zabiju tě stejně tak.", false);
        hrobkaFaraona.vlozPostavu(mumie);

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

}
