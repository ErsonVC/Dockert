package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudianteController {

    private List<String> estudiantes = Arrays.asList("Estudiante A", "Estudiante B", "Estudiante C");

    @GetMapping("/estudiantes")
    public List<String> listarEstudiantes() {
        return estudiantes;
    }

    @GetMapping("/estudiantes/{id}")
    public String buscarEstudiante(@PathVariable int id) {
        return estudiantes.get(id);
    }
}
