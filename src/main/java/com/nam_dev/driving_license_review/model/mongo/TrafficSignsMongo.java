package com.nam_dev.driving_license_review.model.mongo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TRAFFIC_SIGNS")
public class TrafficSignsMongo {
    @Id
    private String id;
    private String urlImage;
    @ApiModelProperty("1: bien bao cam, 2: bien bao nguy hiem, 3: Bien Chi dan, 4 bien hieu lenh, 5: bien phu")
    private Integer type;
}
