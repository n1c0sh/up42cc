package org.n1c0sh.up42cc.services;

import lombok.RequiredArgsConstructor;
import org.n1c0sh.up42cc.dao.FeatureDao;
import org.n1c0sh.up42cc.dto.Feature;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureDao featureDao;

    @Override
    public List<Feature> getAllFeatures() {
        return featureDao.getAllFeatures();
    }

    @Override
    public Feature getFeature(UUID featureId) {
        return featureDao.getFeature(featureId);
    }

    @Override
    public byte[] getQuicklook(UUID featureId) {
        return featureDao.getQuicklook(featureId);
    }
}
