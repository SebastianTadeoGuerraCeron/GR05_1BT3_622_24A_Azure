package service;

import modelo.Comentario;
import modelo.Resena;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

class ComentarioServiceTest {
    private ComentarioService comentarioService;
    private Usuario usuario;
    private Resena resena;

    @BeforeEach
    void setUp() {
        comentarioService = new ComentarioService();
        usuario = new Usuario();
        usuario.setUsername("usuarioTest");

        resena = new Resena();
        resena.setRestaurante("Restaurante Test");
        resena.setTipoComida("Comida Test");
        resena.setDescripcion("Descripción Test");
        resena.setFechaCreacion(new Date());
        resena.setComentarios(new ArrayList<>()); // Inicialización de la lista en la prueba
    }

    @Test
    void given_comment_when_create_then_add_comment_to_review() throws Exception  {
        String contenido = "Este es un comentario de prueba.";
        Comentario comentario = comentarioService.crearComentario(contenido, usuario, resena);

        assertNotNull(comentario);
        assertEquals(contenido, comentario.getContenido());
        assertEquals(usuario, comentario.getUsuario());
        assertEquals(resena, comentario.getResena());
        assertTrue(resena.getComentarios().contains(comentario)); // Verifica que se añadió a la lista
        System.out.println("Comentario creado: " + comentario.getUsuario() + " - " + comentario.getContenido());
    }
}
