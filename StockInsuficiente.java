/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielpg04.educastur.es.tienda2025;

/**
 *
 * @author alu21d
 */
public class StockInsuficiente extends Exception {
    public StockInsuficiente(String cadena){                                                                        
                 super(cadena); //Llama al constructor de Exception y le pasa el contenido de cadena              
    }
}


