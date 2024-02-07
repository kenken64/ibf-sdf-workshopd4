package sg.edu.nus.iss.workshop4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sg.edu.nus.iss.workshop4.server.Cookie;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetRandomCookieName()
    {
        String cookieName = Cookie
            .getRandomCookie("/home/kenneth/Projects/NUS-ISS-SDF/sdf-workshopd4/cookie_file.txt");
        System.out.println(cookieName);
        assertNotNull( cookieName );
    }
}
