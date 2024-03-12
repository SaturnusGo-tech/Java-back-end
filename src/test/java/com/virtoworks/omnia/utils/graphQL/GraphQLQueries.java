package com.virtoworks.omnia.utils.graphQL;

/**
 * The GraphQLQueries class encapsulates various GraphQL query strings as static methods.
 * This structure allows for better organization and reusability of GraphQL queries across the project.
 *
 * Each method in this class returns a GraphQL query string that can be used to perform specific
 * operations or fetch data from a GraphQL server. By centralizing query definitions, we achieve
 * consistency and ease of maintenance.
 */
public class GraphQLQueries {

    /**
     * Constructs and returns a GraphQL query for searching product offers.
     * <p>
     * The query fetches product items based on the provided parameters: storeId, userId,
     * currencyCode, cultureName, and filter. For each item, the query retrieves the product
     * ID and the actual price amount.
     * <p>
     * Parameters:
     * - $storeId: Represents the unique identifier of the store.
     * - $userId: The ID of the user making the request. This can be used for personalization.
     * - $currencyCode: The currency code (e.g., USD, EUR) to format the price amounts.
     * - $cultureName: The culture name (e.g., en-US) to adapt the response to a specific locale.
     * - $filter: A string used to filter the products based on certain criteria.
     * <p>
     * Usage:
     * This query can be used in GraphQL requests to fetch a list of products with their prices,
     * filtered by the specified criteria. It's particularly useful for e-commerce applications
     * that need to display product offers to users.
     *
     * @return The GraphQL query string for searching product offers.
     */
    public static String searchProductsOffersQuery() {
        return """
            query SearchProductsOffers($storeId: String!, $userId: String!, $currencyCode: String!, $cultureName: String, $filter: String) {
                products(
                    storeId: $storeId
                    userId: $userId
                    currencyCode: $currencyCode
                    cultureName: $cultureName
                    filter: $filter
                ) {
                    items {
                        id
                        price {
                            actual {
                                amount
                            }
                        }
                    }
                }
            }
            """;
    }
}
