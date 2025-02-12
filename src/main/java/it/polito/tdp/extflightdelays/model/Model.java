package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
//import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	Logger logger = LoggerFactory.getLogger(Model.class);
	Graph<Airport, ArcoPesato> graph;
	
	/**
	 *  creare un grafo che rappresenti gli aeroporti collegati da almeno un volo, e distanti in media almeno {@code miles} miglia.
	 * @param miles
	 */
	public void creaGrafo(double miles) {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		graph = new SimpleWeightedGraph<>(ArcoPesato.class);
		
		List<Airport> airports = dao.loadAllAirports();
		Map<Integer, Airport> airportsMap = new HashMap<>();
		for(Airport a : airports)
			airportsMap.put(a.getId(), a);
		Graphs.addAllVertices(graph, airports);
		
		List<Flight> flights = dao.loadAllFlights();
		for(Airport a : airports) {
			for(Flight f : flights) {
				if(f.getOriginAirportId() == a.getId()) {
					if(f.getDistance() > miles && !graph.containsEdge(a, airportsMap.get(f.getDestinationAirportId()))) {
//						logger.info("Aggiunto aeroporto di distanza {}", f.getDistance());
						this.graph.addEdge(a, airportsMap.get(f.getDestinationAirportId()) , new ArcoPesato(a, airportsMap.get(f.getDestinationAirportId()), f.getDistance()));
						//this.graph.setEdgeWeight(a, airportsMap.get(f.getDestinationAirportId()), f.getDistance());
					}
				}
			}
		}
		
	}

	
	/**
	 * Stampa del grafico;
	 * @return String con la stampa
	 */
	public String printGraph() {
		String output = "Numero di vertici: " + graph.vertexSet().size() + "\nNumero di archi: " + graph.edgeSet().size() + "\n";
		for(ArcoPesato ap : graph.edgeSet()) {
			output += "\n\n"+ap;
		}
		return output;
	}

}

