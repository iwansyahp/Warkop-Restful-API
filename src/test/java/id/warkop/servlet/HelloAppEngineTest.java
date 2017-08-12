package id.warkop.servlet;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import id.warkop.servlet.HelloAppEngine;

public class HelloAppEngineTest {

  @Test
  public void test() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new HelloAppEngine().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
}
