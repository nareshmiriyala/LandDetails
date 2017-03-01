package com.dellnaresh;

import com.dellnaresh.wsdl.GetDistricts;
import com.dellnaresh.wsdl.GetDistrictsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by nmiriyal on 1/03/2017.
 */
public class LandDetailsClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(LandDetailsClient.class);
    public static final String URI = "http://meebhoomi.ap.gov.in/UtilityWebService.asmx";

    public GetDistrictsResponse getDistricts() {
        GetDistricts request = new GetDistricts();
        log.info("Requesting quote for districts");
        GetDistrictsResponse response = (GetDistrictsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI,
                        request,
                        new SoapActionCallback("http://tempuri.org/GetDistricts"));
        return response;
    }

}
