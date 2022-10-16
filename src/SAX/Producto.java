package SAX;

public class Producto {
    private double precio;
    private double descuento;
    private double cantidad;

    public Producto(double precio, double descuento, double cantidad) {
        this.precio = precio;
        this.descuento = descuento;
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
