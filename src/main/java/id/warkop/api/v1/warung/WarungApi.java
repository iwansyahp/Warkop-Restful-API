package id.warkop.api.v1.warung;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;

import id.warkop.Konstanta;
import id.warkop.api.v1.JSONPesan;
import id.warkop.model.warung.Warung;
import id.warkop.model.warung.WarungCtrl;

@Api(name = "warung",
	title = "Layanan Warung",
	version="v1",
	description="API untuk resource warung")
public class WarungApi {
	
	@ApiMethod(name="daftar", 
			path="warung", 
			httpMethod=HttpMethod.GET)
	public List<JSONWarung> daftar(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit) throws NotFoundException {
		List<JSONWarung> response = new ArrayList<JSONWarung>();
		
		// periksa nilai offset dan limit
		if (offset == null || offset < 0) {
			offset = Konstanta.PAGINATION_OFFSET;
		}
		if (limit == null || limit < 0) {
			limit = Konstanta.PAGINATION_LIMIT;
		}
		
		List<Warung> daftarWarung = 
				(List<Warung>) new WarungCtrl().daftar(offset, limit);
		
		if (daftarWarung.isEmpty()) {
			throw new NotFoundException("Daftar barang kosong");
		}
		
		JSONWarung w = new JSONWarung();
		for(Warung warung: daftarWarung) {
			w = new JSONWarung(warung);
			response.add(w);
		}
		
		return response;
	}
	@ApiMethod(name="baru", 
			path="warung", 
			httpMethod=HttpMethod.POST)
	public JSONWarung baru(
			@Named("nama") String nama, @Named("alamat") String alamat,
			@Named("noTelp") String noTelp,@Named("namaPemilik") String namaPemilik) 
					throws Exception {
		JSONWarung response = null;
		
		Warung warung = new WarungCtrl()
				.baru(nama,alamat, 
					noTelp, namaPemilik);
		
		response = new JSONWarung(warung);
		
		return response;
	}
	
	@ApiMethod(name="cari", 
			path="warung/{id}", 
			httpMethod=HttpMethod.GET)
	public JSONWarung cari(
			@Named("id") Long id) {
		JSONWarung response;
		
		Warung warung = 
				new WarungCtrl().cari(id);
		
		response = new JSONWarung(warung);
		
		return response;
	}
	
	@ApiMethod(name="ubahnama",
			path="warung/{id}/nama",
			httpMethod = HttpMethod.PUT)
	public JSONWarung ubahNama(
			@Named("id") Long id,
			@Named("namaBaru") String namaBaru) throws Exception {
		JSONWarung response = null;
		
		Long idW = (Long) Long.valueOf(id);
		Warung warung = new WarungCtrl().ubah(idW, namaBaru);
		
		response = new JSONWarung(warung);
		
		return response;
	}
	
	@ApiMethod(name="hapus",
			path="warung/{id}",
			httpMethod=HttpMethod.DELETE)
	public JSONPesan hapus(@Named("id") Long id) throws Exception {
		JSONPesan response = new JSONPesan("");
		
		new WarungCtrl().hapus(id);
		
		Warung warung = new WarungCtrl().cari(id);
		
		if(warung == null) {
			response.setMessage("Berhasil dihapus");
		}
		
		return response;
	}
	}
