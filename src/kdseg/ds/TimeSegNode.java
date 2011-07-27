package kdseg.ds;

import java.util.LinkedList;

public class TimeSegNode extends DSNode{
	public TimeSegment centerComparison = null;
	public DSNode left=null;
	public DSNode center=null;
	public DSNode right=null;
	
	public TimeSegNode(int depth) {
		super(depth);
	}

	@Override
	public void addDot(Dot dot) {
		try{
			if(centerComparison!=null){//need to send dot down to left right or center
				if(dot.seg.starttime>centerComparison.stoptime){//fully left
					left.addDot(dot);
				}else if(dot.seg.stoptime<centerComparison.starttime){//fully right
					right.addDot(dot);
				}else{ //must overlap with center
					center.addDot(dot);
				}
			}else{//check if at 10 yet, if so then create DSNode of type lngitude
				if(this.objects.size()>10){
					objects.add(dot);
					Long avestarttime= new Long(0);Long avestoptime=new Long(0);
					for(int i=0;i<objects.size();i++){
						Dot d=objects.get(i);
						avestarttime+=d.seg.starttime;
						avestoptime+=d.seg.stoptime;
					}
					avestarttime/=objects.size();avestoptime/=objects.size();
					centerComparison=new TimeSegment(avestarttime,avestoptime);
					left=new LngNode(depth+1);
					right=new LngNode(depth+1);
					center=new LngNode(depth+1);
					while(!objects.isEmpty()){
						Dot d = objects.removeFirst();
						if(d.seg.starttime>centerComparison.stoptime){//fully left
							left.addDot(d);
						}else if(d.seg.stoptime<centerComparison.starttime){//fully right
							right.addDot(d);
						}else{ //must overlap with center
							center.addDot(d);
						}
					}
				}else{ //just add dot to object list
					objects.add(dot);
				}
			}
		}catch(Exception e){
			System.err.println("Error adding dot at "+depth+" "+e);
		}
	}

	@Override
	public int compareTo(Object obj) {
		return 0;
	}

	@Override
	public void removeDot(Dot dot) {
		if(centerComparison!=null){
			if(dot.seg.starttime>centerComparison.stoptime){//fully left
				left.removeDot(dot);
			}else if(dot.seg.stoptime<centerComparison.starttime){//fully right
				right.removeDot(dot);
			}else{ //must overlap with center
				center.removeDot(dot);
			}
		}else{
			this.objects.remove(dot);
		}
	}

	@Override
	public void copyDots(LinkedList<Dot> result) {
		if(centerComparison!=null){ //get dots from sub nodes
			this.left.copyDots(result);
			this.center.copyDots(result);
			this.right.copyDots(result);
		}else{ //this node has not subdivided yet
			for(int i=0;i<objects.size();i++){
				result.add(objects.get(i));
			}
		}
	}

	@Override
	public void searchDots(GeographicalRange georng, TimeSegment timerng,
			LinkedList<Dot> result) {
		if(centerComparison!=null){ //simple check of time and which to further search
			//firstcheck fully right of center
			if(centerComparison.stoptime<timerng.starttime){//do west look only
				left.searchDots(georng, timerng, result);
			}else{
				if(centerComparison.starttime>timerng.stoptime){//do east look only
					right.searchDots(georng, timerng, result);
				}else{ //do both but west first
					center.searchDots(georng, timerng, result);
					if(centerComparison.stoptime<timerng.stoptime){
						left.searchDots(georng, timerng, result);
					}
					if(centerComparison.starttime>timerng.stoptime)
						right.searchDots(georng, timerng, result);
				}
			}
		}else{//just search nodes in objects list for space and time requirement
			for(int i=0;i<objects.size();i++){
				Dot d=objects.get(i);
				if(georng.containsDot(d) && timerng.containsDotTime(d)){
					result.add(d);
				}
			}
		}
		
	}

}
