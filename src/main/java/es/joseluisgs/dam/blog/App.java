package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.utils.ApplicationProperties;

public class App {
    public static void main(String[] args) {
        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " "
                + properties.readProperty("app.version") + " de " +
                properties.readProperty("app.curso"));

        Blog blog = Blog.getInstance();

        // Categorías
        blog.Categories();
    }
}
