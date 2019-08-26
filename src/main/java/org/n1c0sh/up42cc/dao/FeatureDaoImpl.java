package org.n1c0sh.up42cc.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.n1c0sh.up42cc.dto.Feature;
import org.n1c0sh.up42cc.dto.FeatureCollection;
import org.n1c0sh.up42cc.exceptions.FeatureNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class FeatureDaoImpl implements FeatureDao {
    @Value("classpath:data/source-data.json")
    Resource dataResource;

    private final ObjectMapper mapper;

    private ConcurrentHashMap<UUID, Feature> featuresMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() throws IOException {
        var typeRef = new TypeReference<List<FeatureCollection>>() {};
        List<FeatureCollection> featureCollections = mapper.readValue(dataResource.getInputStream(), typeRef);
        featureCollections.parallelStream()
                .map(featureCollection -> featureCollection.getFeatures().get(0)) //Based on the file provided, assuming there will always be exactly one feature
                .forEach(feature -> featuresMap.put(feature.getProperties().getId(), feature));
    }

    @Override
    public List<Feature> getAllFeatures() {
        return new ArrayList<>(featuresMap.values());
    }

    @Override
    public Feature getFeature(UUID id) {
        return getFeatureOrThrowException(id);
    }

    @Override
    public byte[] getQuicklook(UUID id) {
        return Base64.getDecoder().decode(getFeatureOrThrowException(id).getProperties().getQuicklook());
    }

    private Feature getFeatureOrThrowException(UUID id) {
        return Optional.ofNullable(featuresMap.get(id))
                .orElseThrow(() -> new FeatureNotFoundException(String.format("Feature with id=[%s] not found!", id)));
    }
}
