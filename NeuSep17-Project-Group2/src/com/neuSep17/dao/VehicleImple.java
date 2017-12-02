package com.neuSep17.dao;
        
import java.util.ArrayList;
       
import java.util.Collections;
      

import com.neuSep17.dto.Dealer;
import com.neuSep17.dto.Inventory;
import com.neuSep17.dto.Vehicle;
       


//THIS CLASS IMPLEMENTS LOGIC TO ACCESS gmps-****-*** files

public class VehicleImple implements IVehicleManager {

    //private Vehicle vehicle;
    private HashMap<String, Inventory> allInventory = new HashMap<String, Inventory>();

    public VehicleImple() {
    }

    public VehicleImple(File filepath) {
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("gmps-")) {
                String dealer = listOfFiles[i].getName();
                Inventory inventory = new Inventory(new File(filepath + "/" + dealer));
                allInventory.put(dealer, inventory);
            }
        }
    }

    //new method per feedback
    @Override
    public ArrayList<Inventory> getInventoryForDealers(ArrayList<Dealer> dealers) {
        ArrayList<Inventory> inventories = new ArrayList<Inventory>();
        for (Dealer dealer : dealers) {
            inventories.add(allInventory.get(dealer.getId()));
        }
        return inventories;
    }

    @Override
    public Inventory getInventory(String dealerID) {
        return allInventory.get(dealerID);
    }

    @Override
    public Vehicle getAVehicle(String dealerId, String vehicleID) {
        Collection<Vehicle> vehs = allInventory.get(dealerId).getVehicles();
        Vehicle veh = new Vehicle();
        for (Vehicle v : vehs)
            if (v.getID().equals(vehicleID)) {
                veh = v;
                break;
            }
        return veh;
    }

    @Override
    public Inventory searchInventory(String dealerID, Vehicle vehicle) {
        ArrayList<Vehicle> results = new ArrayList<>();
        Inventory inventory = allInventory.get(dealerID);

        // When ID or WebId is specified in the search criteria, only the matching entry is returned
        if (vehicle.getID() != null) {
            results.add(getAVehicle(dealerID, vehicle.getID()));
            return new Inventory(dealerID, results);
        }

        if (vehicle.getWebID() != null) {
            results.add(getAVehicle(dealerID, vehicle.getWebID()));
            return new Inventory(dealerID, results);
        }

        // Search results will include entries that match all the specified criteria. Omitted fields are considered boolean OR
        // price field is assumed to the max price
        for (Vehicle v : inventory.getVehicles()) {

            if (vehicle.getCategory() != null && !(v.getCategory() == (vehicle.getCategory())))
                continue;

            if (vehicle.getYear() != 0 && v.getYear() != vehicle.getYear())
                continue;

            if (vehicle.getMake() != null && !(v.getMake().equals(vehicle.getMake())))
                continue;

            if (vehicle.getModel() != null && !(v.getModel().equals(vehicle.getModel())))
                continue;

            if (vehicle.getTrim() != null && !(v.getTrim().equals(vehicle.getTrim())))
                continue;

            if (vehicle.getBodyType() != null && !(v.getBodyType().equals(vehicle.getBodyType())))
                continue;

            if (vehicle.getPrice() != 0 && v.getPrice() > vehicle.getPrice())
                continue;

            results.add(v);
        }
        return new Inventory(dealerID, results);
    }
	
	@Override
	public boolean addVehicle(String dealerID, Vehicle v) {
		boolean isSuccess =false;
		/*
    	 * 
    	 * CODE HERE TO ACCESS GMPS-*** FILES
    	 */  
		return isSuccess;
	}
	
	@Override
	public boolean updateVehicle(String dealerID, Vehicle v) {
		if(deleteVehicle(dealerID, v.getID()) && addVehicle(dealerID, v)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteVehicle(String dealerID, String vehicleID) {
		boolean isSuccess =false;
		/*
    	 * 
    	 * CODE HERE TO ACCESS GMPS-*** FILES
    	 */  
		return isSuccess;
	}
	
	//call this to sort
    public void sortBy(ArrayList<Vehicle> v, String sortingField) {
    	for(Vehicle vehicle : v) {
    		vehicle.setSortingField(sortingField);
    	}
    	Collections.sort(v);
    }

}
