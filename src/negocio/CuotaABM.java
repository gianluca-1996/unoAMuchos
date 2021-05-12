package negocio;

import java.time.LocalDate;
import java.util.List;

import datos.Cuota;
import datos.Prestamo;
import dao.CuotaDao;

public class CuotaABM {

	CuotaDao daoCuota = new CuotaDao();
	
	public void agregar(int nroCuota, LocalDate fechaVencimiento, double saldoPendiente, double amortizacion, double interesCuota, double cuota,
			double deuda, boolean cancelada, LocalDate fechaDePago, Prestamo prestamo, double punitorios)
	{
		Cuota obj = new Cuota(nroCuota, fechaVencimiento, saldoPendiente, amortizacion, interesCuota, cuota, deuda,
				cancelada, fechaDePago, prestamo, punitorios);
		daoCuota.agregar(obj);
	}
	
	public List<Cuota> traer()
	{
		return daoCuota.traer();
	}
	
	
}
