package org.n1c0sh.up42cc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureProperties implements Serializable {
    private UUID id;
    private Date timestamp;
    private String quicklook;
    private Acquisition acquisition;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Acquisition implements Serializable {
        private Date endViewingDate;
        private Date beginViewingDate;
        private String missionName;
    }
}
