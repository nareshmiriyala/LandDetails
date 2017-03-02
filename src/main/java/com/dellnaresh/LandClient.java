package com.dellnaresh;

import com.dellnaresh.wsdl.GetDistrictsResponse;
import com.dellnaresh.wsdl.GetMandalsResponse;

/**
 * Created by nmiriyal on 3/03/2017.
 */
public interface LandClient {
    GetDistrictsResponse getDistricts();

    GetMandalsResponse getMandals(String district);
}
