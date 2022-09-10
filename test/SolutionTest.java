package test;

import org.junit.jupiter.api.Test;
import pl.lextraduct.www.Solution;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void generate_bc() {

        String[] urlProvided = new String[] {
                "mysite.com/pictures/holidays.html",
                "www.lextraduct.pl/",
                "https://www.linkedin.com/in/giacomosorbi"};

        String[] urlDesired = new String[] {
                "<a href=\"/\">HOME</a> : <a href=\"/pictures/\">PICTURES</a> : <span class=\"active\">HOLIDAYS</span>",
                "<span class=\"active\">HOME</span>",
                "<a href=\"/in/\">IN</a> : <span class=\"active\">GIACOMOSORBI</span>"
        };

        for (int i = 0; i < 3; i++) {

            String actualUrl = Solution.generate_bc(urlProvided[i], ":");

            assertEquals(urlDesired[i], actualUrl);

        }
    }

    @Test
    void isAnchorContained() {
    }
}