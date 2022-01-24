package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {
	
	private static final String SEQUENCE_NAME_AS = "AS_Sequence";
	private static final String SEQUENCE_NAME_BS = "BS_Sequence";
	private static final String SEQUENCE_NAME_CS = "CS_Sequence";

	@Id
	private String id;
	private int count;

	public DatabaseSequence(String id, int count) {
		super();
		this.id = id;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static String getSequenceNameAS() {
		return SEQUENCE_NAME_AS;
	}
	
	public static String getSequenceNameBS() {
		return SEQUENCE_NAME_BS;
	}
	
	public static String getSequenceNameCS() {
		return SEQUENCE_NAME_CS;
	}
}
