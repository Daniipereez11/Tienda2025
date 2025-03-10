/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielpg04.educastur.es.tienda2025;

/**
 *
 * @author alu21d
 */
public class MetodosAux {
    public static boolean esInt(String opcion){
        try {
            Integer.parseInt(opcion);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
