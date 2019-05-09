package Requests;

import java.io.Serializable;

public class Request implements Serializable {
    private String message;
    private boolean accepted = false;


    //pri kazdom requeste sa dava vklad do katastra (penazny)
    /*type of requests -
    * - zmena ownera... buy, give, zamena
     * - vytvorenie alebo zaniknutie vecneho bremena - ak owner ma nehnutelnst na pozemku, ktory nevlastni, vznikne vecne bremeno,
     * ak odkupi ten pozemok, zbavuje sa vecneho bremena
     * - zriadenie zalozneho prava - ak si ho bude chciet ofice zobrat, kludne moze len tak ho previest
     * - manzelska zmluva - owned property sa stane spolocne pre dvoch ownerov*/
    //message

    /*
    buy property from other owner
    prevod vlastnickeho prava (ako predaj ale bez financneho prevodu)
    darovacia zmluva - majitel musi vystavit tuto zmluvu, aby mohol prejst bezplatny prevod
    navrh na vklad do katastra nehnutelnosti
    zamenna zmluva na pozemok

    navrh na vyadanie uzemneho rozhodnutia na stavbu/ kolaudacneho - ci vytvorit nehnutelnost alebo ho zmazat

    vydanie stav povolenia
    povolenie ostranenia stavby
    zmena supisneho cisla
    ziadost o povolenie terennych uprav
     */

}
