package net.jaredible.mindbank.dao;

import java.sql.Connection;
import java.sql.Timestamp;

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.ProblemInfo;
import net.jaredible.mindbank.util.ProblemInfoList;

public interface ProblemDAO {
	
	// create
	public void addProblem(Problem p);

	// read
	public ProblemInfo getProblemById(int i);
	public ProblemInfoList getAllProblems();
	public ProblemInfoList getAllProblemsByCategoryId(int i);
	public ProblemInfoList getProblemsWithLimit(int i, int j);
	public ProblemInfoList getProblemsByCategoryIdWithLimit(int i, int j, int k);

	// update
	public void updateCategoryIdById(int i, int j);
	public void updateTitleById(int i, String s);
	public void updateContentById(int i, String s);
	public void updateEditedById(int i, boolean b);
	public void updateCreatedByUserIdById(int i, int j);
	public void updateCreatedTimestampById(int i, Timestamp ts);

	// delete
	public void deleteProblemById(int i);

	public Connection getConnection();

	public void closeConnections();

}
