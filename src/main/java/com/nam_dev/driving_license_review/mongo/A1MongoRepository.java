package com.nam_dev.driving_license_review.mongo;

import com.nam_dev.driving_license_review.model.mongo.A1Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface A1MongoRepository extends MongoRepository<A1Mongo, String> {
}
