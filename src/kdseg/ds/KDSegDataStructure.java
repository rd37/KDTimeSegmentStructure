package kdseg.ds;

import java.util.LinkedList;

public class KDSegDataStructure {
	private static KDSegDataStructure kd = new KDSegDataStructure();
	public DSNode rootnode=new LngNode(0);//create new node at level zero
	
	private KDSegDataStructure(){}
	
	public static KDSegDataStructure getInstance(){return kd;};
	
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
	
	public void printStructure(){
		LinkedList<StringBuffer> tree = new LinkedList<StringBuffer>();
		rootnode.print(tree);
		for(int i=0;i<tree.size();i++){
			System.out.println(tree.get(i).toString());
		}
	}
}
