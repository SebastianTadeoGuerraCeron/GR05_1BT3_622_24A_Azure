package service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ModeradorService {
    private static final String[] PALABRAS_OFENSIVAS = {
            "puta", "zorra", "mierda", "tonto", "estúpida", "idiota", "imbécil", "feo", "asqueroso", "burro", "tarado", "bobo",
            "adefecioso", "malo", "estúpido", "zopenco", "patán", "cretino", "baboso", "loco", "menso", "cobarde",
            "holgazán", "gusano", "mocoso", "caradura", "bruto", "mugroso", "animal", "cerdo",
            "vago", "chismoso", "hipócrita", "mentiroso", "rata", "traidor", "insolente",
            "ignorante", "payaso", "sucio", "maleducado", "necio", "desgraciado", "bastardo", "basura"
    };

    private static final Pattern PATRON_OFENSIVO;

    static {
        String pattern = String.join("|", PALABRAS_OFENSIVAS);
        //PATRON_OFENSIVO = Pattern.compile("\\b(" + pattern + ")\\b", Pattern.CASE_INSENSITIVE);
        PATRON_OFENSIVO = Pattern.compile("\\b(" + pattern + ")\\b|[^a-zA-Z0-9](" + pattern + ")[^a-zA-Z0-9]", Pattern.CASE_INSENSITIVE);

    }

    public boolean verificarOfensivo(String contenido) {
        if (contenido == null || contenido.isEmpty()) {
            return false;
        }

        Matcher matcher = PATRON_OFENSIVO.matcher(contenido);
        boolean resultado = matcher.find();
        System.out.println("Verificación de contenido ofensivo para: " + contenido + " Resultado: " + resultado);
        return resultado;
    }

    public boolean esMenorOIgualA200(String contenido) {
        return contenido.length() <= 200;
    }

}
