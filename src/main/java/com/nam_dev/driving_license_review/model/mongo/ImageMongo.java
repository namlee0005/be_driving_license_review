package com.nam_dev.driving_license_review.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "IMAGE")
@CompoundIndexes({
        @CompoundIndex(def = "{'id': 1}"),
        @CompoundIndex(def = "{'time': 1}")
})
public class ImageMongo {
    @Id
    private String id;
    private String path;
    private String bucket;
    private Boolean status;
    private Long time;
}
