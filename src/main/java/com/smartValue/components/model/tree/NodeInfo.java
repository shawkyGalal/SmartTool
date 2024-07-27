package com.smartValue.components.model.tree;

import java.util.StringTokenizer;

public class NodeInfo {
	private String nodeTitle;
	private String nodeURL;
	private String nodeParams;

	public NodeInfo(String pm_info) {
		StringTokenizer tok = new StringTokenizer(pm_info, "$$");
		String pm_nodeTitle = tok.nextToken();
		String pm_nodeURL = ""; 
		try{pm_nodeURL = tok.nextToken();} catch (Exception e) {};
		String pm_nodeParams ="";
		try{pm_nodeParams = tok.nextToken();} catch (Exception e) {};
		this.nodeTitle = pm_nodeTitle;
		this.nodeURL = pm_nodeURL;
		this.nodeParams = pm_nodeParams;
	}

	public String toString() {
		return nodeTitle;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeParams(String nodeParams) {
		this.nodeParams = nodeParams;
	}

	public String getNodeParams() {
		return nodeParams;
	}

	public void setNodeURL(String nodeURL) {
		this.nodeURL = nodeURL;
	}

	public String getNodeURL() {
		return nodeURL;
	}
}
