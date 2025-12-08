package librarytest;
import libraryy.Admin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AdminLoginTest {
	@Test
	public void testAdminLoginSuccess() {
	    Admin a = new Admin("admin", "1234");
	    assertTrue(a.login("admin", "1234"));
	}

	@Test
	public void testAdminLoginFail() {
	    Admin a = new Admin("admin", "1234");
	    assertFalse(a.login("wrong", "nope"));
	}
}
