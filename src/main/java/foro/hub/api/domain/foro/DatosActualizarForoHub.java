package foro.hub.api.domain.foro;

import jakarta.validation.constraints.NotNull;


public record DatosActualizarForoHub(@NotNull Long id, String titulo, String mensaje, String autor, String curso ) {
}
