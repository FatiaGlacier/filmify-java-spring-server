package com.example.filmify

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filmify.data.ApiServiceInterface
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegisterActivity : AppCompatActivity() {
    lateinit var emailEditText: EditText
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var gendersRadioGroup: RadioGroup
    private lateinit var dateEditText: EditText
    private var year = 0
    private var month = 0
    private var day = 0
    private var selectedDate: String? = null
    private var selectedGender: String? = null

    lateinit var apiServiceInterface: ApiServiceInterface
    private val baseUrl = "http://192.168.1.5:8080"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setColor()
        ////
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of your service interface using Retrofit
        apiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
        ///////////
        emailEditText = findViewById<EditText>(R.id.emailEditText)
        usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        gendersRadioGroup = findViewById<RadioGroup>(R.id.gendersRadioGroup)
        dateEditText = findViewById(R.id.dateEditText)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        emailEditText.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputText: String = emailEditText.getText().toString()
                validateEmail(inputText);
            }
        })

        usernameEditText.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputText: String = usernameEditText.getText().toString()
                validateUsername(inputText);
            }
        })

        gendersRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                selectedGender = selectedRadioButton.text.toString()
            }
        }

        passwordEditText.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputText: String = passwordEditText.getText().toString()
                validatePassword(inputText);
            }
        })

        val registerButton = findViewById<MaterialButton>(R.id.registerButton);
        registerButton.setOnClickListener {
            if (validateAllEditTextFields()) {
                //TODO send request to server
            } else {
                Toast.makeText(this@RegisterActivity, "Будь ласка, перевірте введені дані", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val login = findViewById<TextView>(R.id.login);
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_reverse, R.anim.slide_out_reverse)
        }
    }

    private fun setColor() : Unit {
        val login = "Вже маєте акаунт? Увійти"
        val spannableText1 = SpannableString(login);
        spannableText1.apply {
            setSpan(ForegroundColorSpan(Color.WHITE), 0, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.RED), 18, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), 18, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val loginView = findViewById<TextView>(R.id.login)
        loginView.text = spannableText1;
    }

    fun showDatePickerDialog(view: View) {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val minYear = currentYear - 99
        val maxYear = currentYear - 6
        year = currentYear
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                dateEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().apply {
            set(maxYear, month, day)
        }.timeInMillis
        datePickerDialog.datePicker.minDate = Calendar.getInstance().apply {
            set(minYear, month, day)
        }.timeInMillis
        datePickerDialog.show()
    }

    private fun validateEmail(input: String): Boolean {
        val regexPattern = "([a-z\\d_\\.#-]+@[a-z]+\\.[a-z]+)$".toRegex()
        if (input == null) {
            emailEditText.error = "Ви не ввели пошту!"
            return false
        } else if (!regexPattern.matches(input)){
            emailEditText.error = "Введено некоретну пошту!"
            return false
        } else {
            return true
        }
    }

    private fun validateUsername(input: String): Boolean {
        if (input == null) {
            usernameEditText.error = "Введіть ім'я користувача!"
            return false
        } else if (input.length < 2){
            usernameEditText.error = "Ім'я меньше 2 символів, додайте ще!"
            return false
        } else {
            return true
        }
    }

    private fun validatePassword(input: String): Boolean {
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
        val emailValidation = validateEmail(emailEditText.text.toString())
        val usernameValidation = validateUsername(usernameEditText.text.toString())
        val genderValidation = (gendersRadioGroup.checkedRadioButtonId != -1)
        val birthDateValidation = selectedDate != null
        val passwordValidation = validatePassword(passwordEditText.text.toString());
        return emailValidation && usernameValidation && genderValidation && passwordValidation && birthDateValidation
    }
}