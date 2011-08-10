package kdseg.ds;

public class Dot {
	public Double lat;
	public Double lng;
	public TimeSegment seg;
	public Integer userkey;
	
	public Dot(Double lat,Double lng,TimeSegment seg,Integer userkey){
		this.lat=lat;this.lng=lng;this.seg=seg;this.userkey=userkey;
	}
}
