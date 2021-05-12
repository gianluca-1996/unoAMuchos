package test;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;
import java.time.LocalDate;
import dao.*;

public class TestAgregarPrestamo {

	public static void main(String[] args) {

		int dni = 39514986;
		ClienteABM abmCliente = new ClienteABM();
		Cliente c = null;
		PrestamoABM abmPrestamo = new PrestamoABM();
		double monto = 5000.0;
		double interesPrestamo = 0.15;
		
		try
		{
			c = abmCliente.traer(dni);
			System.out.println("Agregar nuevo prestamo al cliente --> " + c);
			abmPrestamo.agregar(LocalDate.of(2021, 9, 3), monto, interesPrestamo, 5, c);
			System.out.println("\nEXITO");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		

	}

}
