package id.warkop.api.v1.warung;

import id.warkop.model.warung.Warung;

public class JSONWarung {
	
	public String getNama() {
		if(warung.getNama() == null) {
			return "";
		}
		return warung.getNama();
	}
	
	public Long getId() {
		if (warung.getId() == null) {
			return 0L;	
		}
		
		return (Long) warung.getId();
	}
	
	public String getAlamat() {
		if(warung.getAlamat() == null) {
			return "";
		}
		return warung.getAlamat();
	}
	
	public String getNoTelp() {
		if (warung.getNoTelp() == null) {
			return "";	
		}
		
		return warung.getNoTelp();
	}
	
	public String getNamaPemilik() {
		if (warung.getNamaPemilik() == null) {
			return "";	
		}
		
		return warung.getNamaPemilik();
	}
	
	public JSONWarung() {
	
	}
	
	public JSONWarung(Warung warung) {
		this.warung = warung;
	}
	
	public void setwarung(Warung warung) {
		this.warung = warung;
	}
	
	private Warung warung;
}
