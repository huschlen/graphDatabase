package com.huschle.neo4j;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import com.huschle.neo4j.config.AppConfig;
import com.huschle.neo4j.entity.Candidate;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		GraphDatabase graphDatabase = (GraphDatabase)ctx.getBean("graphDatabase");
		CandidateRepository candidateRepository = ctx.getBean(CandidateRepository.class);
		Transaction tx = graphDatabase.beginTx();
		Candidate tom = new Candidate("Tom");
		Candidate dave = new Candidate("Dave");
		Candidate kevin = new Candidate("Kevin");
		Candidate naoko = new Candidate("Naoko");
		try {
			//Delete if exists already
			candidateRepository.deleteAll();
			//Save candidate
			candidateRepository.save(tom);
			candidateRepository.save(dave);
			candidateRepository.save(kevin);
			//Build relation
			naoko.myColleagues(tom);
			naoko.myColleagues(dave);
			naoko.myColleagues(kevin);
			candidateRepository.save(naoko);
			//Get candidate By Name
			Candidate c = candidateRepository.getCandidateByName(tom.name);
			System.out.println(c.name);
			//Fetch all colleagues of a candidate
			Iterable<Candidate> colleagues = candidateRepository.getColleaguesByCandidateName(naoko.name);
			System.out.println("----Colleagues----");
			for (Candidate candidate : colleagues) {
				System.out.println(candidate.name);
			}
			tx.success();
		}
		finally {
			tx.close();
		}
	}
}