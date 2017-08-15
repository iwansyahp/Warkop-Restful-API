package id.warkop.model;

import com.googlecode.objectify.ObjectifyService;

import id.warkop.model.barang.Barang;
import id.warkop.model.barang.minuman.Minuman;
import id.warkop.model.warung.Warung;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
	
  public void contextInitialized(ServletContextEvent event) {
    // This will be invoked as part of a warmup request, or the first user request if no warmup
    // request.
    ObjectifyService.register(Barang.class);
    ObjectifyService.register(Minuman.class);
    ObjectifyService.register(Warung.class);
  }

  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}