/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import services.Cliente;
import services.ClienteServiceImpl;
import services.Clientes;
import utils.JsfUtils;

/**
 *
 * @author Joao
 */
@ManagedBean
public class ClienteConverter implements Converter {

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtils.isDummySelectItem(component, value)) {
            return null;
        }
        Clientes c = new Clientes();
        ClienteServiceImpl imp = c.getClienteServiceImplPort();
        Cliente cliente = imp.buscarCliente(getKey(value));
        if (cliente != null) {
            System.out.println("CLIENTE->"+cliente.getNombres());
        } else {
            System.out.println("nulloooooo");
        }
        return cliente;
    }

    Integer getKey(String value) {
        Integer key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = Integer.parseInt(value);
        return key;
    }

    String getStringKey(Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Cliente) {
            Cliente o = (Cliente) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cliente.class.getName()});
            return null;
        }

    }
}
