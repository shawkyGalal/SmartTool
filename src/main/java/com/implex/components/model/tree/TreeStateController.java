package com.implex.components.model.tree;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.framework.Controller;
import org.richfaces.component.state.TreeState;

public class TreeStateController extends Controller {
	private Map<Integer, TreeState> treeStateMap = new HashMap<Integer, TreeState>();

	public TreeState getTreeState(Integer pm_key) {
		TreeState treeState = treeStateMap.get(pm_key);
		if (treeState == null) {
			treeState = new TreeState();
			treeStateMap.put(pm_key, treeState);
		}
		return treeState;
	}

	public void setTreeState(Integer key, TreeState treeState) {
		treeStateMap.put(key, treeState);
	}

}
