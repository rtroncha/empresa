package com.empresas.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaWebController {

    @Autowired
    private EmpresaRepository empresaRepository;

    // Mostrar lista de empresas
    @GetMapping
    public String listarEmpresas(Model model) {
        List<Empresa> empresas = empresaRepository.findAll();
        model.addAttribute("empresas", empresas);
        return "lista";
    }

    // Mostrar formulario para nueva empresa
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaEmpresa(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "formulario";
    }

    // Guardar empresa (nueva o editada)
    @PostMapping("/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa) {
        empresaRepository.save(empresa);
        return "redirect:/empresas";
    }

    // Mostrar formulario para editar empresa
    @GetMapping("/editar/{id}")
    public String editarEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            return "redirect:/empresas";
        }
        model.addAttribute("empresa", empresa);
        return "formulario";
    }

    // Confirmar eliminación
    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            return "redirect:/empresas";
        }
        model.addAttribute("empresa", empresa);
        return "eliminar";
    }

    // Ejecutar eliminación
    @PostMapping("/eliminar-confirmado/{id}")
    public String eliminarEmpresa(@PathVariable Long id) {
        empresaRepository.deleteById(id);
        return "redirect:/empresas";
    }
}
