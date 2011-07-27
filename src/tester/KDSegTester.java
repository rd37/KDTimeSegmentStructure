package tester;

import java.util.Random;

import kdseg.ds.Dot;
import kdseg.ds.KDSegDataStructure;
import kdseg.ds.TimeSegment;

public class KDSegTester {
	private KDSegDataStructure structure = new KDSegDataStructure();
	/**
	 * @param args
	 */
	public KDSegTester(){}
	
	public void execute(){
		Random random = new Random(567);
		for(int i=0;i<5;i++){
			Long stoptime = System.currentTimeMillis()/(1+i);
			Long starttime = System.currentTimeMillis()/(2+i);
			int keyInt = (int)(random.nextDouble()*1000);
			double key = ((double)keyInt)/1000;
			Dot d = new Dot( (random.nextDouble()-0.5)*300, (random.nextDouble()-0.5)*300, new TimeSegment(starttime,stoptime),key);
			structure.addDot(d);
		}
		structure.printStructure();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KDSegTester tester = new KDSegTester();
		tester.execute();
	}

}
