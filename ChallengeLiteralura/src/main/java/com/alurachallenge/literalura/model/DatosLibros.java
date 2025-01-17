package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List <String> idioma,
        @JsonAlias("download_count") Integer numeroDeDescargas) {

    @Override
    public String toString() {
        String mensaje = String.format("""
                \n
                ++++++++ Libro ++++++++
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %s
                ++++++++++++++++++++++++
                """, titulo, autores,idioma,numeroDeDescargas);
        return mensaje;
    }

}
