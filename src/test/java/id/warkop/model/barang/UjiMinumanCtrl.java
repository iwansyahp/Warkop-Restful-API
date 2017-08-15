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
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

import id.warkop.model.barang.minuman.Minuman;
import id.warkop.model.barang.minuman.MinumanCtrl;

@RunWith(JUnit4.class)
public class UjiMinumanCtrl {
  private static final String NAMA_UJI = "sanger";
  private static final boolean GELAS_BESAR = false; 

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
    
    ObjectifyService.register(Barang.class);
    ObjectifyService.register(Minuman.class);

    closeable = ObjectifyService.begin();

    cleanDatastore(ds);
  }

  @After
  public void tearDown() {
    cleanDatastore(ds);
    helper.tearDown();
    closeable.close();
  }

  // pengujian subclass dari entity
  @Test
  public void baru() throws Exception {

    Minuman minuman = new MinumanCtrl().baru(NAMA_UJI, GELAS_BESAR);

    // cari entity yang telah disimpan
    minuman = new MinumanCtrl().cari(minuman.getId());
    
    // pastikan data yang disimpan pada Datastore sama dengan masukan
    assertEquals(minuman.getNama(), NAMA_UJI);
    assertEquals(minuman.isGelasBesar(), GELAS_BESAR);
  }
  
  @Test
  public void cari() throws Exception {

    Minuman m = new MinumanCtrl().baru(NAMA_UJI, GELAS_BESAR);
    
    Minuman mCari = new MinumanCtrl().cari(m.getId());
    
    assertEquals(m.getNama(), mCari.getNama());
  }
   
  @Test
  public void daftar3() throws Exception {

    Minuman m = new MinumanCtrl().baru(NAMA_UJI, GELAS_BESAR);
    Minuman m1 = new MinumanCtrl().baru(NAMA_UJI, GELAS_BESAR);
    Minuman m2 = new MinumanCtrl().baru(NAMA_UJI, GELAS_BESAR);
    
    List<Minuman> daftarMinuman = (List<Minuman>) new MinumanCtrl().daftar(0, 10);
    assertEquals(3, daftarMinuman.size());
    assertEquals(m.getNama(), daftarMinuman.get(0).getNama());
    assertEquals(m1.getNama(), daftarMinuman.get(1).getNama());
    assertEquals(m2.getNama(), daftarMinuman.get(2).getNama());
    
  }
  
  @Test
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
  
  @Test
  public void hapus() throws Exception {

    Barang b = new BarangCtrl().baru(NAMA_UJI);
    assertEquals(b.getNama(), NAMA_UJI);
    
    new BarangCtrl().hapus(b.getId());
    
    Barang bCari = new BarangCtrl().cari(b.getId());
    assertNull(bCari);
    
  }
  
}
