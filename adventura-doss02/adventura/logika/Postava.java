/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy Postava představují jednotlivé postavy ve hře
 * 
 * "Postava" reprezentuje jednu postavu ve scénáři hry.
 *
 * @author    Štěpánka Dostálková
 * @version   ZS 2020/21
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================

    private String jmeno;

    private Vec coChce;

    private String mluvPred;
    private String mluvPo;
    private String recChce;
    private String recNechce;
    public Boolean probehlaVymena = false;

    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     */
    public Postava(String jmeno)
    {
        this.jmeno = jmeno;

    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * nastavuje atributy které má mít postava - které věci chce, co bude rikat
     */
    public void nastavVymenu(Vec coChce, String recNechce, String recChce, String mluvPred ,String mluvPo, Boolean probehlaVymena){

        this.coChce = coChce;
        this.recNechce = recNechce;
        this.recChce = recChce;
        this.mluvPred = mluvPred;
        this.mluvPo = mluvPo;
        this.probehlaVymena = probehlaVymena;
    }

    /**
     * vrací, co mluví postava, buď před výměnou nebo po výměně
     */
    public String getMluv(){
        if(probehlaVymena){
            return mluvPo;
        }
        else {
            return mluvPred;
        }
    }

    /**
     * vrací zda proběhla výměna s postavou
     */
    public Boolean getProbehlaVymena(){
        return probehlaVymena;
    }

    public String getJmeno(){
        return jmeno;
    }

    
    public Vec getCoChce(){
        return coChce;
    }

    //== Soukromé metody (instancí i třídy) ========================================
}
