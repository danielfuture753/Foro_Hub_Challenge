package foro.hub.api.domain.foro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DatosListadoForoHub(Long id, String titulo, String mensaje, String autor, String curso, String fecha_de_creacion) {

    public static String formatFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaDeCreacion.format(formatter);
    }

    public DatosListadoForoHub(ForoHub foroHub) {
        this(foroHub.getId(), foroHub.getTitulo(), foroHub.getMensaje(), foroHub.getAutor(), foroHub.getCurso(), formatFechaDeCreacion(foroHub.getFecha_de_creacion()));
    }
}


