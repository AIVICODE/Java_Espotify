
package Persis;

import Logica.Cliente;

   


public class ControladoraPersistencia {
    ClienteJpaController clijpa=new ClienteJpaController();

    public void AddCliente(Cliente cli) throws Exception {
       clijpa.create(cli);
    }
}
