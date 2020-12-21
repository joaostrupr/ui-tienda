/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import services.Cliente;
import services.ClienteServiceImpl;
import services.Clientes;

/**
 *
 * @author Joao
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

    public ClienteController() {
    }

    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    String nombres, apellidos;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            listar();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public void actualizar() {
        Clientes clienteServices = new Clientes();
        ClienteServiceImpl imp = clienteServices.getClienteServiceImplPort();
        imp.actualizarCliente(clienteSeleccionado);
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Cliente "+clienteSeleccionado.getId()+" actualizado"));
    }

    public void eliminar() {
        Clientes clienteServices = new Clientes();
        ClienteServiceImpl imp = clienteServices.getClienteServiceImplPort();
        imp.eliminarCliente(clienteSeleccionado);
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Cliente eliminado"));
    }

    public void agregar() {
        Clientes clienteServices = new Clientes();
        ClienteServiceImpl imp = clienteServices.getClienteServiceImplPort();
        imp.agregarCliente(nombres, apellidos);
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agregó el cliente"));
    }

    public void listar() {
        Clientes clienteServices = new Clientes();
        ClienteServiceImpl imp = clienteServices.getClienteServiceImplPort();
        clientes = imp.listarClientes();
    }
}
