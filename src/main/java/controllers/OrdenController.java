/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
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
public class OrdenController implements Serializable {

    public OrdenController() {

    }

    @PostConstruct
    public void iniciar() {
        if (clientes == null) {
            Clientes c = new Clientes();
            ClienteServiceImpl imp = c.getClienteServiceImplPort();
            clientes = imp.listarClientes();
        }

        if (productos == null) {
            Productos productoService = new Productos();
            ProductoServiceImpl imp = productoService.getProductoServiceImplPort();
            productos = imp.listarProductos();
        }

        if (ordenes == null) {
            Ordenes o = new Ordenes();
            OrdenServiceImpl imp = o.getOrdenServiceImplPort();
            ordenes = imp.listarOrdenes();
        }
        ordenNueva = new Orden();

    }
    private List<Orden> ordenes;
    private List<Cliente> clientes;
    private List<Producto> productos;

    private Orden ordenSeleccionada = new Orden(), ordenNueva = new Orden();

    private Integer cantidad, idProducto, idCliente;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Orden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public Orden getOrdenNueva() {
        return ordenNueva;
    }

    public void setOrdenNueva(Orden ordenNueva) {
        this.ordenNueva = ordenNueva;
    }

    public void setOrdenSeleccionada(Orden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public List<Cliente> getClientes() {

        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Producto> getProductos() {

        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Orden> getOrdenes() {

        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public void guardar() {
        Ordenes ordenesService = new Ordenes();
        OrdenServiceImpl imp = ordenesService.getOrdenServiceImplPort();
        imp.agregarOrden(idCliente, idProducto, cantidad);
        ordenes = imp.listarOrdenes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Orden Agregada"));
    }

    public void eliminar() {
        Ordenes ordenesService = new Ordenes();
        OrdenServiceImpl imp = ordenesService.getOrdenServiceImplPort();
        imp.eliminarOrden(ordenSeleccionada);
        ordenes = imp.listarOrdenes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Orden Eliminada"));
    }

    public void actualizar() {
        Ordenes ordenesService = new Ordenes();
        OrdenServiceImpl imp = ordenesService.getOrdenServiceImplPort();
        imp.actualizarOrden(ordenSeleccionada.getId(), idCliente, idProducto, cantidad);
        ordenes = imp.listarOrdenes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Orden "+ordenSeleccionada.getId()+" actualizada"));
    }

    public void editar() {
        idProducto = ordenSeleccionada.getProducto().getId();
        idCliente = ordenSeleccionada.getCliente().getId();
        cantidad = ordenSeleccionada.getCantidad();
    }

}
