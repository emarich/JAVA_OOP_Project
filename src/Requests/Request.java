package Requests;

import java.io.Serializable;

public class Request implements Serializable {
    private int number;
    private String message;
    private RequestType requestType;
    private boolean accepted = false;


    //pri kazdom requeste sa dava vklad do katastra (penazny)
    /*type of requests -
     * - zriadenie zalozneho prava - ak si ho bude chciet ofice zobrat, kludne moze len tak ho previest
     * - manzelska zmluva - owned property sa stane spolocne pre dvoch ownerov*/
    //message

    /*
    vydanie stav povolenia
    povolenie ostranenia stavby
    zmena supisneho cisla
    ziadost o povolenie terennych uprav
     */

}
