package foro.hub.api.domain.foro;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroForoHub(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
