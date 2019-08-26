package org.n1c0sh.up42cc.api;

import lombok.RequiredArgsConstructor;
import org.n1c0sh.up42cc.dto.FeatureInfo;
import org.n1c0sh.up42cc.services.FeatureService;
import org.n1c0sh.up42cc.util.MappingUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.n1c0sh.up42cc.util.MappingUtils.mapToDto;

@RestController
@RequestMapping("/features")
@RequiredArgsConstructor
public class MainApiController {

	private final FeatureService featureService;

	@GetMapping
	public ResponseEntity<List<FeatureInfo>> getAllFeatures() {
		return ResponseEntity.ok(
		        featureService.getAllFeatures().parallelStream()
                        .map(MappingUtils::mapToDto)
                        .collect(Collectors.toList()));
	}

	@GetMapping("/{featureId}")
	public ResponseEntity<FeatureInfo> getFeatureById(@PathVariable UUID featureId) {
		return ResponseEntity.ok(mapToDto(featureService.getFeature(featureId)));
	}

	@GetMapping(value = "/{featureId}/quicklook")
	public ResponseEntity<byte[]> getFeatureQuicklook(@PathVariable UUID featureId) {
	    byte[] imageData = featureService.getQuicklook(featureId);
		return ResponseEntity.ok()
                .contentLength(imageData.length)
				.contentType(MediaType.IMAGE_PNG)
                .body(imageData);
	}
}
