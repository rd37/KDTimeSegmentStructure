package kdseg.ds;

import java.util.LinkedList;

public class TimeSegNode extends DSNode{
	public TimeSegment centerComparision = null;
	public DSNode left=null;
	public DSNode center=null;
	public DSNode right=null;
	
	public TimeSegNode(int depth) {
		super(depth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDot(Dot dot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeDot(Dot dot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyDots(LinkedList<Dot> result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchDots(GeographicalRange georng, TimeSegment timerng,
			LinkedList<Dot> result) {
		// TODO Auto-generated method stub
		
	}

}
