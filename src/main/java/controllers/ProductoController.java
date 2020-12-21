/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import services.Cliente;
import services.ClienteServiceImpl;
import services.Clientes;
import services.Orden;
import services.OrdenServiceImpl;
import services.Ordenes;
import services.Producto;
import services.ProductoServiceImpl;
import services.Productos;

/**
 *
 * @author Joao
 */
@Named
@ViewScoped
public class ProductoController implements Serializable {

    public ProductoController() {

    }

    private List<Producto> productos;
    private Producto productoSeleccionado;
    private String nombre, descripcion;
    private BigDecimal precio;

    public List<Producto> getProductos() {
        if (productos == null) {
            listar();
        }
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void actualizar() {
        Productos productoService = new Productos();
        ProductoServiceImpl imp = productoService.getProductoServiceImplPort();
        imp.actualizarProducto(productoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto "+productoSeleccionado.getId()+" actualizado"));
    }

    public void eliminar() {
        Productos productoService = new Productos();
        ProductoServiceImpl imp = productoService.getProductoServiceImplPort();
        imp.eliminarProducto(productoSeleccionado);
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto Eliminado"));
    }

    public void agregar() {
        Productos productoService = new Productos();
        ProductoServiceImpl imp = productoService.getProductoServiceImplPort();
        imp.agregarProducto(nombre, descripcion, precio);
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agregó el producto"));
    }

    public void listar() {
        Productos productoService = new Productos();
        ProductoServiceImpl imp = productoService.getProductoServiceImplPort();
        productos = imp.listarProductos();
    }
}
