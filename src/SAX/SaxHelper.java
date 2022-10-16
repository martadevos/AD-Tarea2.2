package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHelper extends DefaultHandler {
    boolean esFecha = false;
    boolean esPrecioUnidad = false;
    boolean esUnidades = false;
    boolean esDescuento = false;
    double cantProductos = 0;
    double unidadesProducto = 0;
    double precioTotal = 0;
    double descuentoTotal = 0;
    int numeroCompras = 1;

    public void startElement(String uri, String localName, String elementos, Attributes atributos) throws SAXException {
        switch (elementos) {
            case "fecha":
                esFecha = true;
                break;
            case "producto":
                cantProductos++;
                break;
            case "unidades":
                esUnidades = true;
                break;
            case "precio_unidad":
                esPrecioUnidad = true;
                break;
            case "descuento":
                esDescuento = true;
                break;
        }
    }

    public void characters(char[] ch, int inicio, int length) throws SAXException {
        if (esFecha) {
            System.out.println("Compra "+ numeroCompras +": ");
            System.out.println("Fecha: " + new String(ch, inicio, length));
            numeroCompras++;
            esFecha = false;
        }
        if (esPrecioUnidad) {
            precioTotal += (unidadesProducto>0)?doubleConverter(new String(ch, inicio, length))  * unidadesProducto: doubleConverter(new String(ch, inicio, length)) ;
            esPrecioUnidad = false;
        }
        if (esUnidades) {
            unidadesProducto = doubleConverter(new String(ch, inicio, length)) ;
            cantProductos += unidadesProducto - 1;
            esUnidades = false;
        }
        if (esDescuento) {
            descuentoTotal += (unidadesProducto>0)?doubleConverter(new String(ch, inicio, length)) * unidadesProducto: doubleConverter(new String(ch, inicio, length)) ;
            esDescuento = false;
        }
    }

    public void endElement(String uri, String localName, String elementos) throws SAXException {
        if (elementos.equals("ticket")) {
            System.out.println("Cantidad: " + cantProductos);
            System.out.println("Precio total: " + precioTotal);
            System.out.println("Descuento total: " + descuentoTotal);
            System.out.println("Resumen: " + String.valueOf(precioTotal - descuentoTotal).substring(0,4));

            cantProductos = 0;
            precioTotal = 0;
            descuentoTotal = 0;
        }
        if (elementos.equals("producto")) {
            unidadesProducto = 0;
        }
    }

    public double doubleConverter(String stringToConvert){
        return Double.parseDouble(stringToConvert.replace(',', '.'));
    }
}
