package com.singletonapps.repository;

import com.singletonapps.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David Cuellar on 8/06/17.
 */
@Repository
public interface GameRepository extends MongoRepository<Game, Integer> {
}
