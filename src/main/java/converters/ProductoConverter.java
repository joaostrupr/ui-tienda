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
import services.Producto;
import services.ProductoServiceImpl;
import services.Productos;
import utils.JsfUtils;

/**
 *
 * @author Joao
 */
@ManagedBean
public class ProductoConverter implements Converter {

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtils.isDummySelectItem(component, value)) {
            return null;
        }
        Productos p = new Productos();
        ProductoServiceImpl imp = p.getProductoServiceImplPort();
        Producto producto = imp.buscarProducto(getKey(value));
        if(producto==null){
            System.out.println("nulkllo producto");
        }else{
            System.out.println("PRODUCT->"+producto.getNombre());
        }
        return  producto;
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
        if (object instanceof Producto) {
	    Producto o = (Producto) object;
	    return getStringKey(o.getId());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Producto.class.getName()});
	    return null;
	}
       
    }
}
