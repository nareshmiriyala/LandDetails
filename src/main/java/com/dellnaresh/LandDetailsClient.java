package com.dellnaresh;

import com.dellnaresh.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by nmiriyal on 1/03/2017.
 */
@Component
public class LandDetailsClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(LandDetailsClient.class);
    public static final String URI = "http://meebhoomi.ap.gov.in/UtilityWebService.asmx";

    public GetDistrictsResponse getDistricts() {
        GetDistricts request = new GetDistricts();
        GetDistrictsResponse response = (GetDistrictsResponse) callWebservice(request, "districts");
        return response;
    }

    private Object callWebservice(Object request, String str) {
        log.info("Requesting {}", str);
        String action = "http://tempuri.org/Get" + getFirstLettercap(str);
        return getWebServiceTemplate()
                .marshalSendAndReceive(URI,
                        request,
                        new SoapActionCallback(action));
    }

    private String getFirstLettercap(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public GetMandalsResponse getMandals(String district) {
        GetMandals request = new GetMandals();
        request.setKnownCategoryValues("District:" + district);
        request.setCategory("Mandal");

        GetMandalsResponse response = (GetMandalsResponse) callWebservice(request, "mandals");
        return response;
    }

    public GetVillagesResponse getVillages(String district, String mandal) {
        GetVillages request = new GetVillages();
        request.setKnownCategoryValues("District:" + district + ";Mandal:" + mandal);
        request.setCategory("Mandal");

        GetVillagesResponse response = (GetVillagesResponse) callWebservice(request, "villages");
        return response;
    }

    public GetPattadarListResponse getPattadhars(String village){
        GetPattadarList request=new GetPattadarList();
        request.setVillagecode(village);
        GetPattadarListResponse response = (GetPattadarListResponse) callWebservice(request, "pattadarList");
        return response;
    }
    public GetPattadarNamesResponse getPattadharNames(String dist,String mand,String village){
        GetPattadarNames request=new GetPattadarNames();
        request.setDistCode(dist);
        request.setMandCode(mand);
        request.setVillageCode(village);
        GetPattadarNamesResponse response = (GetPattadarNamesResponse) callWebservice(request, "pattadarNames");
        return response;
    }
}
