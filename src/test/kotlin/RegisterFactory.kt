class RegisterFactory {

    var registerPojo= RegisterPojo()

    fun registerSuccess() :RegisterPojo{
        registerPojo.email = "eve.holt@regress.in"
        registerPojo.password= "pistol"
       return registerPojo
    }
    fun registerUnSuccess() :RegisterPojo{
        registerPojo.email = "eve.holt@regress.in"
        registerPojo.password= null
        return registerPojo
    }
    fun registerUserNotFound() :RegisterPojo{
        registerPojo.email = "teste@regress.in"
        registerPojo.password= "pistol"
        return registerPojo
    }
}