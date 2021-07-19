import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;


public class GUI_Cajero extends JFrame {
    //---- Atributo de la aplicaci�n -----------------------------------------------
    
    private CuentaBancaria cuenta;
    
    // Atributos de la GUI
    private Container contenedor;
    private JPanel panelAcciones, panelSaldo;
    private BotonCuentaBancaria bConsultar,bExtraer, bDepositar;

    // Constructor de la clase
    public GUI_Cajero(CuentaBancaria cb) {
        super("Cuenta  #"+ cb.obtenerCodigo());
        cuenta = cb;     
        setSize(300, 175);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGUI();
    }
    
    // Inicializa todos los componentes gr�ficos de la GUI
    private void initGUI() {
    	
    	
    	
        //---- Contenedor y paneles -----------------------------------------------
        contenedor = getContentPane();
        panelAcciones = new JPanel();
        panelSaldo = new JPanel();
        
        //---- Botones -----------------------------------------------
        bConsultar = new BotonCuentaBancaria("Consultar");
        bExtraer = new BotonCuentaBancaria("Extraer");
        bDepositar = new BotonCuentaBancaria("Depositar");
        
        //---- Oyentes -----------------------------------------------
        ListenerDepositar depositar = new ListenerDepositar();
        ListenerExtraer extraer = new ListenerExtraer();
        ListenerConsultar consultar = new ListenerConsultar();
        
        bDepositar.addActionListener(depositar);
        bExtraer.addActionListener(extraer);
        bConsultar.addActionListener(consultar);
        
        
        //---- Layout y paneles -----------------------------------------------
        contenedor.setLayout(new BorderLayout());
        panelAcciones.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        panelAcciones.setPreferredSize(new Dimension(140, 60));
        panelSaldo.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        panelSaldo.setPreferredSize(new Dimension(140, 60));
        
        panelAcciones.add(bDepositar);
        panelAcciones.add(bExtraer);
        panelSaldo.add(bConsultar);
        
        contenedor.add(panelAcciones, BorderLayout.NORTH);
        contenedor.add(panelSaldo, BorderLayout.SOUTH);

    }
    
    
    //---- Clases Oyentes -----------------------------------------------
    private class ListenerDepositar implements ActionListener{ 
            public void actionPerformed(ActionEvent e){
                float monto = obtenerEntrada("Ingrese la cantidad a depositar", "Dep�sito", JOptionPane.PLAIN_MESSAGE); //Solicito monto a depositar
                if(monto > 0){
                    cuenta.depositar(monto); //Deposito el monto en la cuenta
                    mostrarMensaje("Usted deposit� $"+monto, "Comprobante Dep�sito", JOptionPane.INFORMATION_MESSAGE); //Muestro el mensaje
                }
                
            }
    }
    
    private class ListenerExtraer implements ActionListener{
            public void actionPerformed(ActionEvent e){
                float monto = obtenerEntrada("Ingrese la cantidad a extraer", "Extracci�n", JOptionPane.PLAIN_MESSAGE); //Solicito monto a extraer  
                if(monto > 0){
                    if(cuenta.puedeExtraer(monto)){
                        cuenta.extraer(monto); //Extraigo el monto de la cuenta
                        mostrarMensaje("Usted extrajo $"+monto, "Comprobante Extracci�n", JOptionPane.INFORMATION_MESSAGE); //Muestro el mensaje                   
                    }
                    else{
                        mostrarMensaje("Usted no puede extraer esa cantidad.", "Error Extracci�n",JOptionPane.ERROR_MESSAGE); //Muestro mensaje de error
                    }
                }
            }
    }
    
    private class ListenerConsultar implements ActionListener{
            public void actionPerformed(ActionEvent e){
                float saldo = cuenta.obtenerSaldo();
                if(saldo >= 0){
                    mostrarMensaje("Usted tiene un saldo de $"+saldo, "Comprobante Saldo", JOptionPane.INFORMATION_MESSAGE); //Muestro el mensaje   
                }
                else{
                    mostrarMensaje("Usted est� en descubierto $"+saldo, "Comprobante Saldo", JOptionPane.WARNING_MESSAGE); //Muestro el mensaje   
                }
            }
    }

    //---- M�todos auxiliares -----------------------------------------------
    
    // Muestra un mensaje con el texto y titulo indicado por pantalla, haciendo uso de un InputDialog.
    // Obtiene el valor ingresado por el usuario, lo convierte a float y retorna.
    // tipoDeMensaje indica el formato del mensaje a mostrar. Requiere tipoDeMensaje v�lido.
    // Si el usuario no ingresa un valor, retorna 0.
    private float obtenerEntrada(String texto, String titulo, int tipoDeMensaje){
        JOptionPane dialogo;
        String entrada;
        float retorno;
        
        dialogo = new JOptionPane();
        entrada = dialogo.showInputDialog(null, texto, titulo, tipoDeMensaje);
        
        // Se valida que se haya ingresado alg�n valor: se asume que el valor ser� un n�mero
        if ((entrada != null) && (entrada.length() > 0)){
            retorno = Float.parseFloat(entrada);
        }else{
            retorno = 0;
        }
        
        return retorno;
    }
    
    // Muestra un mensaje con el texto y titulo indicado por pantalla, haciendo uso de un MessageDialog.
    // tipoDeMensaje indica el formato del mensaje a mostrar. Requiere tipoDeMensaje v�lido.
    private void mostrarMensaje(String texto, String titulo, int tipoDeMensaje){
        JOptionPane.showMessageDialog(null, texto, titulo, tipoDeMensaje); 
    }
}