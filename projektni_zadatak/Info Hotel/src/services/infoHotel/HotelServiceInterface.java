package services.infoHotel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import services.infoHotel.model.Hotel;

public interface HotelServiceInterface extends Remote{
	
	List<Hotel> getAll() throws RemoteException;
    int add(Hotel hotel) throws RemoteException;
    boolean delete(int id) throws RemoteException;
}
