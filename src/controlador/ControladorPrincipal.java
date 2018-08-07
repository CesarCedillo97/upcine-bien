/*
    Clase semiabastracta que se implementa en todas los controladores,
    la utilizamos para hacer que todos los controladores implementaran la clase iniciar vista
    ademas de ser de mucha utilidad las funciones pues es más facil acceder a ellas 
 */
package controlador;

import java.awt.Color;
import javax.swing.JPanel;
/**
 *
 * @author adria
 */
public abstract class ControladorPrincipal 
{
    /**
     * es para inicializar la vista de las pantallas
     */
    public abstract void iniciarVista();//para inciar la vista
    
    
    //TODO ESTO ES PARA DARLE COLOR A LOS PANELES CUANDO PASAS SOBRE ELLOS:
    public void setColor(JPanel panel){
        panel.setBackground(new java.awt.Color(64, 43, 100));
    }
    
    public void resetColor(JPanel panel){
        panel.setBackground(new java.awt.Color(85,65,118));
    }
    
    public void resetColorSalir(JPanel panel){
        panel.setBackground(new java.awt.Color(25,116,232));
    }
    
    public void resetColorLimpiar(JPanel panel){
        panel.setBackground(new java.awt.Color(106,100,100));
    }
    
    public void resetColorEditar(JPanel panel){
        panel.setBackground(new java.awt.Color(161,143,30));
    }
    
    public void resetColorEliminar(JPanel panel){
        panel.setBackground(new java.awt.Color(215,24,24));
    }
    
    public void resetColorAdd(JPanel panel){
        panel.setBackground(new java.awt.Color(35,8,79));
    }
    
    public void setColorDisabled(JPanel panel){
        panel.setBackground(new java.awt.Color(25,116,232));
    }
    
    public void setColorAceptar(JPanel panel){
        panel.setBackground(new Color(149,208,151));
    }
    
    public void setColorCancelar(JPanel panel){
        panel.setBackground(new Color(247,143,143));
    }
    
    public void setColorAdd(JPanel panel){
        panel.setBackground(new Color(90, 65, 131));
    }
    
    public void setColorLimpiar(JPanel panel){
        panel.setBackground(new Color(57, 244, 214));
    }
    
    public void setColorEliminar(JPanel panel){
        panel.setBackground(new Color(217, 121, 121));
    }
    
    public void setColorEditar(JPanel panel){
        panel.setBackground(new Color(217, 198, 78));
    }
    
    public void resetColorGrey(JPanel panel){
        panel.setBackground(new java.awt.Color(240,240,240));
    }
    
    public void setColorSuccess(JPanel panel){
        panel.setBackground(new Color(100,247,14));
    }
    /**
     * Checa que un array no contenga valores vaciós
     * @param array
     * @return <ul>Retorna:</ul>
     *         <li>True: Cuando todos tienen valor</li>
     *         <li>False: Cuando hay algun valor nulo</li>
     */
    public boolean checkIsNotEmpty(String[] array){
        for (String array1 : array) {
            if("".equals(array1)){
                return false;
            }
        }
        return true;
    }
    //función para validar numeros
    //cuando recibe un 1 se puede ingresar un punto, 0 cuando no
    public boolean validacionNum(String txt, long opcion){
        if(txt.indexOf('.') < 0){
            try{
                Long.parseLong(txt);
                return true;
            }
            catch(NumberFormatException e){
                return false;
            }
        }
        else if(opcion == 1){
            String[] txts = txt.split(".");
            try{
                System.out.println(txts[0]);
                System.out.println(txts[1]);
                Long.parseLong(txts[0]);
                Long.parseLong(txts[1]);
                return true;
            }
            catch(NumberFormatException e){
                return false;
            }
        }
        return false;
    }
}
