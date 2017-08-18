package id.warkop.model.warung;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Warung {
	
	@Id Long id;
	
	private String nama;
	private String alamat;
	private String noTelp;
	private String namaPemilik;
	
	public Long getId() {
		return id;
	}
	
	
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getNoTelp() {
		return noTelp;
	}
	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}
	
	public Warung(String nama, String alamat, String noTelp, String namaPemilik) {
		super();
		this.nama = nama;
		this.alamat = alamat;
		this.noTelp = noTelp;
		this.namaPemilik = namaPemilik;
	}
	
	private Warung() {
		
	}
}
