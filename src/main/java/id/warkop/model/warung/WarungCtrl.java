package id.warkop.model.warung;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

public class WarungCtrl {
	
	// terapkan pagination
	public List<Warung> daftar(int offset, int limit) {
		
		List<Warung> daftarWarung = ofy()
		          .load()
		          .type(Warung.class)
		          .offset(offset)
		          .limit(limit)
		          .list();
		
		return daftarWarung;
	}
		
	public Warung baru(String nama, String alamat, String noTelp, String namaPemilik) 
			throws Exception {
		Warung warung = null;
		
		if ( nama == null || nama.equals("")) {
			throw new Exception("Nama tidak boleh kosong");
		}else {
	    	warung = new Warung(nama, alamat, noTelp, namaPemilik);
	    	
		    ofy().save().entity(warung).now();
	    } 
		return warung;
	}
	
	public Warung ubah(Long id, String namaBaru) throws Exception {
		Warung warung = null;
		
		Result<Warung> result = ofy().load().key(Key.create(Warung.class, id));
		warung = result.now();
		
		if (namaBaru == null || namaBaru.equals("")) {
			throw new Exception("Nama tidak boleh kosong");
		} else {
			// Ubah
			warung.setNama(namaBaru);
			
			// simpan
			ofy().save().entity(warung).now();
			
			result = ofy().load().key(Key.create(Warung.class, id));
			warung = result.now();
		}
		
		return warung;
	}
	
	public Warung cari(Long id) {
		Warung warung = null;
		// Get it back
		Result<Warung> result = 
				ofy().load().key(Key.create(Warung.class, id));
		// .safe()  >> When null throw NotFoundException
		// .now() >> null
		warung = result.now();
		return warung;
	}
	

	public void hapus(Long id) throws Exception {
		Warung warung = cari(id);
		// Hapus
		ofy().delete().entity(warung).now();
	}
}
