package it.corso.helper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import it.corso.dao.RentalDao;
import it.corso.dao.StandoffAreaDao;
import it.corso.dao.VehicleDao;
import it.corso.model.Rental;
import it.corso.model.StandoffArea;
import it.corso.model.Vehicle;

@Component
public class BackgroundTask
{
	@Autowired
	private RentalDao rentalDao;
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private StandoffAreaDao standoffAreaDao;
	
	@Scheduled(fixedDelay = 120000)
	public void updateElementsState()
	{
		currentRentalsTermination();
		reservedRentalsStart();
	}
	
	// conclusione noleggi in corso
	private void currentRentalsTermination()
	{
		List<Rental> inProgress = rentalDao.findByState("C");
		inProgress.forEach(r -> {
			if(LocalDateTime.now().isEqual(r.getReturnTiming()) || 
					LocalDateTime.now().isAfter(r.getReturnTiming()))
			{
				updateTerminatedRentalVehicleState(r.getVehicle().getId());
				r.setState("T");
				rentalDao.save(r);
			}
		});
	}
	// aggiornamento stato veicolo su noleggio terminato
	private void updateTerminatedRentalVehicleState(int vehicleId)
	{
		Vehicle vehicle = vehicleDao.findById(vehicleId).get();
		vehicle.setState("L");
		vehicle.setStandoffArea(getRandomStandoffArea());
		vehicleDao.save(vehicle);
	}
	// determinazione random area di stazionamento per veicolo riconsegnato
	private StandoffArea getRandomStandoffArea()
	{
		List<StandoffArea> freeAreas = standoffAreaDao.findByState("L");
		int randomValue = new Random().nextInt(freeAreas.size());
		StandoffArea randomArea = freeAreas.get(randomValue);
		randomArea.setState("O");
		standoffAreaDao.save(randomArea);
		return randomArea;
	}
	
	// avvio noleggi prenotati
	private void reservedRentalsStart()
	{
		List<Rental> reserved = rentalDao.findByState("P");
		reserved.forEach(r -> {
			if(LocalDateTime.now().isEqual(r.getPickupTiming()) || 
					LocalDateTime.now().isAfter(r.getPickupTiming()))
			{
				updateStartedRentalVehicleState(r.getVehicle().getId());
				r.setState("C");
				rentalDao.save(r);
			}
		});
	}
	// aggiornamento stato veicolo su noleggio avviato
	private void updateStartedRentalVehicleState(int vehicleId)
	{
		Vehicle vehicle = vehicleDao.findById(vehicleId).get();
		vehicle.setState("P");
		updateStartedRentalStandoffAreaState(vehicle.getStandoffArea().getId());
		vehicle.setStandoffArea(null);
		vehicleDao.save(vehicle);
	}
	// aggiornamento stato area di stazionamento per veicolo "su strada"
	private void updateStartedRentalStandoffAreaState(int areaId)
	{
		StandoffArea area = standoffAreaDao.findById(areaId).get();
		area.setState("L");
		standoffAreaDao.save(area);
	}
}