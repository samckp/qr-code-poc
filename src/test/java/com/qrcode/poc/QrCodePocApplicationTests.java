package com.qrcode.poc;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class QrCodePocApplicationTests extends CamelTestSupport {

	@Override
	public RouteBuilder createRouteBuilder() throws Exception	{

		return new QrCodeRoute();
	}

	@Test
	public void testQrCodeRouteTest() throws Exception{

		template.sendBody("direct:generateQrCode","test");
		Thread.sleep(5000);
	}
}
