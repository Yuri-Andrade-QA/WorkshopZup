class LoginFactory {

    var loginPojo= RegisterPojo()


    fun loginSuccess() :RegisterPojo{
        loginPojo.email = "eve.holt@reqres.in"
        loginPojo.password= "cityslicka"
        return loginPojo
    }
    fun loginUnSuccess() :RegisterPojo{
        loginPojo.email = "peter@klaven"
        loginPojo.password= null
        return loginPojo
    }
    fun loginUserNotFound() :RegisterPojo{
        loginPojo.email = "teste@regress.in"
        loginPojo.password= "pistol"
        return loginPojo
    }
}