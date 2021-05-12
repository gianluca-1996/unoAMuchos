package test;

import java.util.List;
import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestTraerPrestamo {

	public static void main(String[] args) {

		PrestamoABM prestamoABM = new PrestamoABM();
		long idPrestamo = 1;
		Prestamo p = null;
		
		System.out.println("\nTraer prestamo idPrestamo = " + idPrestamo + "\n\n");
		
		//IMPLEMENTAR QUE SI ESTE CLIENTE NO TIENE PRESTAMOS OTORGADOS IMPRIMA EL MENSAJE
		try
		{			
			p = prestamoABM.traerPrestamo(idPrestamo);
			System.out.println(p + "\nPertenece a " + p.getCliente());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		ClienteABM clienteABM = new ClienteABM();
		int dni = 14000000;
		List<Prestamo> prestamos;
				
		//IMPLEMENTAR SI ESTE CLIENTE NO TIENE PRESTAMOS OTORGADOS QUE IMPRIMA EL MENSAJE
		try
		{
			Cliente c = clienteABM.traer(dni);
			System.out.println("\nTraer prestamos del cliente --> " + c + "\n\n");
			prestamos = prestamoABM.traerPrestamo(c);
					
			for(Prestamo o : prestamos)
				System.out.println(o + "\nPertenece a " + o.getCliente());
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			
	}

}
