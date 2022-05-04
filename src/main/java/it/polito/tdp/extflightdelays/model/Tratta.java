package it.polito.tdp.extflightdelays.model;

import java.util.Objects;

public class Tratta {
	private Integer fromId;
	private Integer toId;
	private double avgDistance;
	
	public Tratta(Integer from, Integer to, Double distance) {
		// ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		this.fromId = from;
		this.toId = to;
		
		avgDistance = distance;
	}


	public double getMiles() {
		return avgDistance;
	}
	
	public Integer getFromId() {
		return fromId;
	}


	public Integer getToId() {
		return toId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fromId, toId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratta other = (Tratta) obj;
		return Objects.equals(fromId, other.fromId) && Objects.equals(toId, other.toId);
	}

	
	
	
	
}
