package kdseg.ds;

public class Dot {
	public Double lat;
	public Double lng;
	public TimeSegment seg;
	public Double userkey;
	
	public Dot(Double lat,Double lng,TimeSegment seg,Double userkey){
		this.lat=lat;this.lng=lng;this.seg=seg;this.userkey=userkey;
	}
}
