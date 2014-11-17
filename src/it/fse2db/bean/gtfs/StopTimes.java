package it.fse2db.bean.gtfs;

public class StopTimes {
	public String tripId;
	public String arrivalTime;
	public String departureTime;
	public String stopId;
	public String stopSequence;
	public String pickupType;
	public String dropOffType;
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getStopSequence() {
		return stopSequence;
	}
	public void setStopSequence(String stopSequence) {
		this.stopSequence = stopSequence;
	}
	public String getPickupType() {
		return pickupType;
	}
	public void setPickupType(String pickupType) {
		this.pickupType = pickupType;
	}
	public String getDropOffType() {
		return dropOffType;
	}
	public void setDropOffType(String dropOffType) {
		this.dropOffType = dropOffType;
	}
	
}
