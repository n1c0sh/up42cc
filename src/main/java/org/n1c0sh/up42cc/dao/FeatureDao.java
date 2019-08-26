package org.n1c0sh.up42cc.dao;

import org.n1c0sh.up42cc.dto.Feature;

import java.util.List;
import java.util.UUID;

public interface FeatureDao {
    List<Feature> getAllFeatures();

    Feature getFeature(UUID id);

    byte[] getQuicklook(UUID id);
}
