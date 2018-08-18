package services.infoTown.service;

public class TouristAttractionServiceProxy implements services.infoTown.service.TouristAttractionService {
  private String _endpoint = null;
  private services.infoTown.service.TouristAttractionService touristAttractionService = null;
  
  public TouristAttractionServiceProxy() {
    _initTouristAttractionServiceProxy();
  }
  
  public TouristAttractionServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTouristAttractionServiceProxy();
  }
  
  private void _initTouristAttractionServiceProxy() {
    try {
      touristAttractionService = (new services.infoTown.service.TouristAttractionServiceServiceLocator()).getTouristAttractionService();
      if (touristAttractionService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)touristAttractionService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)touristAttractionService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (touristAttractionService != null)
      ((javax.xml.rpc.Stub)touristAttractionService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public services.infoTown.service.TouristAttractionService getTouristAttractionService() {
    if (touristAttractionService == null)
      _initTouristAttractionServiceProxy();
    return touristAttractionService;
  }
  
  public services.infoTown.model.TouristAttraction[] getAll() throws java.rmi.RemoteException{
    if (touristAttractionService == null)
      _initTouristAttractionServiceProxy();
    return touristAttractionService.getAll();
  }
  
  
}