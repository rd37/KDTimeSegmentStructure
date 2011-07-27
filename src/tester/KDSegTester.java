package tester;

import java.util.LinkedList;
import java.util.Random;

import kdseg.ds.Dot;
import kdseg.ds.GeographicalRange;
import kdseg.ds.KDSegDataStructure;
import kdseg.ds.TimeSegment;

public class KDSegTester {
	private KDSegDataStructure structure = new KDSegDataStructure();
	private LinkedList<Dot> list = new LinkedList<Dot>();
	/**
	 * @param args
	 */
	public KDSegTester(){}
	
	public void execute(){
		Random random = new Random(567);
		for(int i=0;i<25;i++){
			Long stoptime = System.currentTimeMillis()/(1000+i);
			Long starttime = System.currentTimeMillis()/(2000+i);
			int keyInt = (int)(random.nextDouble()*1000);
			double key = ((double)keyInt)/1000;
			Dot d = new Dot( (random.nextDouble()-0.5)*300, (random.nextDouble()-0.5)*300, new TimeSegment(starttime,stoptime),key);
			structure.addDot(d);
			list.add(d);
		}
		structure.printStructure();
		System.out.println("----------");
		for(int i=0;i<list.size();i++){
			Dot d = list.get(i);
			System.out.println(d.userkey+":::"+d.lat+":"+d.lng+" "+d.seg.starttime+":"+d.seg.stoptime);
		}
		Double lat1 = new Double(-69);
		Double lng1 = new Double(-11);
		Double lat2 = new Double(-70);
		Double lng2 = new Double(-10);
		//Long starttime = new Long(655874753);
		//Long stoptime = new Long(1311749506);
		Long starttime = new Long(455874753);
		Long stoptime = new Long(755874753);
		LinkedList<Dot> searchResults = structure.searchDots(new GeographicalRange(lat1,lng1,lat2,lng2), new TimeSegment(starttime,stoptime));
		System.out.println("***results****");
		for(int i=0;i<searchResults.size();i++){
			Dot d = list.get(i);
			System.out.println(d.userkey+":::"+d.lat+":"+d.lng+" "+d.seg.starttime+":"+d.seg.stoptime);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KDSegTester tester = new KDSegTester();
		tester.execute();
	}

}
