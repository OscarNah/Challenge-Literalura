package com.alurachallenge.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="libros")
public class Libro {

    //----- variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private List<String> idioma;
    private Integer numeroDeDescargas;

    public Libro(){
    }
    public Libro (DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autor = new Autor(datosLibros.autores().get(0)); //traerá el primero
        this.idioma = datosLibros.idioma(); //get(0)
        this.numeroDeDescargas = datosLibros.numeroDeDescargas() != null ? datosLibros.numeroDeDescargas() : 0;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }
    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        String mensaje = String.format("""
                ++++++++ Libro ++++++++
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %s
                ++++++++++++++++++++++++
                """, titulo, autor.getNombre(),idioma,numeroDeDescargas);
        return mensaje;
    }
}
