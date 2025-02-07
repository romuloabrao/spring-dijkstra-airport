package br.com.bexs.dijktra.airport.model;

public class UserResponse {
	
	private String begin;
	private String end;
	private String path;
	private Long cost;
	
	public UserResponse(String begin,String end) {
		this.begin = begin;
		this.end = end;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getPath().concat(" > $"))
			.append(this.cost);
		return sb.toString();
	}

	public String getFormattedQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(begin.concat("-"))
			.append(end);
		return sb.toString();
	}

}
