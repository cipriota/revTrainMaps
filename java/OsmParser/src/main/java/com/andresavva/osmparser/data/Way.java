package com.andresavva.osmparser.data;

import java.util.ArrayList;
import java.util.List;

public class Way {

	private Long id;
	private List<Node> nodes;
	
	public Way(){
		nodes = new ArrayList<Node>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
