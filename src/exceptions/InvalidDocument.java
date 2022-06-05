/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *Exception del DNI de 8 digitos numericos
 * 
 */
public class InvalidDocument extends Exception {

    public InvalidDocument() {
        super("DNI invalido, verifique su ingreso");
    }

    public InvalidDocument(String message) {
        super(message);
    }

    public InvalidDocument(Exception e) {
        super(e);
    }

}
