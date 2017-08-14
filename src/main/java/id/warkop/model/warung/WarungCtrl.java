package id.warkop.model.warung;

import static com.googlecode.objectify.ObjectifyService.ofy;

import id.warkop.model.barang.Barang;

public class WarungCtrl {
	
	public Warung baru(String nama, String alamat, String noTelp, String namaPemilik) throws Exception {
		Warung warung = null;
		
		if ( nama == null || nama.equals("")) {
			throw new Exception("Nama tidak boleh kosong");
		}else {
	    	warung = new Warung(nama, alamat, noTelp, namaPemilik);
	    	
		    ofy().save().entity(warung).now();
	    } 
		return warung;
	}
}
