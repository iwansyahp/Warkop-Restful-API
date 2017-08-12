package id.warkop.api.v1;

import id.warkop.model.barang.Barang;

public class JSONBarang {
	
	public String getNama() {
		if(barang == null) {
			return "";
		}
		return barang.getNama();
	}
	
	public Long getId() {
		if (barang == null) {
			return 0L;	
		}
		
		return (Long) barang.getId();
	}
	public JSONBarang() {
	
	}
	
	public JSONBarang(Barang barang) {
		this.barang = barang;
	}
	
	public void setBarang(Barang barang) {
		this.barang = barang;
	}
	
	private Barang barang;
}
