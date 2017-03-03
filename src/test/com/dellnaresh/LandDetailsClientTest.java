package com.dellnaresh;

import com.dellnaresh.wsdl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
        iterateDistricts(districts, System.out::println);
    }

    private GetDistrictsResponse getGetDistrictsResponse() {
        return landClient.getDistricts();
    }

    @Test
    public void testGetMandals() throws Exception {
        GetDistrictsResponse getDistrictsResponse = getGetDistrictsResponse();
        iterateDistricts(getDistrictsResponse, this::getMandals);


    }
    @Test
    public void testGetVillages() throws Exception {
        Map<String, String> vilMap = getVillages();

        System.out.println("Total villages "+vilMap.size());


    }

    @Test
    public void testPattadars() throws Exception {
        Map<String, String> vilMap = getVillages();
        vilMap.entrySet().forEach(test -> System.out.println("Pattadars List:" + landClient.getPattadhars(test.getKey())));

    }

    private Map<String, String> getVillages() {
        GetDistrictsResponse getDistrictsResponse = getGetDistrictsResponse();
        Map<String,String> vilMap=new HashMap<>();
        iterateDistricts(getDistrictsResponse, dis -> {
            getMandals(dis).stream().forEach(man -> getVillages(man.getValue(), dis.getValue(), vilMap));
        });
        return vilMap;
    }

    private void iterateDistricts(GetDistrictsResponse getDistrictsResponse, Consumer<CascadingDropDownNameValue> cascadingDropDownNameValueConsumer) {
        getDistrictsResponse.getGetDistrictsResult().getCascadingDropDownNameValue().stream().forEach(cascadingDropDownNameValueConsumer);
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

    @Test
    public void testGetPattadhars() throws Exception {
        GetPattadarListResponse pattadhars = landClient.getPattadhars("741010");
        System.out.println(pattadhars.getGetPattadarListResult());
    }

    @Test
    public void testGetPattadharNames() throws Exception {
        GetPattadarNamesResponse pattadharNames = landClient.getPattadharNames("7", "23", "741010");
        pattadharNames.getGetPattadarNamesResult().getString().stream().forEach(System.out::println);
    }
}