package foro.hub.api.controller;

import foro.hub.api.domain.foro.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class ForoHubController {

    @Autowired
    private ForoHubRepository foroHubRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaForoHub> registrarForoHub(@RequestBody @Valid DatosRegistroForoHub datosRegistroForoHub,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        ForoHub foroHub = foroHubRepository.save(new ForoHub(datosRegistroForoHub));
        DatosRespuestaForoHub datosRespuestaForoHub = new DatosRespuestaForoHub(foroHub.getId(), foroHub.getTitulo(), foroHub.getMensaje(),
                DatosListadoForoHub.formatFechaDeCreacion(foroHub.getFecha_de_creacion()), foroHub.getAutor(), foroHub.getCurso());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(foroHub.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaForoHub);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoForoHub>> listadoForoHub(@PageableDefault(size = 2) Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(foroHubRepository.findByActivoTrue(paginacion).map(DatosListadoForoHub::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarForoHub(@RequestBody @Valid DatosActualizarForoHub datosActualizarForoHub) {
        ForoHub foroHub = foroHubRepository.getReferenceById(datosActualizarForoHub.id());
        foroHub.actualizarDatos(datosActualizarForoHub);
        return ResponseEntity.ok(new DatosRespuestaForoHub(foroHub.getId(), foroHub.getTitulo(), foroHub.getMensaje(),
                DatosListadoForoHub.formatFechaDeCreacion(foroHub.getFecha_de_creacion()), foroHub.getAutor(), foroHub.getCurso()));
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarForoHub(@PathVariable Long id) {
        ForoHub foroHub = foroHubRepository.getReferenceById(id);
        foroHub.desactivarForoHub();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaForoHub> retornaDatosForoHub(@PathVariable Long id) {
        ForoHub foroHub = foroHubRepository.getReferenceById(id);
        var datosForoHub = new DatosRespuestaForoHub(foroHub.getId(), foroHub.getTitulo(), foroHub.getMensaje(),
                DatosListadoForoHub.formatFechaDeCreacion(foroHub.getFecha_de_creacion()), foroHub.getAutor(), foroHub.getCurso());
        return ResponseEntity.ok(datosForoHub);
    }

}
