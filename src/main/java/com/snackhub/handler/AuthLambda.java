package com.snackhub.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.snackhub.domain.Customer;
import com.snackhub.gateway.CustomerGateway;
import com.snackhub.token.JwtTokenGenerator;
import software.amazon.awssdk.http.HttpStatusCode;

import java.io.IOException;
import java.util.Optional;

public class AuthLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        String cpf = getCPFInput(input);

        LambdaLogger logger = context.getLogger();
        logger.log("Autenticando cliente com o CPF: " + cpf);

        try{
            Optional<Customer> customer = new CustomerGateway().getCustomerByCPF(cpf);
            if (customer.isPresent()) {
                logger.log("Gerando token para o cliente: " + customer.get().toString());
                String token = new JwtTokenGenerator().generateToken(customer.get());

                response.setStatusCode(HttpStatusCode.OK);
                JsonObject body = new JsonObject();
                body.addProperty("token", token);
                response.setBody(body.toString());
                return response;
            }
        }catch (Exception e){
            logger.log("Error na Lambda: " + e.getMessage());
            response.setStatusCode(HttpStatusCode.UNAUTHORIZED);
            return response;
        }

        response.setStatusCode(HttpStatusCode.UNAUTHORIZED);
        return response;
    }

    private String getCPFInput(APIGatewayProxyRequestEvent input){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestBody = objectMapper.readTree(input.getBody());
            return requestBody.get("cpf").asText();
        } catch (IOException e) {
            return "Erro ao processar a entrada JSON: " + e.getMessage();
        }
    }
}
