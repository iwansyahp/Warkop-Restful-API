package id.warkop.model.barang;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

public class BarangCtrl {
	
	// terapkan pagination
	public List<Barang> daftar(int offset, int limit) {
		
		List<Barang> daftarBarang = ofy()
		          .load()
		          .type(Barang.class)
		          .offset(offset)
		          .limit(limit)
		          .list();
		
		return daftarBarang;
	}
	
	public Barang baru(String nama) throws Exception {
		Barang barang = null;
		if ( nama == null) {
			throw new Exception("Nama tidak boleh kosong");
		}else {
	    	barang = new Barang(nama);
	    	// Use Objectify to save the greeting and now() is used to make the call synchronously as we
		    // will immediately get a new page using redirect and we want the data to be present.
		  
		    ofy().save().entity(barang).now();
	    } 
		return barang;
	}
	
	public Barang ubah(Long id, String namaBaru) throws Exception {
		Barang barang = null;
		
		Result<Barang> result = ofy().load().key(Key.create(Barang.class, id));
		barang = result.now();
		
		if (namaBaru == null) {
			throw new Exception("Nama tidak boleh kosong");
		} else {
			// Ubah
			barang.setNama(namaBaru);
			
			// simpan
			ofy().save().entity(barang).now();
			
			result = ofy().load().key(Key.create(Barang.class, id));
			barang = result.now();
		}
		
		return barang;
	}
	
	public Barang cari(Long id) {
		Barang barang = null;
		// Get it back
		Result<Barang> result = 
				ofy().load().key(Key.create(Barang.class, id));
		// .safe()  >> When null throw NotFoundException
		// .now() >> null
		barang = result.now();
		return barang;
	}
	

	public void hapus(Long id) throws Exception {
		Barang barang = cari(id);
		// Hapus
		ofy().delete().entity(barang).now();
	}
}
