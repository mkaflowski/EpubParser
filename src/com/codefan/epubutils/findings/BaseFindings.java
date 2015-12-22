package com.codefan.epubutils.findings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Helper methods used in Findings
public abstract class BaseFindings {

	public abstract void fillContent(Node node) throws IllegalArgumentException, IllegalAccessException, DOMException;

	protected class XmlItem {
		private String value;
		private Map<String, String> attributes;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Map<String, String> getAttributes() {
			return attributes;
		}

		public void setAttributes(Map<String, String> attributes) {
			this.attributes = attributes;
		}
	}

	protected List<XmlItem> nodeListToXmlItemList(NodeList nodeList) {

		List<XmlItem> xmlItemList = new ArrayList<>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			XmlItem xmlItem = nodeToXmlItem(nodeList.item(i));
			if (!xmlItem.getValue().replaceAll("\\s+", "").equals("") || xmlItem.getAttributes() != null) {
				xmlItemList.add(xmlItem);
			}
		}

		return xmlItemList;
	}

	protected XmlItem nodeToXmlItem(Node node) {
		XmlItem xmlItem = new XmlItem();
		xmlItem.setValue(node.getTextContent());

		if (node.hasAttributes()) {
			NamedNodeMap nodeMap = node.getAttributes();

			Map<String, String> attributes = new HashMap<>();

			for (int j = 0; j < nodeMap.getLength(); j++) {
				Node attribute = nodeMap.item(j);
				attributes.put(attribute.getNodeName(), attribute.getNodeValue());
			}

			xmlItem.setAttributes(attributes);
		}

		return xmlItem;
	}

}