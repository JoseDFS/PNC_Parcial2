package com.uca.capas.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema="public",name="cat_libro")
public class Libro {

	
	@Id
	@Column(name="c_libro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int c_libro;
	
	@Column(name="s_titulo",nullable=false,length=500)
	@Size(max=500,message="El campo sobrepasa la cantidad de 500 caracteres")
	@NotEmpty(message="El campo no puede estar vacio")
	private String s_titulo;
	
	@Column(name="s_autor")
	@Size(max=150,message="El campo sobrepasa la cantidad de 150 caracteres")
	@NotEmpty(message="El campo no puede estar vacio")
	private String s_autor;
	
	@Column(name="s_isbn")
	@Size(max=10,message="El campo sobrepasa la cantidad de 10 caracteres")
	@NotEmpty(message="El campo no puede estar vacio")
	private String s_isbn;
	
	@Column(name="b_estado")
	private Boolean b_estado;
	
	
	@Column(name="f_ingreso")
	@CreationTimestamp
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss a")
	private LocalDateTime f_ingreso;
	
	public void setB_estado(Boolean b_estado) {
		this.b_estado = b_estado;
	}
	public Boolean getB_estado() {
		return b_estado;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;
	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss a")
	public LocalDateTime getF_ingreso() {
		return f_ingreso;
	}
	public void setF_ingreso(LocalDateTime f_ingreso) {
		this.f_ingreso = f_ingreso;
	}
	
	public String getEstadoDelegate() {
		if(this.b_estado == null) {
			return "";
		}else {
			return b_estado == true ?"Activo":"Inactivo";
		}
	}
	public Libro() {
		
	}
	public int getC_libro() {
		return c_libro;
	}
	public void setC_libro(int c_libro) {
		this.c_libro = c_libro;
	}
	public String getS_titulo() {
		return s_titulo;
	}
	public void setS_titulo(String s_titulo) {
		this.s_titulo = s_titulo;
	}
	public String getS_autor() {
		return s_autor;
	}
	public void setS_autor(String s_autor) {
		this.s_autor = s_autor;
	}
	public String getS_isbn() {
		return s_isbn;
	}
	public void setS_isbn(String s_isbn) {
		this.s_isbn = s_isbn;
	}
	
	
	
	
}
