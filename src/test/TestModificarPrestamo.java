package test;

import java.time.LocalDate;

import dao.PrestamoDao;
import datos.Prestamo;

public class TestModificarPrestamo {

	public static void main(String[] args) {
		
		Prestamo p = null;
		long idPrestamo = 3;
		double interes = 15;
		
		try
		{
			PrestamoDao daoPresta = new PrestamoDao();
			p = daoPresta.traer(idPrestamo);
			
			System.out.println("Prestamo a modificar--> " + p);
			
			p.setInteres(interes);
			daoPresta.modificar(p);
			
			System.out.println("Prestamo modificado --> " + p);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
