package com.ticketo.kotlincalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // This is for the decimal point
    var lastNumeric: Boolean = false;
    var lastDot: Boolean = false;

    // Initializing before declaring it in the onCreate
    lateinit var txtViewInput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txtViewInput = findViewById(R.id.tvInput)

    }
    fun onDigit(view: View){
        // Displaying any no. we click on
        txtViewInput.append((view as Button).text)    // Append works by displaying input thats inside brackets
        lastNumeric = true;
        }

    fun onClear(view: View){
        // It will add clear function to the clear button....we did not need to find the id of the clear btn cause the onClear function is declared in the clr button only.
        txtViewInput.text = "";
        var lastNumeric = false;
        var lastDot = false;
    }

    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            txtViewInput.append(".")
            lastNumeric = false;
            lastDot = true;
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = txtViewInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }


                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    // 99-1
                    var one = splitValue[0];  //99
                    var two = splitValue[1];  // 1

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    txtViewInput.text = removeZeroAfterString((one.toDouble() - two.toDouble()).toString());
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    // 99-1
                    var one = splitValue[0];  //99
                    var two = splitValue[1];  // 1

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    txtViewInput.text = removeZeroAfterString((one.toDouble() / two.toDouble()).toString());
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    // 99-1
                    var one = splitValue[0];  //99
                    var two = splitValue[1];  // 1

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    txtViewInput.text = removeZeroAfterString((one.toDouble() + two.toDouble()).toString());
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    // 99-1
                    var one = splitValue[0];  //99
                    var two = splitValue[1];  // 1

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    txtViewInput.text = removeZeroAfterString((one.toDouble() * two.toDouble()).toString());
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterString(result: String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(txtViewInput.text.toString()))
            txtViewInput.append((view as Button).text)
            lastNumeric = false;
            lastDot = false;
    }

    private fun isOperatorAdded(value: String) :Boolean{
        return  if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}