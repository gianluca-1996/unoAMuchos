package negocio;

import dao.PrestamoDao;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import datos.Cliente;
import datos.Cuota;
import dao.CuotaDao;
import datos.Prestamo;

public class PrestamoABM {

	private PrestamoDao dao = new PrestamoDao();
	
	public Prestamo traerPrestamo(long idPrestamo) throws Exception
	{
		Prestamo p = null;
		//Implementar: si no existe el prestamo lanzar la excepcion
		
		p = dao.traer(idPrestamo);
		
		if(p == null)
			throw new Exception("El prestamo no existe");	 
		
		return p;
	}
	
	public List<Prestamo> traerPrestamo(Cliente c) throws Exception
	{
		if(dao.traer(c) == null)
			throw new Exception("ERROR. No se encontraron prestamos");
		
		return dao.traer(c);
	}
			
	
	
	/*
			 * pendiente implementar:
			 * alta, modificar	*/
			
	
	public long agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente)
	{
		List<Cuota> listaCuotas = new ArrayList<Cuota>();
		Prestamo p = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		long idPrestamo;
		//CuotaABM abmCuota = new CuotaABM();
		double saldoPendiente = monto;
		double amortizacion, interesCuota, valorCuota, deuda;
		LocalDate vencimientoCuota = fecha.plusMonths(1); //la fecha de vencimiento de la primer cuota siempre vence al mes siguiente de la fecha otorgada
		LocalDate fechaPago = vencimientoCuota.minusDays(5);
		
		//SI EL VENCIMIENTO DE LA CUOTA ES UN DIA NO HABIL SE AJUSTA AL SIGUIENTE DIA HABIL
		if(vencimientoCuota.getDayOfWeek() == DayOfWeek.SATURDAY)
		{
			vencimientoCuota.plusDays(2);
		}
		
		if(vencimientoCuota.getDayOfWeek() == DayOfWeek.SUNDAY)
		{
			vencimientoCuota.plusDays(1);
		}
		
		for(int i = 0; i < p.getCantCuotas(); i++)
		{
			//CALCULO PARA LA PRIMER CUOTA
			if(i == 0)
			{	
				amortizacion = (saldoPendiente * interes) / (Math.pow((1 + interes), p.getCantCuotas()) - 1);
				interesCuota = saldoPendiente * interes;
				valorCuota = amortizacion + interesCuota;
				deuda = saldoPendiente - amortizacion;
				listaCuotas.add(new Cuota(i + 1, vencimientoCuota, saldoPendiente, amortizacion, interesCuota, valorCuota, deuda, false, fechaPago, p, 0.0));
				//abmCuota.agregar(i + 1, vencimientoCuota, saldoPendiente, amortizacion, interesCuota, valorCuota, deuda, false, fechaPago, p, 0.0);
			}
			
			//CALCULO PARA LAS CUOTAS SIGUIENTES
			else
			{
				amortizacion = (saldoPendiente * interes) / (Math.pow((1 + interes), p.getCantCuotas() - i) - 1);
				saldoPendiente -= amortizacion;
				interesCuota = saldoPendiente * interes;
				valorCuota = amortizacion + interesCuota;
				deuda = saldoPendiente - amortizacion;
				listaCuotas.add(new Cuota(i + 1, vencimientoCuota.plusMonths(i), saldoPendiente, amortizacion, interesCuota, valorCuota, deuda, false, fechaPago.plusMonths(i), p, 0.0));
				//abmCuota.agregar(i + 1, vencimientoCuota.plusMonths(i), saldoPendiente, amortizacion, interesCuota, valorCuota, deuda, false, fechaPago.plusMonths(i), p, 0.0);
			}
		}
		
		idPrestamo = dao.agregar(p, listaCuotas);
		
		return idPrestamo;
	}
	
	public void modificar(Prestamo p)
	{
		dao.modificar(p);
		
	}
	
	
	
}













