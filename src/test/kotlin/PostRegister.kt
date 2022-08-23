import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PostRegister {


    fun registerUserSucessful(): Response {
        return Given {
            header("Content-Type", "application/json")
            val jsonuser = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/register")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun register02() {

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("email", "eve.holt@regres.in")
        hashMap.put("password", "pistol")

         Given {
            contentType(ContentType.JSON)
            body(hashMap)

        } When {
            post("https://reqres.in/api/register")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
    fun register03() {
       val registerPojo= RegisterPojo()
       registerPojo.email="eve.holt@reqres.in"
        registerPojo.password="pistol"


        Given {
            contentType(ContentType.JSON)
            body(registerPojo)
            log().all()
        } When {
            post("https://reqres.in/api/register")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun register04(registerPojo: RegisterPojo) {
        Given {
            contentType(ContentType.JSON)
            body(registerPojo)
            log().all()
        } When {
            post("https://reqres.in/api/register")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun postLogin(loginPojo: RegisterPojo) {
        Given {
            contentType(ContentType.JSON)
            body(loginPojo)
            log().all()
        } When {
            post("https://reqres.in/api/login")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
        @Test
        fun registerSucessfulTest() {
            val response = registerUserSucessful()
            val json = response.jsonPath()
            assertEquals(200, response.statusCode)
            assertEquals("4", json.getString("id"))


    }
        @Test
        fun register02Test() {
           register02()
        }
    @Test
    fun register03Test() {
        register03()
    }
    val registerFactory=RegisterFactory()


    @Test
    fun registerSuccessTest() {
        register04(registerFactory.registerSuccess())
    }
    @Test
    fun registerFailTest() {
        register04(registerFactory.registerUnSuccess())
    }
    @Test
    fun registerUserNotFound() {
        register04(registerFactory.registerUserNotFound())
    }
    val loginFactory= LoginFactory()
    @Test
    fun loginSuccessTest() {
        postLogin(loginFactory.loginSuccess())
    }
    @Test
    fun loginFailTest() {
        postLogin(loginFactory.loginUnSuccess())
    }
    @Test
    fun loginUserNotFound() {
        postLogin(loginFactory.loginUserNotFound())
    }

}