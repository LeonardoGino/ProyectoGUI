public class TestCuentaBancaria {
    public static void main(String [] args) {
        CuentaBancaria c = new CuentaBancaria (923001);
        GUI_Cajero unaCuenta = new GUI_Cajero(c);
        unaCuenta.setVisible(true);
    }
}