package org.n1c0sh.up42cc.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Feature.class),
        @JsonSubTypes.Type(value = FeatureCollection.class)
})
class GeoJSONObject implements Serializable {
}
