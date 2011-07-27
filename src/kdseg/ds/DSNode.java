package kdseg.ds;

import java.util.LinkedList;

public abstract class DSNode {
	public LinkedList<Dot> objects = new LinkedList<Dot>();
	public int depth;
	
	public abstract void addDot(Dot dot);
	public abstract void removeDot(Dot dot);
	public abstract void searchDots(GeographicalRange georng,TimeSegment timerng, LinkedList<Dot> result);
	public abstract void copyDots(LinkedList<Dot> result);
	public abstract int compareTo(Object obj);
	
	public DSNode(int depth){this.depth=depth;}
	
}
