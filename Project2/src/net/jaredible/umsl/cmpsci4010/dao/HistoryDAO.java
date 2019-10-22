package net.jaredible.umsl.cmpsci4010.dao;

import java.util.List;

import net.jaredible.umsl.cmpsci4010.model.History;

public interface HistoryDAO {

	public List<History> getList();

	public void addHistory(History h);

	public History getHistory(int i);

	public void updateHistory(History h);

	public void deleteHistory(int i);

}
