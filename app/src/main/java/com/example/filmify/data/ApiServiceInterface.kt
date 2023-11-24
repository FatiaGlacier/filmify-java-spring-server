package com.example.filmify.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger

interface ApiServiceInterface {
    @GET("/users/{userId}")
    fun getUserData(@Path("userId") user_name: String, user_password:String): Call<User>


//    @POST("/api/v1/user/add-user")
//    fun getUserInfo(@Body registerInfo: UserLoginInfo): Call<ResponceInfo>

    @POST("/api/v1/user/login")
    fun getUserInfo(@Body requestBody: HashMap<String, Any>): Call<UserInfo>

//    @GET("api/v1/user/login")
//    fun getUserInfo(
//        @Query("user_name") userName: String,
//        @Query("password") password: String
//    ): Call<UserInfo>

    @GET("/users")
    fun getAllUsers(): Call<List<User>>

//    @POST("/api/v1/user/add-user")
//    fun addUser(@Body registerInfo: RegisterInfo): Call<ResponceInfo>


    // Define the endpoint and the HTTP method
//    @PUT("/api/v1/user/add-user-fav-genre/{user_id}")
    // Define the method and specify the request body
//    fun putData(@Path("user_id") user_id: BigInteger, @Body requestBody: YourRequestBodyClass): Call<YourResponseClass>

    // Add more endpoints as needed for your application
}