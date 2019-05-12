package Offices;

import CadasterObjects.Land;
import CadasterObjects.Landform;
import Requests.Request;
import Requests.RequestType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

        switch (landform.getShortName()) {
            case "arable":
                    if (request.getRequestType().equals(RequestType.BUILD)) {

                    } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                    }
                break;


            case "hop":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "vineyard":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "garden":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "grove":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "grass":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "woods":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "water":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "built":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;


            case "other":
                if (request.getRequestType().equals(RequestType.BUILD)) {

                } else if (request.getRequestType().equals(RequestType.TERRAIN)) {

                }
                break;
        }
    }
}



