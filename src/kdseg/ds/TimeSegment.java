package kdseg.ds;

public class TimeSegment {
	public Long starttime;
	public Long stoptime;
	
	public TimeSegment(Long starttime,Long stoptime){
		this.starttime=starttime;
		this.stoptime=stoptime;
	}
	
	public boolean containsDotTime(Dot dot){
		if(dot.seg.starttime<starttime){
			if(dot.seg.stoptime>starttime){
				return true;
			}else{
				return false;
			}
		}else{
			if(dot.seg.starttime<stoptime){
				return true;
			}else{
				return false;
			}
		}
	}
}
