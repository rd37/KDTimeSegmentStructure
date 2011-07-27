package kdseg.ds;

public class GeographicalRange {
	public Double latnorth;
	public Double lngwest;
	public Double latsouth;
	public Double lngeast;
	
	public GeographicalRange(Double latnorth,Double lngwest,Double latsouth,Double lngeast){
		this.latnorth=latnorth;this.lngwest=lngwest;this.latsouth=latsouth;this.lngeast=lngeast;
	}
	
	public boolean containsDot(Dot d){
		if(lngwest<d.lng && lngeast>d.lng){
			if(latnorth>d.lat && latsouth<d.lat){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
