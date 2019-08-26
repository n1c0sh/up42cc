package org.n1c0sh.up42cc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class FeatureInfo implements Serializable {
    private UUID id;
    private Date timestamp;
    private Date beginViewingDate;
    private Date endViewingDate;
    private String missionName;
}
