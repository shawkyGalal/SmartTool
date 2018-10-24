package Support;

import Support.HTML.AnchorTag;

public class TreeNode {
private java.util.Vector[] dataAsVectors ;
private int index ; 
private String id ; 
private String parentId ; 
private String headerId ; 
private String eDesc ; 
private String aDesc ;
private String desc ;
private String tableName ;
private String idInTable ; 
private Boolean linked ;
private String hyperLinkTitle ;
private String targetUrl ;
private String nodeEditor ;
private Boolean Selectable ;
private Integer cycle ; 
private Integer level ;
private String fullPath ; 

public TreeNode(int m_index , java.util.Vector[] m_dataAsVectors)
{
	index  = m_index ;
	dataAsVectors = m_dataAsVectors ; 
}

public TreeNode getParentNode()
{
	int v_parentIndex = this.dataAsVectors[0].indexOf(this.getParentId()) ; 
	TreeNode v_result = new TreeNode ( v_parentIndex , this.dataAsVectors ) ;
	return v_result ; 
}

public String toJsonForGoJs()
{
	String v_result = null ; 
	// { key: 2, text: "NP", fill: "#f68c06", stroke: "#4d90fe", parent: 1 },
	v_result = "{ key: \""+this.getId()+"\", text: \""+this.geteDesc().trim()+"\", fill: \"#f68c06\", table_name : \""+this.getTableName()+ "\", stroke: \"#4d90fe\", parent: \""+this.getParentId()+"\" }," ;  
	return v_result ; 
}



private void setHeaderId(String headerId) {
	this.headerId = headerId;
}

public String getHeaderId() {
	if (headerId == null) 
	{	if (dataAsVectors[7].elementAt(index) != null)
		{
			this.setHeaderId(dataAsVectors[7].elementAt(index).toString()) ;
		}
	}
		

	return headerId;
}

private void seteDesc(String eDesc) {
	this.eDesc = eDesc;
	
}

public String geteDesc() {
	if (eDesc== null) 
	{
		this.seteDesc   (dataAsVectors[5].elementAt(index).toString()) ;
		try {AnchorTag at = new AnchorTag( this.eDesc , "a"); this.eDesc = at.getValue(); }
		catch (Exception e){}
	}

	return eDesc;
}

private void setaDesc(String aDesc) {
	this.aDesc = aDesc;
}

public String getaDesc() {
	if (aDesc == null) 
	{ this.setaDesc (dataAsVectors[4].elementAt(index).toString()) ;
		try {AnchorTag at = new AnchorTag( this.aDesc , "a"); this.aDesc = at.getValue(); }
		catch (Exception e){}
	}

	return aDesc;
}

private void setTableName(String tableName) {
	this.tableName = tableName;
}

public String getTableName() {
	if (tableName == null )	this.setTableName(dataAsVectors[1].elementAt(index).toString()) ;

	return tableName;
}

private void setIdInTable(String idInTable) {
	this.idInTable = idInTable;
}

public String getIdInTable() {
	if (idInTable == null) this.setIdInTable(dataAsVectors[6].elementAt(index).toString()) ;
	return idInTable;
}

private void setLinked(boolean linked) {
	this.linked = linked;
}

public Boolean isLinked() {
	if (linked == null) 
	this.setLinked  (dataAsVectors[3].elementAt(index).toString().equalsIgnoreCase("1")); 

	return linked;
}

private void setHyperLinkTitle(String hyperLinkTitle) {
	this.hyperLinkTitle = hyperLinkTitle;
}

public String getHyperLinkTitle() {
	if (hyperLinkTitle == null) this.setHyperLinkTitle(dataAsVectors[8].elementAt(index).toString()) ;

	return hyperLinkTitle;
}

private void setTargetUrl(String targetUrl) {
	this.targetUrl = targetUrl;
}

public String getTargetUrl() {
	if (targetUrl==null) 	this.setTargetUrl(dataAsVectors[9].elementAt(index).toString()) ;
	return targetUrl;
}

private void setNodeEditor(String nodeEditor) {
	this.nodeEditor = nodeEditor;
}

public String getNodeEditor() {
	if (nodeEditor==null) 	this.setNodeEditor(dataAsVectors[10].elementAt(index).toString()) ;

	return nodeEditor;
}

private void setSelectable(boolean selectable) {
	Selectable = selectable;
}

public Boolean isSelectable() {
	if (Selectable == null) 	this.setSelectable(dataAsVectors[11].elementAt(index).toString().equalsIgnoreCase("1")) ;

	return Selectable;
}

private void setCycle(Integer cycle) {
	this.cycle = cycle;
}

public Integer getCycle() {
	if (cycle == null) 	this.setCycle(Integer.parseInt( dataAsVectors[12].elementAt(index).toString()) ) ;

	return cycle;
}

private void setLevel(int level) {
	this.level = level;
}

public Integer getLevel() {
	if (level == null) 	this.setLevel(Integer.parseInt( dataAsVectors[13].elementAt(index).toString()) ) ;
	return level;
}

private void setFullPath(String fullPath) {
	this.fullPath = fullPath;
}

public String getFullPath() {
	if(fullPath == null) 	this.setFullPath(dataAsVectors[14].elementAt(index).toString()) ;
 	return fullPath;
}

public String getDesc() {
	return desc;
}


public String getId() {
	if (id == null) this.id = (dataAsVectors[0].elementAt(index).toString()) ;
	return id;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}

public String getParentId() {
	if (parentId== null) 	this.setParentId(dataAsVectors[2].elementAt(index).toString()) ;
	return parentId;
}

public String getAttributeValue(int m_columnIndex) 
{
	return dataAsVectors[m_columnIndex].elementAt(index).toString() ; 
}
}
