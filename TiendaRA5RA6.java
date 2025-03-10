package danielpg04.educastur.es.tienda2025;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class TiendaRA5RA6 {
    
    Scanner sc=new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
   
    public TiendaRA5RA6() {
        pedidos = new ArrayList();
        articulos= new HashMap();
        clientes = new HashMap();
    }
    
    public static void main(String[] args) {
        TiendaRA5RA6 t=new TiendaRA5RA6();
        t.cargaDatos();
        
	t.examenPara7yMedio(); // METODO CON UNA SOLUCION PROPUESTA PARA OBTENER UNA PUNTUACIÓN DE HASTA 7,5 PUNTOS

        t.examenPara10();  // METODO CON UNA SOLUCION PROPUESTA PARA OBTENER UNA PUNTUACIÓN DE HASTA 10 PUNTOS
        
        t.mostrarClientesConTotal();
    }

    public void cargaDatos(){
       clientes.put("80580845T",new Cliente("80580845T","ANA ","658111111","ana@gmail.com"));
       clientes.put("36347775R",new Cliente("36347775R","ANTONIO","649222222","antonio@gmail.com"));
       clientes.put("63921307Y",new Cliente("63921307Y","AURORA","652333333","aurora@gmail.com"));
       clientes.put("53472775R",new Cliente("53472775R","EMILIO","649222222","emilio@gmail.com"));
       clientes.put("43211307Y",new Cliente("43211307Y","EVA","652333333","eva@gmail.com"));
       clientes.put("02337565Y",new Cliente("02337565Y","MATEO","634567890","mateo@gmail.com"));
       clientes.put("35445638M",new Cliente("35445638M","MARIA","633478990","maria@gmail.com"));
     
              
       articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
       articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
       articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
       articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
       articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
       articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
       articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
       articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
       articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
       
       LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("80580845T-001/2024",clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3)))));                                                                                                                                                               
       pedidos.add(new Pedido("80580845T-002/2024",clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4)))));
       pedidos.add(new Pedido("36347775R-001/2024",clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("36347775R-002/2024",clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("63921307Y-001/2024",clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2)))));
       pedidos.add(new Pedido("53472775R-001/2025",clientes.get("53472775R"),hoy, new ArrayList<>
        (List.of(new LineaPedido("1-11",2),new LineaPedido("2-11",2)))));
       pedidos.add(new Pedido("43211307Y-001/2025",clientes.get("43211307Y"),hoy, new ArrayList<>
        (List.of(new LineaPedido("4-33",1)))));
     } 
    
    public void examenPara7yMedio(){
        ArrayList<Cliente> clientesSin = new ArrayList();
        ArrayList<Cliente> clientesCon = new ArrayList();
               
        try(BufferedWriter bfwClientesCon=new BufferedWriter(new FileWriter("clientesCon.csv"));
            BufferedWriter bfwClientesSin=new BufferedWriter(new FileWriter("clientesSin.csv"))    ){
            
            for (Cliente c : clientes.values()) {
                /* ESTILO CLÁSICO 
                boolean tienePedido = false;
                for (Pedido p : pedidos) {
                    if (p.getClientePedido() == c) {
                        tienePedido = true;
                        break;
                    }
                } 
                if (tienePedido) {
                   bfwClientesCon.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                } else{
                   bfwClientesSin.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n"); clientesSin.add(c);
                }*/

                //CON STREAMS Y EL METODO anyMatch
                if (pedidos.stream().anyMatch(p-> p.getClientePedido().equals(c))){
                   bfwClientesCon.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                } else{
                   bfwClientesSin.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                }
            }
        }catch (FileNotFoundException e) {
                 System.out.println(e.toString());   
        }catch(IOException e){
            System.out.println(e.toString());
        }
 
        try(Scanner scClientesCon=new Scanner(new File("clientesCon.csv"))){
            while (scClientesCon.hasNextLine()){
                String [] atributos = scClientesCon.nextLine().split("[,]");                                                              
                Cliente c=new Cliente(atributos[0],atributos[1],atributos[2],atributos[3]); 
                clientesCon.add(c);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        
        try(Scanner scClientesSin=new Scanner(new File("clientesSin.csv"))){
            while (scClientesSin.hasNextLine()){
                String [] atributos = scClientesSin.nextLine().split("[,]");                                                              
                Cliente c=new Cliente(atributos[0],atributos[1],atributos[2],atributos[3]); 
                clientesSin.add(c);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        System.out.println("\nLISTADOS EXAMEN VERSIÓN 7,5 PUNTOS:");
        System.out.println("\nCLIENTES CON PEDIDOS:");
        clientesCon.forEach(System.out::println);
        System.out.println("\nCLIENTES SIN PEDIDOS:");
        clientesSin.forEach(System.out::println);
    }
    
    public void examenPara10(){
        ArrayList<Cliente> clientesSin = new ArrayList();
        ArrayList<Cliente> clientesCon = new ArrayList();
        ArrayList<Cliente> clientesMas1000 = new ArrayList();
               
        try(BufferedWriter bfwClientesCon=new BufferedWriter(new FileWriter("clientesCon.csv"));
            BufferedWriter bfwClientesSin=new BufferedWriter(new FileWriter("clientesSin.csv"));
            BufferedWriter bfwClientesMas1000=new BufferedWriter(new FileWriter("clientesMas1000.csv")))
        {
            
            for (Cliente c : clientes.values()){
                if (totalCliente(c)==0){
                    bfwClientesSin.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                }else if (totalCliente(c)>=1000){
                      bfwClientesCon.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                      bfwClientesMas1000.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                    }else {
                       bfwClientesCon.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                    }
            }
            
        }catch (FileNotFoundException e) {
                 System.out.println(e.toString());   
        }catch(IOException e){
            System.out.println(e.toString());
        }
 
        try(Scanner scClientesCon=new Scanner(new File("clientesCon.csv"))){
            while (scClientesCon.hasNextLine()){
                String [] atributos = scClientesCon.nextLine().split("[,]");                                                              
                Cliente c=new Cliente(atributos[0],atributos[1],atributos[2],atributos[3]); 
                clientesCon.add(c);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        
        try(Scanner scClientesSin=new Scanner(new File("clientesSin.csv"))){
            while (scClientesSin.hasNextLine()){
                String [] atributos = scClientesSin.nextLine().split("[,]");                                                              
                Cliente c=new Cliente(atributos[0],atributos[1],atributos[2],atributos[3]); 
                clientesSin.add(c);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        
        try(Scanner scClientesSin=new Scanner(new File("clientesMas1000.csv"))){
            while (scClientesSin.hasNextLine()){
                String [] atributos = scClientesSin.nextLine().split("[,]");                                                              
                Cliente c=new Cliente(atributos[0],atributos[1],atributos[2],atributos[3]); 
                clientesMas1000.add(c);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        System.out.println("\nLISTADOS EXAMEN VERSIÓN 10 PUNTOS:");
        System.out.println("\nCLIENTES CON PEDIDOS:");
        clientesCon.forEach(System.out::println);
        System.out.println("\nCLIENTES SIN PEDIDOS:");
        clientesSin.forEach(System.out::println);
        System.out.println("\nCLIENTES CON MAS DE 1000€ GASTADOS:");
        clientesMas1000.forEach(System.out::println);
    }
    
    public double totalCliente(Cliente c){
        double total=0;
        for(Pedido p:pedidos){
            if (p.getClientePedido().equals(c)){
                total+=totalPedido(p);
            }
        }
        return total;
    }
    public double totalPedido(Pedido p)
    {
        double total=0;
        for (LineaPedido l:p.getCestaCompra())
        {
            total+=(articulos.get(l.getIdArticulo()).getPvp())
                    *l.getUnidades();
        }
        return total;
    }    
    public void mostrarClientesConTotal() {
    System.out.println("\nLISTADO DE CLIENTES CON TOTAL DE PEDIDOS:");
    for (Cliente c : clientes.values()) {
        double totalGastado = totalCliente(c);
        System.out.println(c + " | Total gastado: " + totalGastado + "€");
        }
    }
}