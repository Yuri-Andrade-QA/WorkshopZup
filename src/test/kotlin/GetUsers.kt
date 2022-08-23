import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.module.kotlin.extensions.Then
import io.restassured.response.Response
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class GetUsers {


    fun getUsers(page: Int, per_page: Int): Response {
        return Given {
            queryParam("page", 2)
            queryParam("per_page", 1)
        } When {
            get("https://reqres.in/api/users")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun getSingleUsers(): Response {

        return When {
            get("https://reqres.in/api/users/2")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
    fun getSingleUsersNotFound(): Response {

       return When {
            get("https://reqres.in/api/users/23")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun getListUnknown(): Response {

        return When{
            get("https://reqres.in/api/unknown")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }
    fun getListUnknownSingle(): Response {

        return When{
            get("https://reqres.in/api/unknown/2")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }
    fun getListUnknownNotFound(): Response {

        return When{
            get("https://reqres.in/api/unknown/23")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }

    fun postUsers(): Response {



        return Given {
            header("Content-Type", "application/json")
            val jsonuser="{\"name\": \"morpheus\",\"job\": \"leader\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/users")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
    fun putUsers(): Response {

        return Given {
            header("Content-Type", "application/json")
            val jsonuser="{\"name\": \"morpheus\",\"job\": \"zion resident\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/users/2")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
    fun patchUsers(): Response {

        return Given {
            header("Content-Type", "application/json")
            val jsonuser="{\"name\": \"morpheus\",\"job\": \"zion\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/users/2")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun deleteUsers(): Response {

        return When {
            delete("https://reqres.in/api/users/2")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }


    fun registerUserUnsucessful(): Response {
        return Given {
            header("Content-Type", "application/json")
            val jsonuser = "{\"email\": \"sydney@fife\"}"
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
    fun loginUser(): Response {
        return Given {
            header("Content-Type", "application/json")
            val jsonuser = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/login")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }
    fun loginUserUnsucess(): Response {
        return Given {
            header("Content-Type", "application/json")
            val jsonuser = "{\"email\": \"peter@klaven\"}"
            body(jsonuser)
            log().all()
        } When {
            post("https://reqres.in/api/login")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun getDelayedUsers(delay: Int): Response {
        return Given {
            queryParam("delay", 3)

        } When {
            get("https://reqres.in/api/users")

        } Then {
            log().all()
        } Extract {
            response()
        }

    }


    @Test
    fun getUsersTest() {
        val response= getUsers(2,1)
        val json= response.jsonPath()
        assertNotNull(response)
        assertEquals(200,response.statusCode)
        assertEquals(2,json.getInt("page"))
        assertEquals("Janet",json.getString("data[0].first_name"))
    }

@Test
 fun getSingleUsersTest() {
    val response=getSingleUsers()
    assertEquals(200,response.statusCode)

 }
    @Test
    fun getSingleUsersNotFoundTest() {
        val response=getSingleUsersNotFound()
        assertEquals(404,response.statusCode)

    }
    @Test fun getListUnknownTest() {
        val response=getListUnknown()
        val json= response.jsonPath()
        assertEquals(200,response.statusCode)
        assertEquals("cerulean",json.getString("data[0].name"))
    }
    @Test fun getListUnknownSingleTest() {
        val response=getListUnknownSingle()
        val json= response.jsonPath()
        assertEquals(200,response.statusCode)
        assertEquals("fuchsia rose",json.getString("data.name"))
    }
    @Test fun getListUnknownNotFoundTest() {
        val response=getListUnknownNotFound()
        assertEquals(404,response.statusCode)

    }

    @Test fun postUsersTest() {
        val response=postUsers()
        val json= response.jsonPath()
        assertEquals(201,response.statusCode)
        assertEquals("morpheus",json.getString("name"))

    }

    @Test fun putUsersTest() {
        val response=putUsers()
        val json= response.jsonPath()
        assertEquals(201,response.statusCode)
        assertEquals("zion resident",json.getString("job"))

    }
    @Test fun patchUsersTest() {
        val response=patchUsers()
        val json= response.jsonPath()
        assertEquals(201,response.statusCode)
        assertEquals("zion",json.getString("job"))

    }
    @Test fun deleteUsersTest() {
        val response=deleteUsers()
        assertEquals(204,response.statusCode)


    }


    @Test fun registerUnsucessfulTest() {
        val response= registerUserUnsucessful()
        val json= response.jsonPath()
        assertEquals(400,response.statusCode)
        assertEquals("Missing password",json.getString("error"))

    }
    @Test fun loginUserTest() {
        val response= loginUser()
        val json= response.jsonPath()
        assertEquals(200,response.statusCode)
        assertNotNull(json.getString("token"))


    }

    @Test fun loginUserUnsucessTest() {
        val response= loginUserUnsucess()
        val json= response.jsonPath()
        assertEquals(400,response.statusCode)
        assertEquals("Missing password",json.getString("error"))


    }

    @Test
    fun getUsersDelayedTest() {
        val response= getDelayedUsers(3)
        val json= response.jsonPath()
        assertNotNull(response)
        assertEquals(200,response.statusCode)
        assertEquals(1,json.getInt("page"))
        assertEquals("George",json.getString("data[0].first_name"))
    }


}















