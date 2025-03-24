     /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielpg04.educastur.es.tienda2025;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido implements Comparable <Pedido> {
    
    private String idPedido;
    private danielpg04.educastur.es.tienda2025.Cliente clientePedido;
    private LocalDate fechaPedido;
    private ArrayList <LineaPedido>cestaCompra; 
   

    public Pedido(String idPedido, danielpg04.educastur.es.tienda2025.Cliente clientePedido, LocalDate fechaPedido, ArrayList <LineaPedido> cestaCompra) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fechaPedido = fechaPedido;
        this.cestaCompra = cestaCompra;
    }

    public String getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public danielpg04.educastur.es.tienda2025.Cliente getClientePedido() {
        return clientePedido;
    }
    public void setClientePedido(danielpg04.educastur.es.tienda2025.Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public ArrayList <LineaPedido> getCestaCompra() {
        return cestaCompra;
    }
    
   
    @Override
    public int compareTo(Pedido p) {
        return this.fechaPedido.compareTo(p.getFechaPedido());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Pedido: ").append(idPedido).append("\n");
        sb.append("Cliente: ").append(clientePedido.getNombre())
          .append(" (").append(clientePedido.getDni()).append(")\n");
        sb.append("Fecha: ").append(fechaPedido).append("\n");
        sb.append("Cesta de Compra:\n");
        for (LineaPedido lp : cestaCompra) {
            sb.append("   ").append(lp).append("\n");
        }
        return sb.toString();
    }
    
 }
    
    




