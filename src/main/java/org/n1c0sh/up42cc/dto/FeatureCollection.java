package org.n1c0sh.up42cc.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)

@JsonTypeName("FeatureCollection")
public class FeatureCollection extends GeoJSONObject implements Serializable {
    private List<Feature> features;
}

