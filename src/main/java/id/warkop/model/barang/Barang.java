package id.warkop.model.barang;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Barang {
	@Id private Long id;
	
	private String nama;
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getId() {
		return id;
	}
	//ctor
	public Barang(String nama) {
		this();
		this.nama = nama;
	}
	
	private Barang() {
	}
	
}
