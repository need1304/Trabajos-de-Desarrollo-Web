/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda_k.service;

import com.tienda_k.domain.Cliente;
import java.util.List;

public interface ClienteService {
    
    //se obtiene unlistado de clientes en un List
    public List<Cliente> getClientes();
    
    //se obtiene u clinete, a partir del id de un cliente
    public Cliente getCliente(Cliente cliente);
    
    //se inserta un  nuevo cliente si el id de cliente esta vacio
    // se actualiza un cliente si el id NO esta vacio
    public void save(Cliente cliente);
    
    // se elimina el cliente que tiene el id pasado por parametro
    public void delete(Cliente cliente);
}
