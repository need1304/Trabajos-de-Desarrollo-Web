/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda_k.controller;



import com.tienda_k.domain.Cliente;
import com.tienda_k.services.ClienteService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j

public class ClienteController {
    

    @Autowired 
    private ClienteService clienteService;
    @GetMapping("/")
    public String inicio(Model model){
        
        var clientes = clienteService.getClientes();
        
   
        model.addAttribute("clientes", clientes);
        return "index";  
   
    }
    
}
