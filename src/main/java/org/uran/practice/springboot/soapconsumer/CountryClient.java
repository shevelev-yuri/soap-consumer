package org.uran.practice.springboot.soapconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.consumingwebservice.wsdl.GetCountryRequest;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

@Component
public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    private final SOAPConnector soapConnector;

    @Value("${country-url}")
    private String url;

    @Autowired
    public CountryClient(SOAPConnector soapConnector) {
        this.soapConnector = soapConnector;
    }

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for {}", country);

        return (GetCountryResponse)soapConnector.callWebService(url, request);
    }

}