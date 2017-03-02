package com.dellnaresh;

import com.dellnaresh.wsdl.GetDistricts;
import com.dellnaresh.wsdl.GetDistrictsResponse;
import com.dellnaresh.wsdl.GetMandals;
import com.dellnaresh.wsdl.GetMandalsResponse;
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

}
