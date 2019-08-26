package org.n1c0sh.up42cc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("Feature")
public class Feature extends GeoJSONObject {
    private FeatureProperties properties;
}

