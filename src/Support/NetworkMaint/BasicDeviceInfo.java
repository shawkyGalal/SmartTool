package Support.NetworkMaint;

import java.util.Date;

public abstract class BasicDeviceInfo {
	private String physicalLocation;
	private String serialNumber ;
	private String owner;
	private boolean coveredByMaint ;
	private boolean coveredByReplacment ;
	private boolean coverSpareParts ;
	private Date coverStartDate;
	private Date coverEndDate ;
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;
	}
	public String getPhysicalLocation() {
		return physicalLocation;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	public void setCoveredByMaint(boolean coveredByMaint) {
		this.coveredByMaint = coveredByMaint;
	}
	public boolean isCoveredByMaint() {
		return coveredByMaint;
	}
	public void setCoveredByReplacment(boolean coveredByReplacment) {
		this.coveredByReplacment = coveredByReplacment;
	}
	public boolean isCoveredByReplacment() {
		return coveredByReplacment;
	}
	public void setCoverSpareParts(boolean coverSpareParts) {
		this.coverSpareParts = coverSpareParts;
	}
	public boolean isCoverSpareParts() {
		return coverSpareParts;
	}
	public void setCoverStartDate(Date coverStartDate) {
		this.coverStartDate = coverStartDate;
	}
	public Date getCoverStartDate() {
		return coverStartDate;
	}
	public void setCoverEndDate(Date coverEndDate) {
		this.coverEndDate = coverEndDate;
	}
	public Date getCoverEndDate() {
		return coverEndDate;
	} 
	
	public boolean isCoveredNow()
	{
		boolean result = false ;
		Date now = new Date();
		result = this.getCoverEndDate().after (now) && this.getCoverStartDate().before(now);
		return result; 
	}
	
	protected abstract void save() ;
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	
	

}
