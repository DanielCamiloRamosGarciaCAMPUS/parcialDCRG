package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Coordinador;
import com.example.demo.entity.Estudiante;

import com.example.demo.repositpry.EstudianteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "estudiante")
	
public class ControllerWebEstudiante {
	
	@Autowired
    private EstudianteRepository estudianteRepository;
	
	@GetMapping("/resultadoTotal")
    public String estudianteResultadoTotalTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Estudiante estudiante = (Estudiante) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (estudiante != null) {
            model.addAttribute("nombre", estudiante.getPrimerNombre());
            model.addAttribute("apellido", estudiante.getPrimerApellido());
            model.addAttribute("total", estudiante.getPuntaje());
            model.addAttribute("totalLevel", estudiante.getNivelSaberPro());
        }
        
        return "estudiante-resultado-total";
    }
	
	@GetMapping("/resultadoUnico")
    public String estudianteResultadoUnicoTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Estudiante estudiante = (Estudiante) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (estudiante != null) {
            model.addAttribute("nombre", estudiante.getPrimerNombre());
            model.addAttribute("apellido", estudiante.getPrimerApellido());
            model.addAttribute("comunicacion", estudiante.getComunicacionEscrita());
            model.addAttribute("comunicacionLevel", estudiante.getNivelComunicacionEscrita());
            model.addAttribute("razonamiento", estudiante.getRazonamientoCuantitativo());
            model.addAttribute("razonamientoLevel", estudiante.getNivelRazonamientoCuantitativo());
            model.addAttribute("lectura", estudiante.getLecturaCritica());
            model.addAttribute("lecturaLevel", estudiante.getNivelLecturaCritica());
            model.addAttribute("competencias", estudiante.getCompetenciasCiudadanas());
            model.addAttribute("competenciasLevel", estudiante.getNivelCompetenciasCiudadanas());
            model.addAttribute("ingles", estudiante.getIngles());
            model.addAttribute("inglesLevel", estudiante.getNivelIngles());
            model.addAttribute("levelIngles", estudiante.getNivelDeIngles());
            model.addAttribute("formulacion", estudiante.getFormulacionProyectosIngenieria());
            model.addAttribute("formulacionLevel", estudiante.getNivelFormulacionProyectosIngenieria());
            model.addAttribute("pensamiento", estudiante.getPensamientoCientifico());
            model.addAttribute("pensamientoLevel", estudiante.getNivelPensamientoCientifico());
            model.addAttribute("Diseno", estudiante.getDiseñoSoftware());
            model.addAttribute("DisenoLevel", estudiante.getNivelDiseñoSoftware());
        }
        
        return "estudiante-resultado";
    }
	
	@GetMapping("/index")
    public String estudianteIndexTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Estudiante estudiante = (Estudiante) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (estudiante != null) {
            model.addAttribute("nombre", estudiante.getPrimerNombre());
            model.addAttribute("apellido", estudiante.getPrimerApellido());
        }
        
        return "index-estudiante";
    }
	
	
	 @GetMapping("/ranking")
	    public String mostrarRanking(Model model) {
			model.addAttribute("estudiantes", estudianteRepository.findAll());
	        return "estudianteRanking"; 
	 }
	
	@PostMapping("/logear")
    public String estudianteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        // Buscar al coordinador por nombre de usuario en la base de datos
        Estudiante estudiante = null;
        for (Estudiante c : estudianteRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
            	estudiante = c;
                break;
            }
        }
        
        // Verificar si se encontró al coordinador y si la contraseña es correcta
        if (estudiante != null && estudiante.getContrasena().equals(contrasena)) {
            // Guardar el usuario logeado en la sesión
            session.setAttribute("usuarioLogeado", estudiante);
            // Si las credenciales son correctas, redirigir a la página de inicio
            return "redirect:/estudiante/index";
        } else {
            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
            model.addAttribute("error", true);
            return "login-estudiante";
        }
    }
	
	@GetMapping("/login")
	public String estudianteLoginTemplate(Model model) {
		return "login-estudiante";
	}
	
	
}
