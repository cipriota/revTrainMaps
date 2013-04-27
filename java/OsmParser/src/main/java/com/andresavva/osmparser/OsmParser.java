package com.andresavva.osmparser;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.andresavva.osmparser.data.Node;
import com.andresavva.osmparser.data.Tag;
import com.google.gson.Gson;

public class OsmParser extends DefaultHandler {

	private List<Node> nodeList;
	private Node tmpNode;
	private Tag tmpTag;
	FileWriter writer;
	String jsonFile;
	Gson gson;
	String json;
	int nodeCount;
	
	public OsmParser() {
		
		nodeList = new ArrayList<Node>();
		nodeCount = 0;
		
		try {
			
			String osm = "/home/andresavva/Desktop/workspaces/personal/revTrainMaps/portugal-latest.osm";
			//String osm = "/home/andresavva/Downloads/map.osm";
			jsonFile = "/home/andresavva/workspaces/personal/revTrainMaps/Web/js/db.js";
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			

			writer = new FileWriter(jsonFile);
			gson = new Gson();
			
			writer.write("geoData = {" + '\n' + '\t');
			writer.write("\"nodes\": [" + '\n');
			
			parser.parse(osm, this);
			
			
//			int i=0;
//			for (Node node : nodeList) {
//				for(Tag tag : node.getTags()) {
//					if (tag.getK().equalsIgnoreCase("railway") && tag.getV().equalsIgnoreCase("station")) {
//						json = gson.toJson(node);
//						if (i>0) {
//							writer.write("," + '\n');
//						}
//						writer.write('\t');
//						writer.write('\t');
//						writer.write(json);
//						
//						i++;
//					}
//				}
//				
//				
//			}
			
			writer.write('\n');
			writer.write('\t');
			writer.write("]");
			writer.write('\n');
			writer.write("};");
			writer.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new OsmParser();
	}

	@Override
	public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("node")) {
			tmpNode = new Node();
			tmpNode.setId(new Long(attributes.getValue("id")));
			tmpNode.setLat(new Double(attributes.getValue("lat")));
			tmpNode.setLon(new Double(attributes.getValue("lon")));
		}
		
		if (elementName.equalsIgnoreCase("tag")) {
			tmpTag = new Tag();
			tmpTag.setK(attributes.getValue("k"));
			tmpTag.setV(attributes.getValue("v"));
			
		}
	}
	
	@Override
	public void endElement(String s, String s1, String element) throws SAXException {
		
		try {
			if (element.equals("node")) {
				//nodeList.add(tmpNode);
				
				
				for(Tag tag : tmpNode.getTags()) {
					if (tag.getK().equalsIgnoreCase("railway") && tag.getV().equalsIgnoreCase("station")) {
						json = gson.toJson(tmpNode);
						if (nodeCount>0) {
							writer.write("," + '\n');
						}
						writer.write('\t');
						writer.write('\t');
						writer.write(json);
						
						nodeCount++;
					}
				}
				
			}
			
			if (element.equals("tag")) {
				tmpNode.getTags().add(tmpTag);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
