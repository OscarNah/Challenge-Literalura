package com.alurachallenge.literalura.principal;

import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.DatosGutendex;
import com.alurachallenge.literalura.model.DatosLibros;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibrosRepository;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    //variables
    private Scanner teclado = new Scanner(System.in);
    public final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorioLibros;
    private Optional<Libro> libroBuscado;

    private AutorRepository repositorioAutores;


    public Menu(LibrosRepository repository, AutorRepository autorRepository){
        //, AutorRepository autorRepository
        this.repositorioLibros = repository;
        this.repositorioAutores = autorRepository;
    }


    //método para mostrar el menú
    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0){

            System.out.println("\n++++ Elija una de las opciones del menú ++++");
            String menu = """
                1.- Buscar libros en GUTENDEX.
                2.- Buscar libro por título en la DB.
                3.- Listar los autores registrados en la DB.
                4.- Listar los libros registrados en la DB.
                5.- Listar los libros por idioma en GUTENDEX
                6.- Listar los autores vivos en un determinado año registrados en la DB.                
                0.- Salir de la aplicación
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1: getLibroPorTitulo();
                    break;
                case 2: mostrarLibroPorTitulo();
                    break;
                case 3: mostrarLosAutoresRegistrados();
                    break;
                case 4: mostrarLibrosRegistrados();
                    break;
                case 5: mostrarLibrosPorIdioma();
                    break;
                case 6: mostrarAutoresVivosPorAnio();
                break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    System.out.println("Vuelva pronto");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }

        }

    }

    public void getLibroPorTitulo(){
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var tituloLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        try{
            DatosGutendex datosGenerales = conversor.obtenerDatos(json,DatosGutendex.class);
            Optional<DatosLibros> libroBuscado = datosGenerales.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                    .findFirst();
            if(libroBuscado.isPresent()){
                DatosLibros datosLibros = libroBuscado.get();
                Libro libro = new Libro(datosLibros);
                System.out.println("Libro Encontrado ");
                System.out.println(libro.toString());
                repositorioLibros.save(libro);
                System.out.println("Libro guardado en la Base de datos.");
            }else {
                System.out.println("Libro no encontrado");
            }
        }catch (Exception e){
            System.out.println("--- Ha ocurrido un ERROR ---" + e.getMessage());
        }
    }

    //Buscar título en la BD
    public void mostrarLibroPorTitulo(){
        System.out.println("\nEscribe el nombre del libro que deseas buscar en la DB: ");
        var nombreLibro = teclado.nextLine();
        libroBuscado = repositorioLibros.findByTituloContainingIgnoreCase(nombreLibro);

        if (libroBuscado.isPresent()) {
            System.out.println("El libro buscado es: \n"+ libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    public void mostrarLibrosPorIdioma(){
        System.out.println("""
                \n¿En qué idioma deseas buscar los libros?
                 1. Escribe "es" para Español
                 2. Escribe "en" para Inglés
                 3. Escribe "fr" para Francés
                 4. Escribe "it" para Italiano""");

        System.out.print("\nEscibre tu opción aquí: ");
        var idiomaSeleccionado = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE+"/?languages=" + idiomaSeleccionado.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosGutendex.class);

        System.out.println(datosBusqueda.resultados());

    }
    public void mostrarLibrosRegistrados(){
        // Recuperar los libros almacenados en la base de datos y almacenarlos en la lista libros
        List<Libro> libros = repositorioLibros.findAll();
        libros.forEach(System.out::println);

    }
    public void mostrarLosAutoresRegistrados() {
        List<Autor> autores = repositorioAutores.findAll();
        if (!autores.isEmpty()) {
            System.out.println("\nAutores Registrados:");
            autores.forEach(autor -> System.out.println(autor.getNombre()));
        } else {
            System.out.println("No hay autores registrados en la base de datos.");
        }
    }

    private void mostrarAutoresVivosPorAnio() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea después del entero

        List<Autor> autoresVivos = repositorioAutores.findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(anio, anio);

        if (!autoresVivos.isEmpty()) {
            System.out.println("\nAutores vivos en el año " + anio + ":");
            autoresVivos.forEach(autor -> System.out.println(autor.getNombre()));
        } else {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        }
    }



}
