/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *Exception error al elegir una opcion del menu
 * 
 */
public class OptionNotAvailable extends Exception {
        public OptionNotAvailable() {
        super("Opcion elegida no disponible");
    }

    public OptionNotAvailable(String message) {
        super(message);
    }

    public OptionNotAvailable(Exception e) {
        super(e);
    }
}
