package com.example.FacturaYa.service;

import com.example.FacturaYa.entity.Factura;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

@Service
public class XmlFacturaService {

    public void generateXml(Factura factura) throws JAXBException, IOException {
        if (factura == null) {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }

        JAXBContext context = JAXBContext.newInstance(Factura.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String fileName = "Factura_" + factura.getCodigo() + ".xml";
        File file = new File(fileName);

        marshaller.marshal(factura, file);
        System.out.println("Factura XML generada con éxito: " + fileName);
    }
}