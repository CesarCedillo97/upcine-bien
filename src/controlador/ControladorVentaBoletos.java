/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vista.VentaBoletos;
import vista.alerts.alertMessage;
import vista.alerts.alertAccept;
import vista.alerts.alertError;
import vista.alerts.alertSuccess;
import vista.EmpSelectAsientos;
import modelo.modeloVentaBoletos;
import controlador.conAlerts.controladorMessage;
import controlador.conAlerts.controladorError;
import controlador.conAlerts.controladorAceptar;
import controlador.conAlerts.controladorSucces;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import modelo.modeloLogin;
import vista.EmpConfirmVenta;
import vista.Login;

/**
 * @author Adrián Scott
 */
public class ControladorVentaBoletos extends ControladorPrincipal implements ChangeListener, ActionListener, KeyListener, MouseListener{
    VentaBoletos vista;
    alertMessage vistaMess = new alertMessage();
    alertError vistaError = new alertError();
    alertAccept vistaAccept = new alertAccept();
    alertSuccess vistaSucces = new alertSuccess();
    EmpConfirmVenta confirmVenta = new EmpConfirmVenta();
    EmpSelectAsientos visAsientos;
    controladorMessage cMess;
    controladorError cError;
    controladorSucces cSuccess;
    controladorAceptar cAceptar;
    modeloVentaBoletos modelo;
    float subTotal;
    float ivaCuenta;
    float total;
    float IVA;
    int selectedMovie;
    int idSala;
    int idFuncion;
    int idEmpleado;
    int idCliente;
    String selectedDate;
    String selectedHour;
    JSpinner[] arraySpinners;
    String[][] tiposBoletos;
    String[][] funciones;
    JLabel[] pelis;
    JPanel[] panelesPelis;
    String[] datosCliente;
    
    public ControladorVentaBoletos(VentaBoletos vista, modeloVentaBoletos modelo, int idEmpleado) {
        this.vista = vista;
        this.modelo = modelo;
        this.idEmpleado = idEmpleado;
    }
    
    @Override
    public void iniciarVista() {
        vista.setLocationRelativeTo(null);
        tiposBoletos = modelo.obtenerTiposBoletos();
        funciones = modelo.obtenerPeliculas();
        IVA = modelo.obtenerIVA();
        int numTipos = tiposBoletos.length;
        arraySpinners = new JSpinner[numTipos];
        anadirTiposBoletos(vista.panelBoletos, numTipos);
        anadirPeliculas();
        vista.btnAceptClient.addActionListener(this);
        vista.comboFechas.addActionListener(this);
        vista.comboHorario.addActionListener(this);
        vista.chckPuntos.addActionListener(this);
        vista.panelSig.addMouseListener(this);
        vista.panelBack.addMouseListener(this);
        vista.panelSalir.addMouseListener(this);
        vista.txtCliente.addKeyListener(this);
        vista.setVisible(true);
        
    }
    /**
     * Obtiene los tipos de boletos que hay desde la base de datos y los muestra en el panel
     * @param panelBoletos Panel donde se mostrarán los Spinners
     * @param numTipos
     */
    public void anadirTiposBoletos(JPanel panelBoletos, int numTipos){
        for (int i = 0; i < numTipos; i++) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(new Color(255,255,153));
            label.setText(tiposBoletos[i][0]);
            label.setFont(new Font("Tahoma", Font.PLAIN, 22));
            panel.add(label);
            JSpinner spinner;
            panel.add(spinner=crearSpinner(i));
            arraySpinners[i]=spinner;
            arraySpinners[i].addChangeListener((ChangeListener) this);
            panelBoletos.add(panel);
        }
    }
    
    public void anadirPeliculas(){
        int[] pelisYaAgregadas = new int[funciones.length]; //Variable para que salga solo salga una vez la pelicula en ves de todas las funciones 
        boolean yaAgregada = false;
        int counter=0;
        pelis = new JLabel[funciones.length];
        panelesPelis = new JPanel[funciones.length];
        for (String[] funcion : funciones) {
            for (int j = 0; j < pelisYaAgregadas.length && pelisYaAgregadas[j] != 0; j++) {
                if (pelisYaAgregadas[j] == Integer.parseInt(funcion[0])) {
                    yaAgregada = true;
                }
            }
            if (!yaAgregada) {
                pelisYaAgregadas[counter] = Integer.parseInt(funcion[0]);
                JPanel peli = new JPanel(new GridLayout(0, 2));
                peli.setSize(vista.panelPeliculas.getSize().width, 200);
                peli.setAlignmentY(Component.CENTER_ALIGNMENT);
                JLabel lbl2 = new JLabel(funcion[1] + " " + funcion[4] + " (" + funcion[2] + ") Sub: " + funcion[3]);
                lbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
                pelis[counter] = lbl2;
                panelesPelis[counter] = peli;
                panelesPelis[counter].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        for (int j = 0; panelesPelis[j] != null; j++) {
                            panelesPelis[j].setBackground(new Color(240,240,240));
                            vista.txtPelicula.setText(funcion[1]);
                            //vista.numAsientos.setText(modelo.getNumAsientos(j));
                            selectedMovie = Integer.parseInt(funcion[0]);
                            //llenarComboHoras(funcion[0]);
                            llenarComboFecha(funcion[0]);
                        }
                        peli.setBackground(new Color(220,220,220));
                    }
                });
                peli.add(new JLabel(new ImageIcon(getClass().getResource("/assets/imgsPelis/" + funcion[6]))));
                peli.add(lbl2);
                peli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                vista.panelPeliculas.add(peli);
                counter++;
            } else {
                yaAgregada = false;
            }
        }
    }
    /**
     * Obtiene los horariso de la pelicula seleccionada
     */
    public void llenarComboHoras(){
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        String fecha = String.valueOf(vista.comboFechas.getSelectedItem());
        String[] arrayFecha = fecha.split(" ");
        String dia = arrayFecha[0];
        String mes = "0"+numMes(arrayFecha[2]);
        String año = ""+arrayFecha[4];
        fecha = año+"-"+mes+"-"+dia;
        selectedDate = fecha;
        String txtHora;
        Date fechaIni = null;
        Date fechaFin = null;
        Date fechaComp = null;
        for (String[] funcion : funciones) {
            if(Integer.parseInt(funcion[0]) == selectedMovie){
                try {
                fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[7]);
                fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[8]);
                fechaComp = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorVentaBoletos.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (fechaIni.compareTo(fechaComp) <= 0 && fechaFin.compareTo(fechaComp) >= 0) {
                    String[] hora = funcion[5].split(":");
                    if(Integer.parseInt(hora[0]) > 12){
                        txtHora = Integer.parseInt(hora[0])-12+":"+hora[1]+" PM";
                    }
                    else{
                        txtHora = hora[0]+":"+hora[1]+" AM";
                    }
                    cmbModel.addElement(txtHora);
                }
            }
        }
        vista.comboHorario.setModel(cmbModel);
    }
    
    public void llenarComboFecha(String idPeli){
        DefaultComboBoxModel cmbModelFechas = new DefaultComboBoxModel(); 
        Date fechaIni;
        Date fechaFin;
        Calendar fI = Calendar.getInstance();
        Calendar fF = Calendar.getInstance();
        
        for (String[] funcion : funciones) {
            if(funcion[0].equals(idPeli)){
                try { 
                    fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[7]);
                    fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[8]);
                    fI.setTime(fechaIni);
                    fF.setTime(fechaFin);
                    
                } catch (ParseException ex) {
                    System.out.println("Ha ocurrido un error con la fecha");
                }
                
                while (fI.getTimeInMillis() <= fF.getTimeInMillis()){
                    boolean yaEsta = false;
                    String txtFecha = fI.get(Calendar.DATE)+" de "+nombreMes(fI.get(Calendar.MONTH))+" del "+fI.get(Calendar.YEAR);
                    
                    for (int i = 0; i < cmbModelFechas.getSize(); i++) {
                        if(txtFecha.equals(cmbModelFechas.getElementAt(i))) yaEsta = true;
                    }
                    
                    if(!yaEsta)
                        cmbModelFechas.addElement(txtFecha);
                    fI.add(Calendar.DATE, 1);
                }
            }
        }
        vista.comboFechas.setModel(cmbModelFechas);
        llenarComboHoras();
        this.seleccionHorario();
    }
    
    public String nombreMes(int numMes){
        switch(numMes){
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Novimebre";
            default: return "Diciembre";
        }
    }
    
    public int numMes(String nombreMes){
        switch(nombreMes){
            case "Enero": return 1;
            case "Febrero": return 2;
            case "Marzo": return 3;
            case "Abril": return 4;
            case "Mayo": return 5;
            case "Junio": return 6;
            case "Julio": return 7;
            case "Agosto": return 8;
            case "Septiembre": return 9;
            case "Octubre": return 10;
            case "Noviembre": return 11;
            default: return 12;
        }
    }
    
    public JSpinner crearSpinner(int indexBoleto){
        SpinnerNumberModel m = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinner = new JSpinner(m) {
            @Override public void setLayout(LayoutManager mgr) {
              super.setLayout(new SpinnerLayout());
            }
        };
        JFormattedTextField ftf = getTextField(spinner);
        if(ftf != null){
            ftf.setFont(new Font("Tagoma", Font.PLAIN, 20));
            ftf.setHorizontalAlignment(JTextField.CENTER);
        }
        Dimension d = spinner.getComponent(0).getPreferredSize();
        d.height = 100;
        spinner.getComponent(0).setPreferredSize(d);
        spinner.addChangeListener((ChangeEvent e) -> {
        });
        
        return spinner;
    }
    
    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();

        } else {
            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        subTotal=0;
        for (int i = 0; i < arraySpinners.length; i++) {
            subTotal += Integer.parseInt(String.valueOf(arraySpinners[i].getValue())) * Integer.parseInt(this.tiposBoletos[i][1]);
        }
        calcularTotal();
    }

    public void calcularTotal(){
        ivaCuenta = subTotal * IVA;
        total =  subTotal + ivaCuenta;
        vista.txtSubtotal1.setText(String.format(Locale.US,"%.2f",subTotal));
        vista.txtIva.setText(String.format(Locale.US,"%.2f",ivaCuenta));
        vista.txtTotal.setText(String.format(Locale.US,"%.2f",total));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.comboFechas){
            llenarComboHoras();
            seleccionHorario();
        }
        else if(e.getSource() == vista.comboHorario){
            seleccionHorario();
        }
        else if(e.getSource() == vista.btnAceptClient){
            if(!"".equals(vista.txtCliente.getText())){
                datosCliente = modelo.obtenerCliente(Integer.parseInt(vista.txtCliente.getText()));
                if(datosCliente != null){
                    vista.txtNombre.setText(datosCliente[1]);
                    vista.txtPuntos.setText(datosCliente[2]);
                    vista.chckPuntos.setEnabled(true);
                    idCliente = Integer.parseInt(vista.txtCliente.getText());
                }
                else{
                    cMess = new controladorMessage(vistaMess, "No se encontró el cliente");
                    cMess.iniciarVista();
                    float puntos = Float.parseFloat(vista.txtPuntos.getText());
                    vista.txtCliente.setText("");
                    vista.txtNombre.setText("");
                    vista.txtPuntos.setText("");
                    vista.chckPuntos.setEnabled(false);
                    if(vista.chckPuntos.isSelected()){
                        vista.chckPuntos.setSelected(false);
                        subTotal= subTotal+puntos;
                        calcularTotal();
                    }
                }
            }
            else{
                cMess = new controladorMessage(vistaMess, "Introduce el ID del cliente");
                float puntos = "".equals(vista.txtPuntos.getText())?0f:Float.parseFloat(vista.txtPuntos.getText());
                vista.txtCliente.setText("");
                vista.txtNombre.setText("");
                vista.txtPuntos.setText("");
                vista.chckPuntos.setEnabled(false);
                if(vista.chckPuntos.isSelected()){
                    vista.chckPuntos.setSelected(false);
                    subTotal= subTotal+puntos;
                    calcularTotal();
                }
                cMess.iniciarVista();
            }
        }
        else if(e.getSource() == vista.chckPuntos){
            float puntos = Float.parseFloat(datosCliente[2]);
            if(vista.chckPuntos.isSelected()){
                subTotal= subTotal-puntos;
                calcularTotal();
            }
            else{
                subTotal= subTotal+puntos;
                calcularTotal();
            }
        }
        
    }
    public void seleccionHorario(){
        Calendar horaCal = Calendar.getInstance();//para poder agregar las horas que se le habian quitado si es en la tarde
        Date fechaIni = null;
        Date fechaFin = null;
        Date fechaComp = null;
        Date horaIni = null;
        Date horaComp = null;
        for (String[] funcion : funciones) {
            try {
                fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[7]);
                fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(funcion[8]);
                fechaComp = new SimpleDateFormat("yyyy-MM-dd").parse(selectedDate);
                horaIni = new SimpleDateFormat("HH:mm").parse(funcion[5]);
                horaComp= new SimpleDateFormat("HH:mm").parse(String.valueOf(vista.comboHorario.getSelectedItem()));
                String[] meridiano = String.valueOf(vista.comboHorario.getSelectedItem()).split(" ");
                horaCal.setTime(horaComp);
                System.out.println(meridiano[1]);
                if(meridiano[1].equals("PM")){
                    horaCal.add(Calendar.HOUR_OF_DAY,12);
                }
                selectedHour = String.valueOf(horaCal);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorVentaBoletos.class.getName()).log(Level.SEVERE, null, ex);
            }
//                System.out.println(funcion[0]+" == "+selectedMovie+" : " + (Integer.parseInt(funcion[0]) == selectedMovie));
//                System.out.println(fechaComp+ " >= " +fechaIni+" : "+ (fechaComp.compareTo(fechaIni) >= 0));
//                System.out.println(fechaComp+ " <= " +fechaFin+ ": "+ (fechaComp.compareTo(fechaFin) <= 0));
//                System.out.println(horaIni+ " == " +horaCal.getTime()+ " : "+ (horaIni.compareTo(horaCal.getTime()) == 0));
//                System.out.println("");
            if(Integer.parseInt(funcion[0]) == selectedMovie && 
               fechaComp.compareTo(fechaIni) >= 0 && 
               fechaComp.compareTo(fechaFin) <= 0 && 
               horaIni.compareTo(horaCal.getTime()) == 0){
                System.out.println("SALA: "+funcion[9]);
                idFuncion = Integer.parseInt(funcion[10]);
                idSala=Integer.parseInt(funcion[9]);
                vista.numAsientos.setText(""+modelo.getNumAsientos(Integer.parseInt(funcion[9])));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.txtCliente && !"".equals(vista.txtCliente.getText())){
            validacionTexFields(vista.txtCliente, "[0-9]+");    
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == vista.panelSig && !"".equals(vista.txtPelicula.getText())){
            int asientosComprados=0;
            for (JSpinner arraySpinner : arraySpinners) {
                asientosComprados += Integer.parseInt(String.valueOf(arraySpinner.getValue()));
            }
            if(asientosComprados > Integer.parseInt(vista.numAsientos.getText())){
                cError = new controladorError(vistaError, "Ya no hay tanta disponibilidad de asientos");
                cError.iniciarVista();
            }
            else if(asientosComprados == 0){
                cMess = new controladorMessage(vistaMess, "No se ha seleccionado el numero de asientos");
                cMess.iniciarVista();
            }
            else{
                vista.setEnabled(false);
                visAsientos = new EmpSelectAsientos();
                visAsientos.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e) {
                        vista.setEnabled(true);
                        visAsientos.dispose();
                    }
                });
                SeleccionAsientos selAsientos = new SeleccionAsientos(idSala, asientosComprados);
                selAsientos.iniciarSeleecionAsientos();
            }
        }
        else if(e.getSource() == vista.panelSig && "".equals(vista.txtPelicula.getText())){
            cMess = new controladorMessage(vistaMess, "No se ha seleccionado ninguna pelicula");
            cMess.iniciarVista();
        }
        else if(e.getSource() == vista.panelBack){
            vista.dispose();
            VentaBoletos vBol = new VentaBoletos();
            modeloVentaBoletos mVBol = new modeloVentaBoletos();
            ControladorVentaBoletos conVenBol = new ControladorVentaBoletos(vBol,mVBol, idEmpleado);
            conVenBol.iniciarVista();
        }
        else if(e.getSource() == vista.panelSalir){
            vista.dispose();
            Login vista2 = new Login();
            modeloLogin modelo2 = new modeloLogin();
            controladorLogin control = new controladorLogin(modelo2, vista2);
            control.iniciarVista();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == vista.panelSig){
            setColorAceptar(vista.panelSig);
        }
        else if(e.getSource() == vista.panelBack){
            setColorCancelar(vista.panelBack);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == vista.panelSig){
            resetColorGrey(vista.panelSig);
        }
        else if(e.getSource() == vista.panelBack){
            resetColorGrey(vista.panelBack);
        }
    }
    
    public class SeleccionAsientos implements MouseListener{
        int[][] asientos;
        private JLabel[][] arregloAsientos;
        private int filas, columnas, numAsientos;
        private int sala;
        private int countAsientos; 

        public SeleccionAsientos(int sala, int numAsientos) {
            this.sala = sala;
            this.numAsientos = numAsientos;
        }
        
        public void iniciarSeleecionAsientos(){
            asientos = modelo.obtenerAsientos(sala);
            this.filas = asientos.length;
            this.columnas = asientos[0].length;
            System.out.println("filas: "+filas);
            System.out.println("Col: "+columnas);
            visAsientos.panelAsientos.setLayout(new GridLayout(asientos.length,asientos[0].length));
            visAsientos.panelAdd2.addMouseListener(this);
            visAsientos.panelBack2.addMouseListener(this);
            arregloAsientos = new JLabel[filas][columnas];
            //imprime los asientos 
            for (int i = asientos.length -1; i >= 0; i--)
            {
                for (int j = 0; j < asientos[0].length; j++)
                {
                    switch (asientos[i][j])
                    {
                        case 1:
                            arregloAsientos[i][j] = new JLabel(getLetra(i+1)+"-"+j,new ImageIcon(getClass().getResource("/assets/icons/asientoAzul.png")),0);
                            break;
                        case 0:
                            arregloAsientos[i][j] = new JLabel(getLetra(i+1)+"-"+j,new ImageIcon(getClass().getResource("/assets/icons/asientoRojo.png")),0);
                            break;
                        case -1:
                            arregloAsientos[i][j] = new JLabel("");
                            break;
                    }
                    visAsientos.panelAsientos.add(arregloAsientos[i][j]);
                    arregloAsientos[i][j].addMouseListener(new java.awt.event.MouseAdapter(){
                        @Override
                        public void mousePressed(java.awt.event.MouseEvent evt){
                            asientosMousePressed(evt);
                        }
                    });
                }
            }
            visAsientos.setLocationRelativeTo(null);
            visAsientos.setVisible(true);
        }
        
        private String getLetra(int i) {
            return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
        }
        public void asientosMousePressed(java.awt.event.MouseEvent e){
            for (int i = 0; i < filas; i++)
            {
                for (int j = 0; j < columnas; j++)
                {
                    if(arregloAsientos[i][j] == e.getSource()){
                        if(asientos[i][j] == 0 && countAsientos < numAsientos){
                            arregloAsientos[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/icons/asientoVerde.png")));
                            asientos[i][j] = 2;
                            countAsientos++;
                        }
                        else if(asientos[i][j] == 2){
                            arregloAsientos[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/icons/asientoRojo.png")));
                            asientos[i][j] = 0;
                            countAsientos--;
                        }
                    }
                }
            }
        }
        
        public void realizarInsercion(){
            int[][] selectedSeats = new int[countAsientos][2];
            int counter = 0;
            //obtiene los asientos que fueron
            for (int i = filas-1; i >= 0; i--)
            {
                for (int j = 0; j < columnas; j++)
                {
                    if(asientos[i][j] == 2){
                        selectedSeats[counter][0] = i;
                        selectedSeats[counter][1] = j;
                        counter++;
                    }
                }
            }
            if(modelo.insertarVenta2(confirmVenta.txtSubtotal.getText(),confirmVenta.txtIva.getText(), confirmVenta.txtTotal.getText(), idEmpleado,idCliente,selectedSeats, sala, idFuncion)){
                if(vista.chckPuntos.isSelected()){
                    modelo.modificarPuntos(String.valueOf(idCliente),"0");
                }
                cSuccess = new controladorSucces(vistaSucces, "Se ha realizado correctamente la venta");
                vistaSucces.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        confirmVenta.dispose();
                        visAsientos.dispose();
                        vista.dispose();
                        VentaBoletos vBol = new VentaBoletos();
                        modeloVentaBoletos mVBol = new modeloVentaBoletos();
                        ControladorVentaBoletos conVenBol = new ControladorVentaBoletos(vBol,mVBol, idEmpleado);
                        conVenBol.iniciarVista();
                    }
                });
                cSuccess.iniciarVista();
            }
            else{
                cError = new controladorError(vistaError, "No se pudo concretar la venta");
                cError.iniciarVista();
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == visAsientos.panelAdd2){
                if(countAsientos == numAsientos){
                    confirmVenta.panelAdd1.addMouseListener(this);
                    confirmVenta.panelBack.addMouseListener(this);
                    confirmVenta.txtSubtotal.setText(vista.txtSubtotal1.getText());
                    confirmVenta.txtIva.setText(vista.txtIva.getText());
                    confirmVenta.txtTotal.setText(vista.txtTotal.getText());
                    confirmVenta.txtPeli.setText(vista.txtPelicula.getText());
                    String boletos="";
                    for (int i = 0; i < tiposBoletos.length; i++) {
                        if(!"".equals(arraySpinners[i].getValue()))
                            boletos +=tiposBoletos[i][0]+": "+arraySpinners[i].getValue()+" ";
                    }
                    confirmVenta.txtBoletos.setText(boletos);
                    confirmVenta.setLocationRelativeTo(null);
                    confirmVenta.setVisible(true);
                }
                else{
                    cError = new controladorError(vistaError, "Seleccione todos los asientos para continuar");
                    cError.iniciarVista();
                }
            }
            else if(e.getSource() == visAsientos.panelBack2){
                vista.setEnabled(true);
                vista.toFront();
                visAsientos.dispose();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(confirmVenta.panelBack == e.getSource()){
                confirmVenta.dispose();
            }
            else if(confirmVenta.panelAdd1 == e.getSource()){
                realizarInsercion();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == visAsientos.panelAdd2){
                setColor(visAsientos.panelAdd2);
            }
            else if(e.getSource() == visAsientos.panelBack2){
                setColor(visAsientos.panelBack2);
            }
            else if(confirmVenta.panelAdd1 == e.getSource()){
            setColorAceptar(confirmVenta.panelAdd1);
            }
            else if(confirmVenta.panelBack == e.getSource()){
                setColorCancelar(confirmVenta.panelBack);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == visAsientos.panelAdd2){
                resetColorGrey(visAsientos.panelAdd2);
            }
            else if(e.getSource() == visAsientos.panelBack2){
                resetColorGrey(visAsientos.panelBack2);
            }
            else if(confirmVenta.panelAdd1 == e.getSource()){
                resetColorGrey(confirmVenta.panelAdd1);
            }
            else if(confirmVenta.panelBack == e.getSource()){
                resetColorGrey(confirmVenta.panelBack);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
    }
}
