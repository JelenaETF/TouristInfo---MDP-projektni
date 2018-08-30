/**
 * TouristAttractionServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services.infoTown.service;

public class TouristAttractionServiceServiceLocator extends org.apache.axis.client.Service implements services.infoTown.service.TouristAttractionServiceService {

    public TouristAttractionServiceServiceLocator() {
    }


    public TouristAttractionServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TouristAttractionServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TouristAttractionService
    private java.lang.String TouristAttractionService_address = "http://localhost:8080/InfoTown/services/TouristAttractionService";

    public java.lang.String getTouristAttractionServiceAddress() {
        return TouristAttractionService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TouristAttractionServiceWSDDServiceName = "TouristAttractionService";

    public java.lang.String getTouristAttractionServiceWSDDServiceName() {
        return TouristAttractionServiceWSDDServiceName;
    }

    public void setTouristAttractionServiceWSDDServiceName(java.lang.String name) {
        TouristAttractionServiceWSDDServiceName = name;
    }

    public services.infoTown.service.TouristAttractionService getTouristAttractionService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TouristAttractionService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTouristAttractionService(endpoint);
    }

    public services.infoTown.service.TouristAttractionService getTouristAttractionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            services.infoTown.service.TouristAttractionServiceSoapBindingStub _stub = new services.infoTown.service.TouristAttractionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getTouristAttractionServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTouristAttractionServiceEndpointAddress(java.lang.String address) {
        TouristAttractionService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (services.infoTown.service.TouristAttractionService.class.isAssignableFrom(serviceEndpointInterface)) {
                services.infoTown.service.TouristAttractionServiceSoapBindingStub _stub = new services.infoTown.service.TouristAttractionServiceSoapBindingStub(new java.net.URL(TouristAttractionService_address), this);
                _stub.setPortName(getTouristAttractionServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TouristAttractionService".equals(inputPortName)) {
            return getTouristAttractionService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.infoTown.services", "TouristAttractionServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.infoTown.services", "TouristAttractionService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TouristAttractionService".equals(portName)) {
            setTouristAttractionServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
