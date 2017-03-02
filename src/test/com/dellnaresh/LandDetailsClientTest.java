package com.dellnaresh;

import com.dellnaresh.wsdl.GetDistrictsResponse;
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
        GetDistrictsResponse districts = landClient.getDistricts();
        assertNotNull(districts);
        districts.getGetDistrictsResult().getCascadingDropDownNameValue().stream().forEach(System.out::println);
    }

    @Test
    public void testGetMandals() throws Exception {

    }
}