// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 30/05/2026 | Modificación: 01/06/2026
// Descripción: Lógica matemática estática para métricas del hogar.
// ==============================================================================
package logica;
import java.util.ArrayList;

public class CalculadoraHogar {
    
    public static double calcularBalance(double presupuestoTotal, ArrayList<Double> gastos) {
        double sumatoria = 0;
        for (Double gasto : gastos) {
            sumatoria += gasto;
        }
        double balance = presupuestoTotal - sumatoria;
        System.out.println("[CONSOLE LOG] Balance calculado. Presupuesto: $" + presupuestoTotal + " | Gastos: $" + sumatoria + " | Restante: $" + balance);
        return balance;
    }

    public static double calcularProductividad(int tareasCompletadas, int totalTareas) {
        if (totalTareas <= 0) {
            System.out.println("[CONSOLE ERROR] Total de tareas inválido para promediar.");
            return 0.0;
        }
        double porcentaje = ((double) tareasCompletadas / totalTareas) * 100.0;
        System.out.println("[CONSOLE LOG] Productividad calculada: " + porcentaje + "%");
        return porcentaje;
    }
}