package id.warkop.model.barang;

import static id.warkop.model.barang.UtilitasUjiBarang.cleanDatastore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

import id.warkop.model.warung.Warung;
import id.warkop.model.warung.WarungCtrl;

@RunWith(JUnit4.class)
public class UjiWarungCtrl {
  private static final String NAMA = "zakir";
  private static final String ALAMAT = "alamat zakir";
  private static final String NOTELP = "notelp zakir";
  private static final String NAMA_PEMILIK = "zakir lah";

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(
          // Set no eventual consistency, that way queries return all results.
          // https://cloud.google.com/appengine/docs/java/tools/localunittesting#Java_Writing_High_Replication_Datastore_tests
          new LocalDatastoreServiceTestConfig()
              .setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

  private Closeable closeable;
  private DatastoreService ds;

  @Before
  public void setUp() throws Exception {

    helper.setUp();
    ds = DatastoreServiceFactory.getDatastoreService();

    ObjectifyService.register(Warung.class);

    closeable = ObjectifyService.begin();

    cleanDatastore(ds);
  }

  @After
  public void tearDown() {
    cleanDatastore(ds);
    helper.tearDown();
    closeable.close();
  }

  @Test
  public void baru() throws Exception {

    new WarungCtrl().baru(NAMA, ALAMAT, NOTELP, NAMA_PEMILIK);

    Query query = new Query("Warung");
    PreparedQuery pq = ds.prepare(query);
    Entity warung = pq.asSingleEntity();    // Should only be one at this point.
    assertEquals(warung.getProperty("nama"), NAMA);
    assertEquals(warung.getProperty("alamat"), ALAMAT);
    assertEquals(warung.getProperty("noTelp"), NOTELP);
    assertEquals(warung.getProperty("namaPemilik"), NAMA_PEMILIK);
  }
  

  /*public void cari() throws Exception {

    Barang b = new BarangCtrl().baru(NAMA_UJI);
    
    Barang bCari = new BarangCtrl().cari(b.getId());
    
    assertEquals(b.getNama(), bCari.getNama());
  }
   
  public void daftar3() throws Exception {

    Barang b = new BarangCtrl().baru(NAMA_UJI);
    Barang b1 = new BarangCtrl().baru(NAMA_UJI);
    Barang b2 = new BarangCtrl().baru(NAMA_UJI);
    
    List<Barang> daftarBarang = (List<Barang>) new BarangCtrl().daftar(0, 10);
    assertEquals(3, daftarBarang.size());
    assertEquals(b.getNama(), daftarBarang.get(0).getNama());
    assertEquals(b1.getNama(), daftarBarang.get(1).getNama());
    assertEquals(b2.getNama(), daftarBarang.get(2).getNama());
    
  }
  
  public void ubah() throws Exception {

    Barang b = new BarangCtrl().baru(NAMA_UJI);
    assertEquals(b.getNama(), NAMA_UJI);
    
    String namaBaru = "aja";
    
    Barang bUbah = new BarangCtrl().ubah(b.getId(), namaBaru);
    assertEquals(namaBaru, bUbah.getNama());
    
    String namaBaru2 = "aja2";
    
    bUbah = new BarangCtrl().ubah(b.getId(), namaBaru2);
    assertEquals(namaBaru2, bUbah.getNama());
    
    Barang bCari = new BarangCtrl().cari(b.getId());
    assertEquals(namaBaru2, bCari.getNama());
    
  }
  
  public void hapus() throws Exception {

    Barang b = new BarangCtrl().baru(NAMA_UJI);
    assertEquals(b.getNama(), NAMA_UJI);
    
    new BarangCtrl().hapus(b.getId());
    
    Barang bCari = new BarangCtrl().cari(b.getId());
    assertNull(bCari);
    
  }*/
  
}
