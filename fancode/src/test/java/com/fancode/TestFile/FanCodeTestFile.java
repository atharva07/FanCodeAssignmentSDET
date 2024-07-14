package com.fancode.TestFile;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fancode.reporting.ExtentReportManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FanCodeTestFile {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com"; 

    @Test
    public void testFanCodeUsersTodoCompletion() {
        // Fetch all users. We are providing Base URL and hitting GET request.
        Response usersResponse = RestAssured.get(BASE_URL + "/users");
        Assert.assertEquals(usersResponse.getStatusCode(), 200);
        ExtentReportManager.logInfoDetails("Response Status code is : " + Integer.toString(usersResponse.getStatusCode()));
        List<Map<String, Object>> users = usersResponse.jsonPath().getList("");
        
        // Filter users from city "FanCode". Here parameters such as Latitude and Longitude are used to get the location of 
        // city Fancode. Then after they are stored in List.
        List<Map<String, Object>> fanCodeUsers = users.stream()
                    .filter(user -> {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> address = (Map<String, Object>) user.get("address");
                        @SuppressWarnings("unchecked")
                        Map<String, Object> geo = (Map<String, Object>) address.get("geo");
                        double lat = Double.parseDouble((String) geo.get("lat"));
                        double lng = Double.parseDouble((String) geo.get("lng"));
                        return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
                    })
                    .collect(Collectors.toList());
        
        // Verify each user has more than 50% of todos completed. 
        for (Map<String, Object> user : fanCodeUsers) {
            int userId = (Integer) user.get("id");
            // Fetch todos for the user
            Response todosResponse = RestAssured.get(BASE_URL + "/todos?userId=" + userId);
            Assert.assertEquals(todosResponse.getStatusCode(), 200);
            //ExtentReportManager.logInfoDetails("Todo Response Body is : ");
            //ExtentReportManager.logJson(todosResponse.getBody().prettyPrint());
            List<Map<String, Object>> todos = todosResponse.jsonPath().getList("");
            
            // Calculate completed percentage
            long completedCount = todos.stream()
                    .filter(todo -> (Boolean) todo.get("completed"))
                    .count();
            double completedPercentage = ((double) completedCount / todos.size()) * 100;

            // Print completed percentage to console
            System.out.println("User ID: " + userId + " - Completed Task Percentage: " + completedPercentage + "%");

            ExtentReportManager.logInfoDetails("User ID : " + userId);
            ExtentReportManager.logInfoDetails("Completed Task Percentage : " + Double.toString(completedPercentage));
            // Assert the completed percentage is greater than 50%
            Assert.assertTrue(completedPercentage > 50, "User ID: " + userId + " has less than 50% completed tasks.");
        }
    }
}