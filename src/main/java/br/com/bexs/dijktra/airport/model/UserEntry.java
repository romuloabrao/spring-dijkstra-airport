package br.com.bexs.dijktra.airport.model;

public class UserEntry {

	private String begin;
	private String end;
	private Long cost;

	public UserEntry(String begin, String end, Long cost) {
		this.begin = begin;
		this.end = end;
		this.cost = cost;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public String getFormatedInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append(begin.concat(","))
			.append(end.concat(","))
			.append(cost.toString());
		return sb.toString();
	}

}
