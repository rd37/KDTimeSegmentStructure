package kdseg.ds;

import java.util.LinkedList;

public class KDSegDataStructure {
	public DSNode rootnode=new LngNode(0);//create new node at level zero
	
	public KDSegDataStructure(){}
	
	public void addDot(Dot dot){
		rootnode.addDot(dot);
	}
	
	public void removeDot(Dot dot){
		rootnode.removeDot(dot);
	}
	
	public LinkedList<Dot> searchDots(GeographicalRange georng,TimeSegment timerng){
		LinkedList<Dot> result = new LinkedList<Dot>();
		rootnode.searchDots(georng, timerng,result);
		return result;
	}
}
