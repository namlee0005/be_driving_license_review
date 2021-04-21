package com.nam_dev.driving_license_review.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "A1")
public class A1Mongo {
    @Id
    private String id;
    private String question;
    private String explain;
    private Integer correctAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer type;
    private String urlImage;
}
