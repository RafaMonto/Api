package com.example.FacturaYa.service;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.example.FacturaYa.entity.Factura;
import org.springframework.stereotype.Service;

@Service
public class XmlFacturaService {

    public String generarXml(Factura factura) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Factura.class);
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(factura, writer);
        return writer.toString();
    }
}