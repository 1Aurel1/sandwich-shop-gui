package client;

import model.Sandwich;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class RestClient {

    private String baseUrl;
    private Traverson traverson;

    public RestClient() {
        this.baseUrl = "http://localhost:8080/";
        this.traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);
    }

    public List<SandwichDto> getSandwiches() {
        ParameterizedTypeReference<CollectionModel<SandwichDto>> sandwichRef =
                new ParameterizedTypeReference<CollectionModel<SandwichDto>>() {};

        CollectionModel<SandwichDto> sandwichRel =
                traverson
                        .follow("sandwiches")
                        .toObject(sandwichRef);

        assert sandwichRel != null;
        return new ArrayList<>(sandwichRel.getContent());
    }

    public SandwichDto createSandwich(SandwichDto sandwich) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(baseUrl + "sandwiches", sandwich, SandwichDto.class).getBody();
    }
}
