package com.dellnaresh;

import com.dellnaresh.wsdl.CascadingDropDownNameValue;
import com.dellnaresh.wsdl.GetDistrictsResponse;
import com.dellnaresh.wsdl.GetMandalsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by nmiriyal on 3/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LandDetailsClientTest  {
    @Autowired
    private LandDetailsClient landClient;
    @Test
    public void testGetDistricts() throws Exception {
        GetDistrictsResponse districts = getGetDistrictsResponse();
        assertNotNull(districts);
        districts.getGetDistrictsResult().getCascadingDropDownNameValue().stream().forEach(System.out::println);
    }

    private GetDistrictsResponse getGetDistrictsResponse() {
        return landClient.getDistricts();
    }

    @Test
    public void testGetMandals() throws Exception {
        GetDistrictsResponse getDistrictsResponse = getGetDistrictsResponse();
        getDistrictsResponse.getGetDistrictsResult().getCascadingDropDownNameValue().stream().forEach(this::getMandals);


    }

    private void getMandals(CascadingDropDownNameValue dis) {
        GetMandalsResponse mandalsResponse=landClient.getMandals( dis.getValue());
        assertNotNull(mandalsResponse);

    }
}