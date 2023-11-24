package com.example.filmify

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filmify.data.ApiServiceInterface
import com.example.filmify.data.UserInfo
import com.google.android.material.button.MaterialButton
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var apiServiceInterface: ApiServiceInterface
    private val baseUrl = "http://192.168.1.5:8080"//"http://192.168.1.5:8080/"//"localhost:8080"//"http://192.168.1.5:8080"//"http://85.221.136.115:8080"//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColors()

        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of your service interface using Retrofit
        apiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
        ///////////
        emailEditText = findViewById<EditText>(R.id.emailEditText)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton);

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                validateEmailEditText(inputText)
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                validatePasswordEditText(inputText)
            }
        })

        loginButton.setOnClickListener {
            if (validateAllEditTextFields()) {

                val requestBody = HashMap<String, Any>()
                requestBody["user_email"] = emailEditText.text.toString()
                requestBody["user_password"] = passwordEditText.text.toString();

                val call: Call<UserInfo> = apiServiceInterface.getUserInfo(requestBody)

                call.enqueue(object : Callback<UserInfo> {

                    override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                        if (response.isSuccessful) {
                            val userInfo: UserInfo? = response.body()
                            if (userInfo != null) {
                                // Handle the user info
                                val userId: BigInteger = userInfo.user_id
                                val userName: String = userInfo.user_name
                                Toast.makeText(this@LoginActivity, "$userId,$userName", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(this@LoginActivity, "OF FUCK KURWA NULL", Toast.LENGTH_SHORT)
                                    .show()
                                // The response body is null
                                // TODO: Handle this scenario
                            }
                        } else {
                            // The request was not successful (HTTP status code is not 2xx)
                            val errorCode: Int = response.code()
                            Toast.makeText(this@LoginActivity, "THE REQUEST IS UNSUCCESSFUL, FUCK $errorCode", Toast.LENGTH_SHORT)
                                .show()
                            // TODO: Handle the error based on the error code
                        }
                    }
                    override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                        if (t is HttpException) {
                            val errorCode = t.code()
                            Toast.makeText(this@LoginActivity, "HTTP Error Code: $errorCode", Toast.LENGTH_LONG).show()
                        } else {

                            t.printStackTrace()
                            Log.e("NetworkError", "Network request failed", t)
                            // Handle other types of failures (non-HTTP exceptions)
                            Toast.makeText(
                                this@LoginActivity, t.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this@LoginActivity, "Будь ласка, перевірте введені дані", Toast.LENGTH_SHORT)
                    .show()

            }
        }

        val createAccount = findViewById<TextView>(R.id.createaccount);
        createAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        }
    }

    fun setColors(): Unit {
        val recoverPassword = "Забув пароль? Відновити"

        val spannableText1 = SpannableString(recoverPassword);
        spannableText1.apply {
            setSpan(ForegroundColorSpan(Color.WHITE), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.RED), 14, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), 14, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val recoverPasswordView = findViewById<TextView>(R.id.recoverPassword)
        recoverPasswordView.text = spannableText1;

        val register = "Вперше з нами? Зареєструватися"
        val spannableText2 = SpannableString(register);
        spannableText2.apply {
            setSpan(ForegroundColorSpan(Color.WHITE), 0, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.RED), 14, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), 15, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val createAccountView = findViewById<TextView>(R.id.createaccount)
        createAccountView.text = spannableText2;
    }

    private fun validateEmailEditText(input: String): Boolean {
//        val regexPattern = "([a-z\\d_\\.#-]+@[a-z]+\\.[a-z]+)$".toRegex()
//        if (input == null) {
//            emailEditText.error = "Ви не ввели пошту!"
//            return false
//        } else if (!regexPattern.matches(input)){
//            emailEditText.error = "Введено некоретну пошту!"
//            return false
//        } else {
//            return true
//        }
        return true;
    }

    private fun validatePasswordEditText(input: String): Boolean {
        if (input == null) {
            passwordEditText.error = "Ви не ввели пароль!"
            return false
        } else if (input.length < 8){
            passwordEditText.error = "Пароль меньше 8 символів, додайте ще!"
            return false
        } else {
            return true
        }
    }

    private fun validateAllEditTextFields(): Boolean {
        val emailValidation = validateEmailEditText(emailEditText.text.toString())
        val passwordValidation = validatePasswordEditText(passwordEditText.text.toString())
        return emailValidation && passwordValidation
    }
}