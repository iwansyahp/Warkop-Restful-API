package id.warkop.api.v1.barang;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import id.warkop.Konstanta;
import id.warkop.api.v1.JSONPesan;
import id.warkop.model.barang.Barang;
import id.warkop.model.barang.BarangCtrl;

import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;

@Api(name = "barang",
	title = "Layanan Barang",
	version="v1",
	description="API untuk resource barang")
public class BarangApi {
	
	@ApiMethod(name="daftar", 
			path="barang", 
			httpMethod=HttpMethod.GET)
	public List<JSONBarang> daftar(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit) throws NotFoundException {
		List<JSONBarang> response = new ArrayList<JSONBarang>();
		
		// periksa nilai offset dan limit
		if (offset == null || offset < 0) {
			offset = Konstanta.PAGINATION_OFFSET;
		}
		if (limit == null || limit < 0) {
			limit = Konstanta.PAGINATION_LIMIT;
		}
		
		List<Barang> daftarBarang = 
				(List<Barang>) new BarangCtrl().daftar(offset, limit);
		
		if (daftarBarang.isEmpty()) {
			throw new NotFoundException("Daftar barang kosong");
		}
		
		JSONBarang b = new JSONBarang();
		for(Barang barang: daftarBarang) {
			b = new JSONBarang(barang);
			response.add(b);
		}
		
		return response;
	}
	
	@ApiMethod(name="baru",
			path="barang",
			httpMethod=HttpMethod.POST)
	public JSONBarang baru(
			@Named("nama") String nama) throws Exception {
		JSONBarang response = null;
		
		Barang barang = new BarangCtrl().baru(nama);
		
		response = new JSONBarang(barang);
		
		return response;
	}
	
	@ApiMethod(name="cari", 
			path="barang/{id}", 
			httpMethod=HttpMethod.GET)
	public JSONBarang cari(
			@Named("id") Long id) {
		JSONBarang response;
		
		Barang barang = 
				new BarangCtrl().cari(id);
		
		response = new JSONBarang(barang);
		
		return response;
	}
	
	@ApiMethod(name="ubahNama", 
			path="barang/{id}/nama", 
			httpMethod=HttpMethod.PUT)
	public JSONBarang ubahNama(
			@Named("id") Long id,
			@Named("namaBaru") String namaBaru) throws Exception {
		JSONBarang response = null;
		
		Long idB = (Long) Long.valueOf(id);
		Barang barang = new BarangCtrl().ubah(idB, namaBaru);
		
		response = new JSONBarang(barang);
		
		return response;
	}
	
	@ApiMethod(name="hapus", 
			path="barang/{id}", 
			httpMethod=HttpMethod.DELETE)
	public JSONPesan hapus(@Named("id") Long id) throws Exception {
		JSONPesan response = new JSONPesan("");
		
		new BarangCtrl().hapus(id);
		
		Barang barang = new BarangCtrl().cari(id);
		
		if(barang == null) {
			response.setMessage("Berhasil dihapus");
		}
		
		return response;
	}

}
