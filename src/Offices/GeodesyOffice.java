package Offices;

import CadasterObjects.Land;
import CadasterObjects.Landform;
import Requests.Request;
import Requests.RequestType;
import java.io.Serializable;
import java.util.Random;


/**
 * Táto trieda slúži len na posudzovanie požiadavok typu {@link RequestType#BUILD}, {@link RequestType#TERRAIN},
 * {@link RequestType#DEMOLITION}, kedy vygeneruje náhodné číslo a na základe jeho hodnoty a typu pozemku pošle
 * pravdivostnú hodnotu, či akceptuje alebo neakceptje požiadavku
 */
public class GeodesyOffice extends Office implements Serializable {
    private Request requests;
    private Land land;

    public GeodesyOffice(Land land) {
        this.land = land;
    }

    @Override
    public void acceptRequest(Request request) {
        this.requests = (request);
    }

    public boolean considerRequest(Request request) {
        acceptRequest(request);

        Landform landform = land.getLandform();

        Random random = new Random();
        int number = random.nextInt(10);

        switch (landform.getShortName()) {
            case "arable":
            case "grove":
            case "hop": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }

            case "vineyard": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 3) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }

            case "built":
            case "garden": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }


            case "grass": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 5) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 7) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }

            case "woods": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 5) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 9) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }


            case "water": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 5) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }


            case "other": {
                if (request.getRequestType().equals(RequestType.BUILD)) {
                    if (number > 5) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {
                    if (number > 5) {
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            }
        }

        return false;
    }
}



