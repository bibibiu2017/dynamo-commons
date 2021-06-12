package ke.co.dynamodigital.commons.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.*;
import ke.co.dynamodigital.commons.models.swagger.Error;
import org.springdoc.core.customizers.OpenApiCustomiser;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author arthurmita
 * created 05/06/2021 at 03:24
 **/
public class DefaultResponseCustomizer implements OpenApiCustomiser {
    @Override
    public void customise(OpenAPI openApi) {
        openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            ApiResponses apiResponses = operation.getResponses();

            ApiResponse badRequest = new ApiResponse().description("Bad request, Invalid request data")
                    .content(new Content()
                            .addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                                    .schema(new Schema<Error>().example(Error.badRequest()).title("Bad Request"))));
            ApiResponse unauthorized = new ApiResponse().description("Unauthorized");
            ApiResponse forbidden = new ApiResponse().description("Forbidden, Insufficient scope")
                    .content(new Content()
                            .addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                                    .schema(new Schema<Error>().example(Error.forbidden()).title("Forbidden Response"))));
            ApiResponse conflict = new ApiResponse().description("Conflict occurred during request processing")
                    .content(new Content()
                            .addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                                    .schema(new Schema<Error>().example(Error.conflict()).title("Conflict Response"))));
            ApiResponse unprocessableEntity = new ApiResponse().description("Unprocessable request")
                    .content(new Content()
                            .addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                                    .schema(new Schema<Error>().example(Error.unprocessableEntity()).title("Unprocessable Entity Response"))));
            ApiResponse internalError = new ApiResponse().description("Internal Error, Try again later");
            apiResponses.addApiResponse("400", badRequest);
            apiResponses.addApiResponse("401", unauthorized);
            apiResponses.addApiResponse("403", forbidden);
            apiResponses.addApiResponse("409", conflict);
            apiResponses.addApiResponse("422", unprocessableEntity);
            apiResponses.addApiResponse("500", internalError);
        }));
    }
}
