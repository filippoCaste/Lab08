package it.polito.tdp.extflightdelays.model;

import org.jgrapht.graph.DefaultEdge;

public class ArcoPesato extends DefaultEdge {
	
	private Airport from;
	private Airport to;
	private double weight; // distance

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArcoPesato(Airport from, Airport to, double weigth) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weigth;
	}

	public Airport getFrom() {
		return from;
	}

	public Airport getTo() {
		return to;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return "Origine: " + from.toString() + " \nDestinazione: " + to.toString() + " \nPeso: " + weight +" miles";
	}

}
