package com.virtoworks.omnia.utils.actions.orders.data;

/**
 * OrdersData class provides a static dataset for order search operations.
 * This class contains a hardcoded JSON string that mimics a sample dataset of orders,
 * each identified by an "id" and associated with a "keyword".
 * The "keyword" field represents searchable terms associated with each order,
 * which can be utilized in testing search functionalities in an application.
 * <p>
 * The JSON structure is as follows:
 * - "orders": an array of order objects.
 *   - Each order object has:
 *     - "id": a unique identifier for the product.
 *     - "keyword": a string that can be used to search for the product.
 * <p>
 * Usage:
 * This dataset can be used to simulate retrieving product information from a database
 * or an external service. It's particularly useful in testing environments where
 * real database connections are impractical or unnecessary.
 */
public class OrdersData {
    public static final String JSON_DATA = """
    {
      "products": [
        {"id": 1, "keyword": "Global"},
        {"id": 2, "keyword": "global"},
        {"id": 3, "keyword": "global"},
        {"id": 4, "keyword": "global"},
        {"id": 5, "keyword": "global"},
        {"id": 6, "keyword": "global"},
        {"id": 7, "keyword": "global"},
        {"id": 8, "keyword": "global"},
        {"id": 9, "keyword": "global"},
        {"id": 10, "keyword": "global"},
      ]
    }
    """;
}
