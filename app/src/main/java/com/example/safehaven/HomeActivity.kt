package com.example.safehaven

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.example.safehaven.EmployeeInfo
import android.os.Bundle
import com.example.safehaven.R
import android.content.Intent
import com.example.safehaven.GetHelpActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class HomeActivity : AppCompatActivity() {

    lateinit var employeeNameEdt: EditText
    lateinit var employeePhoneEdt: EditText
    lateinit var employeeAddressEdt: EditText
    lateinit var sendDatabtn: Button
    lateinit var btn_sos: Button
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var employeeInfo: EmployeeInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        employeeNameEdt = findViewById(R.id.idEdtEmployeeName)
        employeePhoneEdt = findViewById(R.id.idEdtEmployeePhoneNumber)
        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference("EmployeeInfo")
        employeeInfo = EmployeeInfo()
        sendDatabtn = findViewById(R.id.idBtnSendData)
        btn_sos = findViewById(R.id.btn_sos)

        btn_sos!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    GetHelpActivity::class.java
                )
            )
        })
        sendDatabtn!!.setOnClickListener(View.OnClickListener {
            // getting text from our edittext fields.
            val name = employeeNameEdt.getText().toString()
            val phone = employeePhoneEdt.getText().toString()
            val address = employeeAddressEdt.getText().toString()

            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {

                Toast.makeText(this@HomeActivity, "Please add some data.", Toast.LENGTH_SHORT)
                    .show()
            } else {

                addDatatoFirebase(name, phone, address)
            }
        })
    }

    private fun addDatatoFirebase(name: String, phone: String, address: String) {

        employeeInfo!!.employeeName = name
        employeeInfo!!.employeeContactNumber = phone
        employeeInfo!!.employeeAddress = address


        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                databaseReference!!.setValue(employeeInfo)

                Toast.makeText(this@HomeActivity, "data added", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@HomeActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}