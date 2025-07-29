package com.empresas.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes-lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes-formulario";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "clientes-formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "clientes-eliminar";
    }

    @PostMapping("/eliminar-confirmado/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}

