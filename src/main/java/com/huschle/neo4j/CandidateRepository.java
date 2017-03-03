package com.huschle.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import com.huschle.neo4j.entity.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, String> {
   @Query("Match (candidate:Candidate) Where candidate.name = {0} return candidate")	
   Candidate getCandidateByName(String name);
   @Query("Match (candidate:Candidate)-[:COLLEAGUE]-> (candidateColleagues) where candidate.name = {0} return candidateColleagues")
   Iterable<Candidate> getColleaguesByCandidateName(String name);
}