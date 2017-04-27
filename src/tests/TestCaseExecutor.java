package tests;

public interface TestCaseExecutor {
	String [] execWithArgs(String [] args);
	String execWithFiles(String [] infiles);
}