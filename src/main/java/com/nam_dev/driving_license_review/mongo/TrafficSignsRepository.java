package com.nam_dev.driving_license_review.mongo;

import com.nam_dev.driving_license_review.model.mongo.TrafficSignsMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficSignsRepository extends MongoRepository<TrafficSignsMongo, String> {
    Page<TrafficSignsMongo> findByType(Integer type, Pageable pageable);
}
