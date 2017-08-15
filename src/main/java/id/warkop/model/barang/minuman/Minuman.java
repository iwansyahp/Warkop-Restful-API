package id.warkop.model.barang.minuman;

import com.googlecode.objectify.annotation.Subclass;

import id.warkop.model.barang.Barang;

/**
 * @author xhaa
 *
 */
@Subclass(index=true)
public class Minuman extends Barang {
	
	private boolean gelasBesar;
	
	public boolean isGelasBesar() {
		return gelasBesar;
	}

	public void setGelasBesar(boolean gelasBesar) {
		this.gelasBesar = gelasBesar;
	}

	public Minuman(String nama, boolean gelasBesar) {
		super(nama);
		this.gelasBesar = gelasBesar;
	}
	
	protected Minuman() {
		super();
	}
	
}
