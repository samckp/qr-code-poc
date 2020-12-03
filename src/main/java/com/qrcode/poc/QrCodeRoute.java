package com.qrcode.poc;

import com.google.zxing.DecodeHintType;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.barcode.BarcodeDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class QrCodeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        DataFormat code = new BarcodeDataFormat();
        ((BarcodeDataFormat) code).addToHintMap(DecodeHintType.TRY_HARDER, true);
        from("direct:generateQrCode") //"{{inbox}}"
                .routeId("marshallingRoute")
                .setBody(simple("www.google.co.in"))
                .log(LoggingLevel.INFO,"marshalling --> ${body}")
                .marshal(code)
                .to("{{outbox}}")
                ;

        from("{{outbox}}")
                .routeId("unmarshallingRoute")
                .unmarshal(code)
                .log(LoggingLevel.INFO, "unmarshalling --> ${body}")
                .to("mock:out")
                ;
    }
}
