/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.ArrayList;
import java.util.List;
/*******************************************************************************
 * Instance třídy Batoh představují vatoh ve kterém můžu přenášet věci mezi místnosti
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================

    private List<Vec> obsahBatohu;
    private static final int KAPACITA_BATOHU = 4;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh()
    {
        obsahBatohu = new ArrayList<Vec>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * přidá věc do batohu
     */
    public boolean pridejVec(Vec vec){
        return obsahBatohu.add(vec);
    }

    /**
     * vrátí zda je v batohu místo nebo ne 
     */
    public boolean jeVBatohuMisto(){
        return obsahBatohu.size() < KAPACITA_BATOHU;
    }

    public Vec odeberVec(String nazevVeci){
        for(Vec vec : obsahBatohu){
            if(vec.getNazev().equals(nazevVeci)){
                obsahBatohu.remove(vec);
                return vec;
            }
        }
        return null;
    }

    /**
     * vrací seznam věcí, které jsou v batohu
     */
    public List<Vec> getObsahBatohu(){
        return obsahBatohu;
    }

    /**
     *  je v bratohu - přepsala jsem se už jsem to tak nechala
     *  
     *  zjišťuje zda se věc nachází v batohu s daným názvem
     *  vrací true false
     */
    public Boolean jeVBratohu(String nazevVeci){
        for (Vec vec : obsahBatohu){
            if (vec.getNazev().equals(nazevVeci)){

                return true;
            }
        }
        return false;
    }

    /**
     * zjišťuje zda je daná věc v batohu
     * vrací věc
     */
    public Vec obsahujeVec(Vec neco){
        for (Vec vec : obsahBatohu){
            if (vec.equals(neco)){
                vec = neco;
                return neco;
            }
        }
        return null;
    }

    /**
     * zjišťuje zda se věc nachází v batohu s daným názvem
     * vrací věc
     */
    public Vec obsahujeVecNazev (String nazevVeci){
        Vec vec = null;
        for(Vec nejaka : obsahBatohu){
            if(nejaka.getNazev().equals(nazevVeci)){
                vec = nejaka;
                return vec;
            }
        }
        return vec;
    }

    //== Soukromé metody (instancí i třídy) ========================================
}
