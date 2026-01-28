package utils;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock")
public class MockApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from the mock API!";
    }

    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(@PathVariable int id) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "User " + id);
        user.put("email", "user" + id + "@example.com");
        return user;
    }

    @PostMapping("/echo")
    public Map<String, Object> echo(@RequestBody Map<String, Object> requestBody) {
        return requestBody;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/resource")
    public Map<String, Object> createResource(@RequestBody Map<String, Object> requestBody) {
        // Simulate creating a resource and returning its ID
        int newId = 12345; // Hardcoded for the mock
        Map<String, Object> response = new HashMap<>();
        response.put("id", newId);
        response.put("message", "Resource created successfully");
        return response;
    }
}