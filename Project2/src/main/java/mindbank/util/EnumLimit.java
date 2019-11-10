package main.java.mindbank.util;

public enum EnumLimit {

	FIVE(0, 5),
	TEN(1, 10),
	FIFTEEN(2, 15),
	TWENTY(3, 20);

	private int id;
	private int limit;

	private EnumLimit(int id, int limit) {
		this.id = id;
		this.limit = limit;
	}

	public int getId() {
		return id;
	}

	public int getLimit() {
		return limit;
	}

}
