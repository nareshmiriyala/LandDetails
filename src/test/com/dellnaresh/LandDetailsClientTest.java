package com.dellnaresh;

import com.dellnaresh.wsdl.CascadingDropDownNameValue;
import com.dellnaresh.wsdl.GetDistrictsResponse;
import com.dellnaresh.wsdl.GetMandalsResponse;
import com.dellnaresh.wsdl.GetVillagesResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Test
    public void testGetVillages() throws Exception {
        GetDistrictsResponse getDistrictsResponse = getGetDistrictsResponse();
        Map<String,String> vilMap=new HashMap<>();
        getDistrictsResponse.getGetDistrictsResult().getCascadingDropDownNameValue().stream().forEach(dis->{
                getMandals(dis).stream().forEach(man->getVillages(man.getValue(),dis.getValue(),vilMap));
        });

        System.out.println("Total villages "+vilMap.size());


    }

    private void getVillages(String man,String dis,Map<String,String> villMap) {
        GetVillagesResponse villages = landClient.getVillages(dis, man);
        assertNotNull(villages);
        villages.getGetVillagesResult().getCascadingDropDownNameValue().stream().forEach(vil->villMap.put(vil.getName(),vil.getName()));
    }

    private List<CascadingDropDownNameValue> getMandals(CascadingDropDownNameValue dis) {
        GetMandalsResponse mandalsResponse=landClient.getMandals( dis.getValue());
        assertNotNull(mandalsResponse);
        return mandalsResponse.getGetMandalsResult().getCascadingDropDownNameValue();

    }
}