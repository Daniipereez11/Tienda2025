/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielpg04.educastur.es.tienda2025;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author alu21d
 */
public class Tienda2025 {
    Scanner sc=new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
   
    public Tienda2025() {
        pedidos = new ArrayList(); 
        articulos= new HashMap();
        clientes = new HashMap();
    }
  
  
    
    public static void main(String[] args) {
        Tienda2025 t=new Tienda2025();
        
        t.cargaDatos();
        t.listaArticulos();
    }

    public void cargaDatos(){
       clientes.put("80580845T",new Cliente("80580845T","ANA ","658111111","ana@gmail.com"));
       clientes.put("36347775R",new Cliente("36347775R","LOLA","649222222","lola@gmail.com"));
       clientes.put("63921307Y",new Cliente("63921307Y","JUAN","652333333","juan@gmail.com"));
       clientes.put("02337565Y",new Cliente("02337565Y","EDU","634567890","edu@gmail.com"));
              
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
       
    }
    
    
    
    public void listarArticulosPorCategoria() {
        Map<String, List<Articulo>> categorias = new HashMap<>();
        categorias.put("Periféricos", new ArrayList<>());
        categorias.put("Almacenamiento", new ArrayList<>());
        categorias.put("Impresoras", new ArrayList<>());
        categorias.put("Monitores", new ArrayList<>());

        for (Articulo articulo : articulos.values()) {
            if (articulo.getIdArticulo().contains("RATON") || articulo.getIdArticulo().contains("TECLADO")) {
                categorias.get("Periféricos").add(articulo);
            } else if (articulo.getIdArticulo().contains("HDD") || articulo.getIdArticulo().contains("SSD")) {
                categorias.get("Almacenamiento").add(articulo);
            } else if (articulo.getIdArticulo().contains("EPSON") || articulo.getIdArticulo().contains("PRINT")) {
                categorias.get("Impresoras").add(articulo);
            } else if (articulo.getIdArticulo().contains("MONITOR") || articulo.getIdArticulo().contains("ODISSEY")) {
                categorias.get("Monitores").add(articulo);
            }
        }

        // Imprimir las categorías
        categorias.forEach((categoria, lista) -> {
            System.out.println("\n" + categoria.toUpperCase() + ":");
            lista.forEach(System.out::println);
        });
    }
    
    
    
    public void backupPorSeccion() {
        try (ObjectOutputStream oosPerifericos = new ObjectOutputStream(new FileOutputStream("Perifericos.dat"));
            ObjectOutputStream oosAlmacenamiento = new ObjectOutputStream(new FileOutputStream("Almacenamiento.dat"));
            ObjectOutputStream oosImpresoras = new ObjectOutputStream(new FileOutputStream("Impresoras.dat"));
            ObjectOutputStream oosMonitores = new ObjectOutputStream (new FileOutputStream("Monitores.dat"))) {
	   	   
            for (Articulo a : articulos.values()) {
                char seccion=a.getIdArticulo().charAt(0);
                switch (seccion) {
                    case '1':
                        oosPerifericos.writeObject(a);
                        break;
                    case '2':
                        oosAlmacenamiento .writeObject(a);
                        break;
                    case '3':
                        oosImpresoras.writeObject(a);
                        break;
                    case '4':
                        oosMonitores.writeObject(a);
                        break;
                }
            }
            System.out.println("Copia de seguridad realizada con éxito.");
	    
        } catch (FileNotFoundException e) {
                 System.out.println(e.toString());                                                          
        } catch (IOException e) {
                 System.out.println(e.toString());
        } 
    
    
    System.out.println("Teclea la Seccion de los articulos CUYO ARCHIVO QUIERES COMPROBAR:");        
        char seccion=sc.next().charAt(0);
        String nombreArchivo=null;
        switch (seccion) {
                    case '1':
                        nombreArchivo="Perifericos.dat";
                        break;
                    case '2':
                        nombreArchivo="Almacenamiento.dat";
                        break;
                    case '3':
                        nombreArchivo="Impresoras.dat";
                        break;
                    case '4':
                        nombreArchivo="Monitores.dat";
                        break;
        }
        Articulo a;
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream(nombreArchivo))){
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                System.out.println(a);
            } 
        } catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        }
    }
    

    public void leerArchivosSeccion() {
        System.out.println("Teclea la Seccion de los articulos que quieres recuperar:");        
        String id=sc.next();
        ArrayList<Articulo> articulosAux= new ArrayList();
        Articulo a;
        
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))){
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                if (id.equals("5")){
                    articulosAux.add(a);
                }else if (a.getIdArticulo().startsWith(id)){
                    articulosAux.add(a);
                }
            } 
        } catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        } 
        
        articulosAux.forEach(System.out::println);
    }
    
    
    
    
    public void backup() {
        try (ObjectOutputStream oosArticulos = new ObjectOutputStream(new FileOutputStream("articulos.dat"));
            ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
            ObjectOutputStream oosPedidos = new ObjectOutputStream (new FileOutputStream("pedidos.dat"))) {
	   	   
            //COLECCIONES COMPLETAS
            for (Articulo a:articulos.values()){
                oosArticulos.writeObject(a);
            }
           for (Cliente c:clientes.values()){
               oosClientes.writeObject(c);
           }
            //LOS PEDIDOS SE GUARDAN OBJETO A OBJETO    
            for (Pedido p:pedidos){
                 oosPedidos.writeObject(p);
            }
            
            System.out.println("Copia de seguridad realizada con éxito.");
	    
        } catch (FileNotFoundException e) {
                 System.out.println(e.toString());                                                          
        } catch (IOException e) {
                 System.out.println(e.toString());
        } 
    }  
    
    public void leerArchivos() {
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"));
             ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("clientes.dat"));
             ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))){
            
            articulos = (HashMap<String,Articulo>) oisArticulos.readObject();
            clientes = (HashMap<String,Cliente>) oisClientes.readObject();
            
            
            Articulo a;
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                 articulos.put(a.getIdArticulo(), a);
            } 
            
            Cliente c;
            while ( (c=(Cliente)oisClientes.readObject()) != null){
                 clientes.put(c.getDni(), c);
            } 
            
            Pedido p;
            while ( (p=(Pedido)oisPedidos.readObject()) != null){
                 pedidos.add(p);
            } 
            System.out.println("Colecciones importadas con éxito.");
            
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        } 
        
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
    public double totalPedido(Pedido p){
        double total=0;
        for (LineaPedido l:p.getCestaCompra())
        {
            total+=(articulos.get(l.getIdArticulo()).getPvp())
                    *l.getUnidades();
        }
        return total;
    }
    
    public void listaArticulos(){
        Scanner sc = new Scanner(System.in);
        String opcion;
        do{
            System.out.println("\n\n\n\n\n\nLISTAR ARTICULOS");
            System.out.println("\t\t\t\t\t0 - TODOS LOS ARTICULOS");
            System.out.println("\t\t\t\t\t1 - PERIFERICOS");
            System.out.println("\t\t\t\t\t2 - ALMACENAMIENTO");
            System.out.println("\t\t\t\t\t3 - IMPRESORAS");
            System.out.println("\t\t\t\t\t4 - MONITORES");
            System.out.println("\t\t\t\t\t5 - SALIR");
            do
                opcion=sc.next();
            while(!opcion.matches("[0 - 5]"));
            if(opcion.equals("5")){
                listados(opcion);
            }
        }while(!opcion.equals("5"));
    }
    
    public void listados(String seccion){
        if (seccion.equals("0")){
            articulos.values().stream().forEach(System.out::println);
        }else{
            articulos.values().stream().filter(a -> a.getIdArticulo().startsWith(seccion)).forEach(System.out::println);
        }
    }
}







