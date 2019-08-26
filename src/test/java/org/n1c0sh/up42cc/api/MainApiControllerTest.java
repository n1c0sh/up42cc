package org.n1c0sh.up42cc.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.n1c0sh.up42cc.dto.Feature;
import org.n1c0sh.up42cc.dto.FeatureProperties;
import org.n1c0sh.up42cc.exceptions.FeatureNotFoundException;
import org.n1c0sh.up42cc.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MainApiController.class)
public class MainApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    FeatureService featureService;

    @Test
    public void whenGetAllFeatures_shoudReturnJsonArray() throws Exception {
        UUID testFeatureId = UUID.randomUUID();
        Date testDate = new Date();
        String testMissionName = "Sample Mission";
        Feature testFeature = Feature.builder()
                .properties(FeatureProperties.builder()
                        .id(testFeatureId)
                        .timestamp(testDate)
                        .acquisition(FeatureProperties.Acquisition.builder()
                                .endViewingDate(testDate)
                                .beginViewingDate(testDate)
                                .missionName(testMissionName)
                                .build())
                        .build())
                .build();
        given(featureService.getAllFeatures()).willReturn(List.of(testFeature));

        mvc.perform(get("/features")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(testFeatureId.toString())))
                .andExpect(jsonPath("$[0].timestamp", is(testDate.getTime())))
                .andExpect(jsonPath("$[0].beginViewingDate", is(testDate.getTime())))
                .andExpect(jsonPath("$[0].endViewingDate", is(testDate.getTime())))
                .andExpect(jsonPath("$[0].missionName", is(testMissionName)));
    }

    @Test
    public void givenFeatureId_whenGetFeature_shoudReturnFeature() throws Exception {
        UUID testFeatureId = UUID.randomUUID();
        Date testDate = new Date();
        String testMissionName = "Sample Mission";
        Feature testFeature = Feature.builder()
                .properties(FeatureProperties.builder()
                        .id(testFeatureId)
                        .timestamp(testDate)
                        .acquisition(FeatureProperties.Acquisition.builder()
                                .endViewingDate(testDate)
                                .beginViewingDate(testDate)
                                .missionName(testMissionName)
                                .build())
                        .build())
                .build();
        given(featureService.getFeature(testFeatureId)).willReturn(testFeature);

        mvc.perform(get("/features/{testFeatureId}", testFeatureId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testFeatureId.toString())))
                .andExpect(jsonPath("$.timestamp", is(testDate.getTime())))
                .andExpect(jsonPath("$.beginViewingDate", is(testDate.getTime())))
                .andExpect(jsonPath("$.endViewingDate", is(testDate.getTime())))
                .andExpect(jsonPath("$.missionName", is(testMissionName)));
    }

    @Test
    public void givenBadFeatureId_whenGetFeature_shoudReturnError() throws Exception {
        UUID testFeatureId = UUID.randomUUID();
        given(featureService.getFeature(testFeatureId)).willThrow(new FeatureNotFoundException());

        mvc.perform(get("/features/{testFeatureId}", testFeatureId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenFeatureId_whenGetFeatureQuicklook_shouldReturnImage() throws Exception {
        UUID testFeatureId = UUID.randomUUID();
        byte[] testBytes = {3,1,3,3,7};
        given(featureService.getQuicklook(testFeatureId)).willReturn(testBytes);

        mvc.perform(get("/features/{testFeatureId}/quicklook", testFeatureId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().string(is(new String(testBytes))));
    }

    @Test
    public void givenBadFeatureId_whenGetFeatureQuicklook_shoudReturnError() throws Exception {
        UUID testFeatureId = UUID.randomUUID();
        String testExceptionMessage = "NotFound";
        given(featureService.getQuicklook(testFeatureId)).willThrow(new FeatureNotFoundException(testExceptionMessage));

        mvc.perform(get("/features/{testFeatureId}/quicklook", testFeatureId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}