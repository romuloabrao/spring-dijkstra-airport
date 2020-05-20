package br.com.bexs.dijktra.airport.model;

public class UserResponse {
	
	private String begin;
	private String end;
	private String path;
	private Long cost;
	
	public UserResponse(String[] entries) {
		this.begin = entries[0];
		this.end = entries[1];
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

}
