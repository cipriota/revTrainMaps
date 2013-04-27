package com.andresavva.osmparser.data;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private Long id;
	private Double lat;
	private Double lon;
	private List<Tag> tags;
	
	public Node(){
		tags = new ArrayList<Tag>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return "id: " + this.id + " lat: " + this.lat + " lon: " + this.lon;
	}
}
