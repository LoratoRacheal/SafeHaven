package com.example.safehaven

import android.widget.EditText

class EmployeeInfo  // an empty constructor is
// required when using
// Firebase Realtime Database.
{
    // created getter and setter methods
    // for all our variables.
    // string variable for
    // storing employee name.
    lateinit var employeeName: String

    // string variable for storing
    // employee contact number
    lateinit var employeeContactNumber: String

    // string variable for storing
    // employee address.
    lateinit var employeeAddress: String
}