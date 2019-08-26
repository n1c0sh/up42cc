package org.n1c0sh.up42cc.services;

import org.n1c0sh.up42cc.dto.Feature;

import java.util.List;
import java.util.UUID;

public interface FeatureService {
    List<Feature> getAllFeatures();

    Feature getFeature(UUID featureId);

    byte[] getQuicklook(UUID featureId);
}
