package it.fse2db.bean.gtfs;

import java.util.List;

public class Stops {
	public String stopId;
	public String stopName;
	public String stopDesc;
	public String stopLat;
	public String stopLon;
	public String stopUrl;
	public String locationType;
	public String parentStation;
	public List<StopTimes> listStopTimes;
	public List<StopTimes> getListStopTimes() {
		return listStopTimes;
	}
	public void setListStopTimes(List<StopTimes> listStopTimes) {
		this.listStopTimes = listStopTimes;
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public String getStopDesc() {
		return stopDesc;
	}
	public void setStopDesc(String stopDesc) {
		this.stopDesc = stopDesc;
	}
	public String getStopLat() {
		return stopLat;
	}
	public void setStopLat(String stopLat) {
		this.stopLat = stopLat;
	}
	public String getStopLon() {
		return stopLon;
	}
	public void setStopLon(String stopLon) {
		this.stopLon = stopLon;
	}
	public String getStopUrl() {
		return stopUrl;
	}
	public void setStopUrl(String stopUrl) {
		this.stopUrl = stopUrl;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getParentStation() {
		return parentStation;
	}
	public void setParentStation(String parentStation) {
		this.parentStation = parentStation;
	}
}
