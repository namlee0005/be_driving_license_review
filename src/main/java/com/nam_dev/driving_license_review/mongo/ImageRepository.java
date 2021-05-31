package com.nam_dev.driving_license_review.mongo;

import com.nam_dev.driving_license_review.model.mongo.ImageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<ImageMongo, String> {
}
