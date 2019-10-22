package main.java.mathbank.dao;

import java.util.List;

import main.java.mathbank.model.History;

public interface HistoryDAO {

	public List<History> getList();

	public void addHistory(History h);

	public History getHistory(int i);

	public void updateHistory(History h);

	public void deleteHistory(int i);

}
