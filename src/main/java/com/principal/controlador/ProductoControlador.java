package com.principal.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.principal.modelo.Producto;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {
	
	private List<Producto> productos = new ArrayList<>();
	private int idCounter = 1;
	
	@GetMapping("/agregar")
	public ModelAndView mostrarFormulario() {
		ModelAndView mav = new ModelAndView("agregarProducto"); //Vista del formulario
		mav.addObject("producto", new Producto(0,"","",0,0));
		return mav;
	}
	
    @PostMapping("/procesar")
    public ModelAndView procesarFormulario(@ModelAttribute Producto producto) {
        producto.setId(idCounter++); // Asignar un ID único
        productos.add(producto);

        // Retornar la vista con la lista actualizada de productos
        ModelAndView mav = new ModelAndView("mostrarProductos"); 
        mav.addObject("productos", productos); 
        return mav;
    }

    @GetMapping("/mostrar")
    public ModelAndView mostrarProductos() {
        ModelAndView mav = new ModelAndView("mostrarProductos"); // Vista con lista de productos
        mav.addObject("productos", productos); // Atributo con lista de productos
        return mav;
    }
}
