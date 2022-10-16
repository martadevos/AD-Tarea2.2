package SAX;

import java.util.List;

public class Compra {
    private String fecha;
    private List<Producto> listaProductos;

    public Compra(String fecha, List<Producto> listaProductos) {
        this.fecha = fecha;
        this.listaProductos = listaProductos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
