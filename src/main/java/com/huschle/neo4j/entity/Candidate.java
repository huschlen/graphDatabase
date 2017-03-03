package com.huschle.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Candidate {
	@GraphId
	Long id;
	public String name;
	@Fetch
	@RelatedTo(type="COLLEAGUE", direction=Direction.BOTH)
	public Set<Candidate> colleagues;
	public Candidate(){}
	public Candidate(String name) {
		this.name=name;
	}
	public void myColleagues(Candidate candidate) {
		if(colleagues == null) {
			colleagues = new HashSet<Candidate>();
		}
		colleagues.add(candidate);
	}
}
