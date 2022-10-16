package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHelper extends DefaultHandler {
    boolean esFecha = false;
    boolean esPrecioUnidad = false;
    boolean esUnidades = false;
    boolean esDescuento = false;
    double cantProducto = 0;

    public void startElement(String uri, String localName, String elementos, Attributes atributos) throws SAXException {
        switch (elementos) {
            case "fecha":
                esFecha = true;
                break;
            case "ticket":
                System.out.println("Cantidad: "+cantProducto);
                cantProducto = 0;
                break;
            case "producto":
                cantProducto++;
                break;
            case "unidades":
                esUnidades = true;
                break;
        }
    }

    public void characters(char[] ch, int inicio, int length) throws SAXException {
        if (esFecha) {
            System.out.println("Fecha: " + new String(ch, inicio, length));
            esFecha = false;
            return;
        }
        if (esPrecioUnidad) {
            System.out.println("Precio unidad: " + new String(ch, inicio, length));
            esPrecioUnidad = false;
            return;
        }
        if (esUnidades) {
            cantProducto+= Double.parseDouble(new String(ch, inicio, length).replace(',','.'))-1;
            esUnidades = false;
            return;
        }
        if (esDescuento) {
            System.out.println("Descuento: " + new String(ch, inicio, length));
            esDescuento = false;
            return;
        }
    }

    public void endElement(String uri, String localName, String elementos) throws SAXException {

    }
}
