public class MaquinaExpendedoraMejorada {
    
    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // Numero de billetes vendidos
    private int billetesVendidos;
    // Tipo de maquina con premio o sin premio
    private boolean tipoMaquina;
    // Numero maximo de billetes
    private int numeroMaximoDeBilletes;
    // Cuenta el numero de series de billetes
    private int serieDeBilletes;
    
    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean tipoDeLaMaquina, int maximoDeBilletes) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        billetesVendidos = 0;
        tipoMaquina = tipoDeLaMaquina;
        numeroMaximoDeBilletes = maximoDeBilletes;
        serieDeBilletes = 0;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (billetesVendidos < numeroMaximoDeBilletes) {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }
        }
        else {
            System.out.println("No se acepta más dinero, lo siento.");
        }
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        if (billetesVendidos < numeroMaximoDeBilletes) {
            int cantidadDeDineroQueFalta = precioBillete - balanceClienteActual;
            if (cantidadDeDineroQueFalta <= 0) {        
                serieDeBilletes = serieDeBilletes + 1;
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                if (tipoMaquina == true && 0 == serieDeBilletes -3) {
                    double descuento = (precioBillete * 10) / 100;
                    System.out.println("Usted dispone de " + descuento + " euros de descuento para la tienda de alimentación de " + estacionDestino + ".");
                    serieDeBilletes = 0;
                }
                System.out.println("##################");
                System.out.println();         
        
                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                // Cuenta el numero de billetes vendidos
                billetesVendidos = billetesVendidos + 1;
            }
            else {
                System.out.println("Necesitas introducir " + (cantidadDeDineroQueFalta) + " euros mas!");
                        
            }
        }
        else  {
            System.out.println("No se pueden imprimir más billetes, se ha llegado al máximo de billetes vendidos.");
        }
    }
    
    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 
    
    public int vaciarDineroDeLaMaquina() {
        int totalDineroExtraido = balanceClienteActual + totalDineroAcumulado;
        if (balanceClienteActual == 0) {
            balanceClienteActual = 0;
            totalDineroAcumulado = 0;
        }
        else {
            System.out.println("No se puede ejecutar el vaciado mientras se introduzca dinero");
            totalDineroExtraido = -1;
        }
        return totalDineroExtraido;
    }
    
    public int getNumeroBilletesVendidos() {
        return billetesVendidos;
    }
    
    public void imprimeNumeroBilletesVendidos() {
        System.out.println("Se han vendido estos billetes: " + billetesVendidos);
    }
}
