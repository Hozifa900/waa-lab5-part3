import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mvc.domain.Account;
import mvc.dto.AccountCommand;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BankAccountRESTTest {

        @BeforeClass
        public static void setup() {
                RestAssured.port = Integer.valueOf(8080);
                RestAssured.baseURI = "http://localhost";
                RestAssured.basePath = "";
        }

        @Test
        public void testGetOneAccount() {
                AccountCommand accountCommand = new AccountCommand("create", 667L, 0.0, "Joe Smith");
                // add the account to be fetched
                given()
                                .contentType("application/json")
                                .body(accountCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // test getting the book
                given()
                                .when()
                                .get("accounts/667")
                                .then()
                                .contentType(ContentType.JSON)
                                .and()
                                .body("accountnumber", equalTo(667))
                                .body("accountHolder", equalTo("Joe Smith"))
                                .body("balance", equalTo(0.0f));
                // cleanup
                given()
                                .when()
                                .delete("accounts/667");
        }

        @Test
        public void testDeleteAccount() {
                // create the account to be deleted
                AccountCommand accountCommand = new AccountCommand("create", 667L, 0.0, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(accountCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // delete account
                given()
                                .when()
                                .delete("accounts/667");
                // verify if it is deleted
                given()
                                .when()
                                .get("accounts/667")
                                .then()
                                .statusCode(404)
                                .and()
                                .body("errorMessage", equalTo("Account with number= 667 is not available"));
        }

        @Test
        public void testCreateAccount() {
                // create the account to deposit to
                AccountCommand accountCommand = new AccountCommand("create", 667L, 0.0, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(accountCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // verify account
                given()
                                .when()
                                .get("accounts/667")
                                .then()
                                .contentType(ContentType.JSON)
                                .and()
                                .body("accountnumber", equalTo(667))
                                .body("accountHolder", equalTo("Joe Smith"))
                                .body("balance", equalTo(0.0f));
                // cleanup
                given()
                                .when()
                                .delete("accounts/667");

        }

        @Test
        public void testDepositAccount() {
                // create the account to deposit to
                AccountCommand accountCommand = new AccountCommand("create", 667L, 0.0, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(accountCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // deposit to this account
                AccountCommand depositCommand = new AccountCommand("deposit", 667L, 122.25, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(depositCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // verify balance
                given()
                                .when()
                                .get("accounts/667")
                                .then()
                                .contentType(ContentType.JSON)
                                .and()
                                .body("accountnumber", equalTo(667))
                                .body("accountHolder", equalTo("Joe Smith"))
                                .body("balance", equalTo(122.25f));
                // cleanup
                given()
                                .when()
                                .delete("accounts/667");

        }

        @Test
        public void testWithdrawAccount() {
                // create the account to deposit to
                AccountCommand accountCommand = new AccountCommand("create", 667L, 0.0, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(accountCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // withdraw from this account
                AccountCommand withdrawCommand = new AccountCommand("withdraw", 667L, 122.25, "Joe Smith");
                given()
                                .contentType("application/json")
                                .body(withdrawCommand)
                                .when().post("/accounts").then()
                                .statusCode(200);
                // verify balance
                given()
                                .when()
                                .get("accounts/667")
                                .then()
                                .contentType(ContentType.JSON)
                                .and()
                                .body("accountnumber", equalTo(667))
                                .body("accountHolder", equalTo("Joe Smith"))
                                .body("balance", equalTo(-122.25f));
                // cleanup
                given()
                                .when()
                                .delete("accounts/667");

        }

}
