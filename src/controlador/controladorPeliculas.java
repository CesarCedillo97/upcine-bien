/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.IF_peliculas;
import modelo.modeloPelicuas;

/**
 *
 * @author Cesar Cedillo
 */
public class controladorPeliculas {
    IF_peliculas vista = new IF_peliculas();
    modeloPelicuas modelo = new modeloPelicuas();

    public controladorPeliculas(IF_peliculas vista, modeloPelicuas modelo) {
    this.modelo = modelo;
    this.vista= vista;
    }
    
    
}
