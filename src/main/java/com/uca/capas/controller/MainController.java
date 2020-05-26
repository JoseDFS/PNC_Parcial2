package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.LibroDAO;
import com.uca.capas.dao.CategoriaDAO;
import com.uca.capas.domain.Libro;

import com.uca.capas.domain.Categoria;


@Controller
public class MainController {
	
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private LibroDAO libroDAO;
	
	
	
	@RequestMapping("/ingresar_libro")
	public ModelAndView ingresar_libro() {
		ModelAndView mav = new ModelAndView();
		Libro libro =new Libro();
		mav.addObject("libro",  libro);
		List<Categoria> categorias = null;
		categorias = categoriaDAO.findAll();
		mav.addObject("categoria", libro.getCategoria());
		mav.addObject("categorias", categorias);
		
		mav.setViewName("ingresar_libro");
		return mav;
	}
	
	@RequestMapping("/ingresar_categoria")
	public ModelAndView ingresar_cat() {
		ModelAndView mav = new ModelAndView();
		Categoria categoria =new Categoria();
		mav.addObject("categoria",  categoria);
		mav.setViewName("ingresar_categoria");
		return mav;
	}
	
	@RequestMapping("/index")
	public String initMain() {
		return "index";
	}
	
	@RequestMapping("/exito_libro")
	public ModelAndView exito(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			List<Categoria> categorias = null;
			categorias = categoriaDAO.findAll();
			mav.addObject("categoria", libro.getCategoria());
			mav.addObject("categorias", categorias);
			mav.addObject("exito", "");
			mav.setViewName("ingresar_libro");
		}else {
			libroDAO.insert(libro);
			mav.addObject("exito", "Libro guardado con exito!");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/exito_categoria")
	public ModelAndView exito(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.addObject("exito", "");
			mav.setViewName("ingresar_categoria");
		}else {
			categoriaDAO.insert(categoria);
			mav.addObject("exito", "Categoria guardada con exito!");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = libroDAO.findAll(); 
		try {
			
			mav.addObject("libros", libros);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		for (Libro libro : libros) {
			System.out.println(libro.getS_titulo());
		}
		mav.setViewName("listado");
		return mav;
	}
}
