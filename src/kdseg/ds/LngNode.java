package kdseg.ds;

import java.util.LinkedList;

public class LngNode extends DSNode{
	public double longitudeComparison=-1000;
	public DSNode westNode=null; //this needs to be a lat node
	public DSNode eastNode=null; //this needs to be a lat node
	
	public LngNode(int depth) {
		super(depth);
	}

	@Override
	public void addDot(Dot dot) {
		try{
			if(longitudeComparison!=-1000){ //this pass dot onto west or east node compared to comparison 
				if(dot.lng>longitudeComparison){
					this.eastNode.addDot(dot);
				}else{
					this.westNode.addDot(dot);
				}
			}else if(this.objects.size()>max){ // you need to pick a split and create east west nodes
				//find average
				double total=0;
				objects.add(dot);
				for(int i=0;i<objects.size();i++){
					total+=objects.get(i).lng;
				}
				longitudeComparison=total/objects.size();
				//create north and south nodes i.e. latnodes
				this.eastNode=new LatNode(depth+1);
				this.westNode=new LatNode(depth+1);
				
				//remove keys and add to east or west nodes
				while(!objects.isEmpty()){
					Dot d=objects.removeFirst();
					if(d.lng>longitudeComparison){
						this.eastNode.addDot(d);
					}else{
						this.westNode.addDot(d);
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
	public int compareTo(Object obj) {
		return 0;
	}

	@Override
	public void removeDot(Dot dot) {
		if(this.longitudeComparison!=-1000){
			if(dot.lng>longitudeComparison){
				this.eastNode.removeDot(dot);
			}else{
				this.westNode.removeDot(dot);
			}
		}else{
			this.objects.remove(dot);
		}
	}

	@Override
	public void searchDots(GeographicalRange georng,
			TimeSegment timerng,LinkedList<Dot> result) {
		if(this.longitudeComparison!=-1000){ //then further search required maybe down both nodes
			//firstcheck west then east but this depends on longitudeComparison
			if(georng.lngeast<longitudeComparison){//do west look only
				this.westNode.searchDots(georng, timerng, result);
			}else{
				if(georng.lngwest>longitudeComparison){//do east look only
					this.eastNode.searchDots(georng, timerng, result);
				}else{ //do both but west first
					this.westNode.searchDots(georng, timerng, result);
					this.eastNode.searchDots(georng, timerng, result);
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
	public void copyDots(LinkedList<Dot> result) {
		if(this.longitudeComparison!=-1000){ //get dots from sub nodes
			this.westNode.copyDots(result);
			this.eastNode.copyDots(result);
		}else{ //this node has not subdivided yet
			for(int i=0;i<objects.size();i++){
				result.add(objects.get(i));
			}
		}
	}

	public void print(LinkedList<StringBuffer> tree){
		if(tree.size()<(depth+1)){
			StringBuffer sb = new StringBuffer();
			sb.append(" Nlng:");
			for(int i=0;i<objects.size();i++){
				Dot d=objects.get(i);
				sb.append(d.userkey+",");
			}
			tree.add(depth, sb);
			if(westNode!=null)
				westNode.print(tree);
			if(eastNode!=null)
				eastNode.print(tree);
		}else{
			StringBuffer sb = tree.get(depth);
			sb.append(" Nlng:");
			for(int i=0;i<objects.size();i++){
				Dot d=objects.get(i);
				sb.append(d.userkey+",");
			}
			if(westNode!=null)
				westNode.print(tree);
			if(eastNode!=null)
				eastNode.print(tree);
		}
	}
}
