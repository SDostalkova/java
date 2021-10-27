/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;

/*******************************************************************************
 * pomocná třída k testování
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class DotazyDoTestu implements ZadavaniDotazu
{
    //== Datové atributy (statické i instancí)======================================
    private List<String> odpovedi = new ArrayList<String>();
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public DotazyDoTestu()
    {
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String odpovez(String otazka) { 
        if (odpovedi.isEmpty()) {
            return "";
        }
        else {
            return odpovedi.remove(0);
        }
    }

    
    public void pridejOdpoved(String odpoved) { // přidává odpověď
        odpovedi.add(odpoved);
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
