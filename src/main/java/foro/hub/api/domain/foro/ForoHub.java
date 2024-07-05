package foro.hub.api.domain.foro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "foro_hub")
@Entity(name = "Foro_Hub")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ForoHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_de_creacion;
    private String autor;
    private String curso;
    private Boolean activo;


    public ForoHub(DatosRegistroForoHub datosRegistroForoHub) {
        this.activo = true;
        this.titulo = datosRegistroForoHub.titulo();
        this.mensaje = datosRegistroForoHub.mensaje();
        this.autor = datosRegistroForoHub.autor();
        this.fecha_de_creacion = LocalDateTime.now();
        this.curso = datosRegistroForoHub.curso();
    }

    public void actualizarDatos(DatosActualizarForoHub datosActualizarForoHub) {
        if (datosActualizarForoHub.titulo() != null) {
            this.titulo = datosActualizarForoHub.titulo();
        }
        if (datosActualizarForoHub.mensaje() != null) {
            this.mensaje = datosActualizarForoHub.mensaje();
        }
        if (datosActualizarForoHub.autor() != null) {
            this.autor = datosActualizarForoHub.autor();
        }
        if (datosActualizarForoHub.curso() != null) {
            this.curso = datosActualizarForoHub.curso();
        }
    }

    public void desactivarForoHub() {
        this.activo = false;
    }

    @PrePersist
    protected void onCreate() {
        fecha_de_creacion = LocalDateTime.now();
    }
}
