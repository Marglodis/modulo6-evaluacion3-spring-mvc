package com.principal.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.principal.modelo.Producto;

@Controller
@RequestMapping("/")
public class ProductoControlador {
	
	private List<Producto> productos = new ArrayList<>();
	private int idCounter = 1;
	
	@RequestMapping("/")
    public String redirectToShowProducts() {
        return "redirect:/producto/mostrar";  // Redirige a la vista que muestra productos
    }
	
	@RequestMapping("/producto/agregar")
	public ModelAndView mostrarFormulario() {
		ModelAndView mav = new ModelAndView("agregarProducto"); //Vista del formulario
		mav.addObject("producto", new Producto(0,"","",0,0));
		return mav;
	}
	
    @RequestMapping(value = "/producto/procesar", method = RequestMethod.POST)
    public ModelAndView procesarFormulario(@ModelAttribute Producto producto) {
        producto.setId(idCounter++); // Asignar un ID único
        productos.add(producto);

        // Retornar la vista con la lista actualizada de productos
        ModelAndView mav = new ModelAndView("mostrarProductos"); 
        mav.addObject("productos", productos); 
        return mav;
    }

    @RequestMapping("/producto/mostrar")
    public ModelAndView mostrarProductos() {
        ModelAndView mav = new ModelAndView("mostrarProductos"); // Vista con lista de productos
        mav.addObject("productos", productos); // Atributo con lista de productos
        return mav;
    }
}
