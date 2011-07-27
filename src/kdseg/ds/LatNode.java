package kdseg.ds;

import java.util.LinkedList;

public class LatNode extends DSNode{
	public double latitudeComparison=-1000;
	public DSNode northNode=null;
	public DSNode southNode=null;
	
	public LatNode(int depth) {
		super(depth);
	}

	@Override
	public int compareTo(Object obj) {
		return 0;
	}

	@Override
	public void removeDot(Dot dot) {
		try{
			if(this.latitudeComparison!=-1000){
				if(dot.lat>latitudeComparison){
					this.northNode.removeDot(dot);
				}else{
					this.southNode.removeDot(dot);
				}
			}else{
				this.objects.remove(dot);
			}
		}catch(Exception e){
			System.err.println("Error removing dot "+dot.userkey+" "+e);
		}
	}

	@Override
	public void searchDots(GeographicalRange georng,
			TimeSegment timerng,LinkedList<Dot> result) {
		if(this.latitudeComparison!=-1000){ //then further search required maybe down both nodes
			//firstcheck north then south but this depends on latitudeComparison
			if(georng.latnorth<latitudeComparison){//do south look only
				this.southNode.searchDots(georng, timerng, result);
			}else{
				if(georng.latsouth>latitudeComparison){//do north look only
					this.northNode.searchDots(georng, timerng, result);
				}else{ //do both but south first
					this.southNode.searchDots(georng, timerng, result);
					this.northNode.searchDots(georng, timerng, result);
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

	@Override
	public void addDot(Dot dot) {
		try{
			if(latitudeComparison!=-1000){ //this pass dot onto north or south node compared to comparison 
				if(dot.lat>latitudeComparison){
					this.northNode.addDot(dot);
				}else{
					this.southNode.addDot(dot);
				}
			}else if(this.objects.size()>max){ // you need to pick a split and create north south nodes
				//find average
				objects.add(dot);
				double total=0;
				for(int i=0;i<objects.size();i++){
					total+=objects.get(i).lng;
				}
				latitudeComparison=total/objects.size();
				//create north and south time nodes i.e. timesegnodes
				this.northNode=new TimeSegNode(depth+1);
				this.southNode=new TimeSegNode(depth+1);
				
				//remove keys and add to north or south nodes
				while(!objects.isEmpty()){
					Dot d=objects.removeFirst();
					if(d.lat>latitudeComparison){
						this.northNode.addDot(d);
					}else{
						this.southNode.addDot(d);
					}
				}
			}else{//just add dot locally
				this.objects.add(dot);
			}
		}catch(Exception e){
			System.err.println("Should not happen, depth "+depth+" : "+e);
		}
	}

	@Override
	public void copyDots(LinkedList<Dot> result) {
		if(this.latitudeComparison!=-1000){ //get dots from sub nodes
			this.northNode.copyDots(result);
			this.southNode.copyDots(result);
		}else{ //this node has not subdivided yet
			for(int i=0;i<objects.size();i++){
				result.add(objects.get(i));
			}
		}
	}

	public void print(LinkedList<StringBuffer> tree){
		if(tree.size()<(depth+1)){
			StringBuffer sb = new StringBuffer();
			sb.append(" Nlat:");
			for(int i=0;i<objects.size();i++){
				Dot d=objects.get(i);
				sb.append(d.userkey+",");
			}
			tree.add(depth, sb);
		}else{
			StringBuffer sb = tree.get(depth);
			sb.append(" Nlat:");
			for(int i=0;i<objects.size();i++){
				Dot d=objects.get(i);
				sb.append(d.userkey+",");
			}
		}
		if(northNode!=null)
			northNode.print(tree);
		if(southNode!=null)
			southNode.print(tree);
	}
}
