package org.n1c0sh.up42cc.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.n1c0sh.up42cc.dto.Feature;
import org.n1c0sh.up42cc.dto.FeatureInfo;

public class MappingUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.addMappings(pmFeatureToFeatureInfo());
    }

    private static PropertyMap<Feature, FeatureInfo> pmFeatureToFeatureInfo() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(source.getProperties().getId());
                map().setTimestamp(source.getProperties().getTimestamp());
                map().setBeginViewingDate(source.getProperties().getAcquisition().getBeginViewingDate());
                map().setEndViewingDate(source.getProperties().getAcquisition().getEndViewingDate());
                map().setMissionName(source.getProperties().getAcquisition().getMissionName());
            }
        };
    }

    public static FeatureInfo mapToDto(Feature entity) {
        return modelMapper.map(entity, FeatureInfo.class);
    }
}