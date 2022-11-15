package fr.sfeir.micro.micro2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fibonnaci")
public class FiboController {

    @GetMapping("/{num}")
    public String fibo(@PathVariable("num") long num) {

        long start = System.currentTimeMillis();
        long res = fibonacci(num);
        long end = System.currentTimeMillis();

        return "La valeur pour '%d' est '%d' (temps exécution (ms) = %s)".formatted(num, res, (end - start) + "");
    }

    private long fibonacci(final long n) {

        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
