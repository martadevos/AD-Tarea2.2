import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Dom {

    /*Fecha de compra,
    qué cantidad de productos ha comprado ese día,
    suma de descuentos aplicados (si los hubiera) ese día,
    cuánto debe pagar finalmente ese día,
    resumen total de compra con la suma de los resultados anteriores.*/

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document docCompras = db.parse("src/compras.xml");
            //Declara variables auxiliares
            double cantidadProductos = 0, precioTotal = 0, descuentoTotal = 0, resumenCompra = 0, unidades;
            String fecha = null;

            //Obtiene el elemento raíz del documento
            Element raiz = docCompras.getDocumentElement();

            //Obtiene los nodos que tienen la etiqueta compra
            NodeList nlCompras = raiz.getElementsByTagName("compra");
            //Imprime cabecera
            System.out.printf("%-10s%-15s%-15s%-20s%-25s%-10s%n", "Compra", "fecha", "cantidad", "precio total", "descuento total", "resumen");
            //Repite tantas veces como compras haya
            for (int n = 0; n < nlCompras.getLength(); n++) {
                Node ndCompra = nlCompras.item(n);
                //comprueba que la compra es un elemento
                if (ndCompra.getNodeType() == Node.ELEMENT_NODE) {
                    Element compraElement = (Element) ndCompra;
                    //Obtiene los nodos que tienen la etiqueta producto
                    NodeList nlProductos = compraElement.getElementsByTagName("producto");
                    fecha = compraElement.getElementsByTagName("fecha").item(0).getTextContent();
                    cantidadProductos = 0;
                    precioTotal = 0;
                    descuentoTotal = 0;
                    //Repite tantas veces como productos haya
                    for (int m = 0; m < nlProductos.getLength(); m++) {
                        Node ndProducto = nlProductos.item(m);
                        //comprueba que el producto es un elemento
                        if (ndProducto.getNodeType() == Node.ELEMENT_NODE) {
                            Element productoElement = (Element) ndProducto;
                            //Saca unidades del producto
                            unidades = (productoElement.getElementsByTagName("unidades").item(0) == null) ? 1 : Double.parseDouble(productoElement.getElementsByTagName("unidades").item(0).getTextContent().replace(',', '.'));
                            //Suma unidades a las unidades totales de la compra
                            cantidadProductos += unidades;
                            //Suma el precio del producto por las unidades de producto al precio total de compra
                            precioTotal += Double.parseDouble(productoElement.getElementsByTagName("precio_unidad").item(0).getTextContent().replace(',', '.')) * unidades;

                            //Suma el descuento del producto por las unidades de producto al descuento total de compra
                            descuentoTotal += (productoElement.getElementsByTagName("descuento").item(0) == null) ? 0 : Double.parseDouble(productoElement.getElementsByTagName("descuento").item(0).getTextContent().replace(",", ".")) * unidades;
                        }
                    }
                    //Resta el descuento total al precio total para sacar el resumen de compra
                    resumenCompra = precioTotal - descuentoTotal;
                }
                //Imprime los datos con formato
                System.out.printf("%-10d%-15s%-15.3f%-20.2f%-25.2f%-10.2f%n", n + 1, fecha, cantidadProductos, precioTotal, descuentoTotal, resumenCompra);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
