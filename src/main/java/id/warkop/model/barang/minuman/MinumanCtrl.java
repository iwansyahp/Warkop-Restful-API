package id.warkop.model.barang.minuman;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

public class MinumanCtrl {
	
	// terapkan pagination
	public List<Minuman> daftar(int offset, int limit) {
		
		List<Minuman> daftarMinuman = ofy()
		          .load()
		          .type(Minuman.class)
		          .offset(offset)
		          .limit(limit)
		          .list();
		
		return daftarMinuman;
	}
	
	public Minuman baru(String nama, boolean gelasBesar) throws Exception {
		Minuman minuman = new Minuman();
		if ( nama == null) {
			throw new Exception("Nama tidak boleh kosong");
		} else {
			minuman = new Minuman(nama, gelasBesar);
	    	// Use Objectify to save the greeting and now() is used to make the call synchronously as we
		    // will immediately get a new page using redirect and we want the data to be present.
		  
		    ofy().save().entity(minuman).now();
	    }
		
		return minuman;
	}
	
	public Minuman ubah(Long id, String namaBaru, boolean gelasBesar) throws Exception {
		Minuman minuman = null;
		
		Result<Minuman> result = ofy().load().key(Key.create(Minuman.class, id));
		minuman = result.now();
		
		if (namaBaru == null) {
			throw new Exception("Nama tidak boleh kosong");
		} else {
			// Ubah
			minuman.setNama(namaBaru);
			minuman.setGelasBesar(gelasBesar);
			
			// simpan
			ofy().save().entity(minuman).now();
			
			result = ofy().load().key(Key.create(Minuman.class, id));
			minuman = result.now();
		}
		
		return minuman;
	}
	
	public Minuman cari(Long id) {
		Minuman minuman = null;
		// Get it back
		Result<Minuman> result = 
				ofy().load().key(Key.create(Minuman.class, id));
		// .safe()  >> When null throw NotFoundException
		// .now() >> null
		minuman = result.now();
		return minuman;
	}
	

	public void hapus(Long id) throws Exception {
		Minuman minuman = cari(id);
		// Hapus
		ofy().delete().entity(minuman).now();
	}
}
