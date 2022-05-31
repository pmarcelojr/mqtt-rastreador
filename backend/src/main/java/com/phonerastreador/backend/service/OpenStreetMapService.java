package com.phonerastreador.backend.service;

import com.phonerastreador.backend.controller.dto.OpenStreetAddress;
import com.phonerastreador.backend.controller.dto.OpenStreetResponse;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.repository.LocalizacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenStreetMapService {

    private static final String URL = "https://nominatim.openstreetmap.org/reverse?lat={lat}&lon={lon}&format=json";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocalizacaoRepository repository;

    public String coordinatesToAddress(Double lat, Double lon) {
        ResponseEntity<OpenStreetResponse> response = this.restTemplate.getForEntity(URL, OpenStreetResponse.class, lat,
                lon);

        OpenStreetResponse body = response.getBody();
        OpenStreetAddress end = body.getAddress();

        return String.format("%s, %s, %s, %s, %s - CEP %s",
                end.getRoad(), end.getSuburb(), end.getCity(), end.getState(), end.getCountry(), end.getPostcode());
    }

    public void obterLocalizacao(Localizacao localizacao) {
        final Double lat = localizacao.getLatitude();
        final Double lon = localizacao.getLongitude();
        new Thread(() -> {
            String endereco = this.coordinatesToAddress(lat, lon);
            localizacao.setEndereco(endereco);
            this.repository.save(localizacao);
        })
        .start();
    }
}
