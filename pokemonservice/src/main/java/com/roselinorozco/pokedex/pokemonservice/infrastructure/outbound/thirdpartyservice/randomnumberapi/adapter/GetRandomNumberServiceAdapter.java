package com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.thirdpartyservice.randomnumberapi.adapter;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.UnableToGetRandomNumberException;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.RandomNumberOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Roselin Orozco
 */
@Service
public class GetRandomNumberServiceAdapter implements RandomNumberOutputPort {

    private final String API_URL = "http://www.randomnumberapi.com/api/v1.0/random?min=1&max=100000&count=1";
    private final RestTemplate restTemplate;

    @Autowired
    public GetRandomNumberServiceAdapter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public int getRandomNumber() {

        try {
            final ResponseEntity<List> randomNumberResponse = restTemplate.getForEntity(API_URL, List.class);

            if (!randomNumberResponse.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
                throw new UnableToGetRandomNumberException("Error getting random number");
            }

            List<Integer> randomNumberList = randomNumberResponse.getBody();

            if (randomNumberList == null || randomNumberList.isEmpty()) {
                throw new UnableToGetRandomNumberException("Random number is not available");
            }

            return randomNumberList.get(0).intValue();

        } catch (RestClientException exception) {
            throw new UnableToGetRandomNumberException("Random number service is not available");
        }
    }
}
