package com.example.demo.service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.DatabaseSequence;


@Service
public class SequenceGeneratorService {

		@Autowired
		private MongoOperations mongoOperation;

		public int getCount(String seqName) {
			Query q = new Query(Criteria.where("id").is(seqName));
			Update update = new Update().inc("count", 1);
			DatabaseSequence counter = mongoOperation.findAndModify(q, update, options().returnNew(true).upsert(true),
					DatabaseSequence.class);
			return counter.getCount();

		}
}

