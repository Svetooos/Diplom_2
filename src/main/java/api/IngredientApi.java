package api;

import data.ingridient.GetIngredientsResponse;

public class IngredientApi extends BaseHttpClient {
    private final static String INGREDIENT_PATH = "/api/ingredients";

    public GetIngredientsResponse getIngredients() {
        return doGetRequest(INGREDIENT_PATH).as(GetIngredientsResponse.class);
    }
}
