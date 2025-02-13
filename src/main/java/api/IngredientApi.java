package api;

import data.ingridient.GetIngredientsResponse;
import io.restassured.response.Response;

public class IngredientApi extends BaseHttpClient {
    private final static String INGREDIENT_PATH = "/api/ingredients";

    public GetIngredientsResponse getIngredients() {
        Response response = doGetRequest(INGREDIENT_PATH);
        GetIngredientsResponse getIngredientsResponse = response.as(GetIngredientsResponse.class);
        getIngredientsResponse.setCode(response.getStatusCode());
        return getIngredientsResponse;
    }
}
