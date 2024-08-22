/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import Logica.Cliente;

/**
 *
 * @author ivan
 */
public class ControladoraPersistencia {
    ClienteJpaController clijpa=new ClienteJpaController();

    public void AddCliente(Cliente cli) throws Exception {
       clijpa.create(cli);
    }

    public void guardar(Cliente cli) throws Exception {
        clijpa.create(cli);
    }
}
