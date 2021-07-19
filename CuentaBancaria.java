public class CuentaBancaria{
    //Atributos de clase, monto máximo para extraer en descubierto
    private static final int maxDescubierto = 1000;
    
    //Atributos de instancia
    private int codigo;
    //private float saldo;
    
    Persistencia persistencia = new Persistencia("monto");
    
    // Constructores
    // El código se establece al crear la cuenta y no cambia
    public CuentaBancaria(int cod){
        codigo = cod; 
        
    }
    
    public CuentaBancaria(int cod, float sal){
        codigo = cod;
        persistencia.guardar(sal);
        
    }
    
    // Comandos
    public void depositar(float mto){
    	persistencia.guardar(persistencia.recuperar() + mto);
        
    }
       
    public void extraer(float mto){
        if (puedeExtraer(mto)){
        	persistencia.guardar(persistencia.recuperar()- mto);
        }
    }
       
    // Consultas
    public int obtenerCodigo(){
        return codigo;
    }
    
    public float obtenerSaldo(){
        return persistencia.recuperar();
    }
    
    public String toString(){
        return codigo + " "+ persistencia.recuperar();
    }
    
    public boolean equals(CuentaBancaria cta){
        boolean iguales;
        iguales = codigo == cta.obtenerCodigo();
        iguales = iguales && Math.abs(persistencia.recuperar() - cta.obtenerSaldo()) < 0.000000000001;
        return iguales;
    }

    public boolean puedeExtraer(float mto){
        boolean puede = false;
        
        if ( (mto-persistencia.recuperar()) <= maxDescubierto){
            puede = true;
        }
        return puede;
    }

    public int mayorSaldo(CuentaBancaria cta){
        int retorno;
        
        if (persistencia.recuperar() > cta.obtenerSaldo()){
            retorno = codigo;
        }else{
            retorno = cta.obtenerCodigo();
        }
        return retorno;
    }
    
    public CuentaBancaria cta_mayorSaldo(CuentaBancaria cta){
        CuentaBancaria retorno;
        
        if (persistencia.recuperar() > cta.obtenerSaldo()){
            retorno = this;
        }else{
            retorno = cta;
        }
        return retorno;
    }
}