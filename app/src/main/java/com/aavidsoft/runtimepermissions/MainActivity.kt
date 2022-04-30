package com.aavidsoft.runtimepermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.aavidsoft.runtimepermissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetOTP.setOnClickListener {

            var permissionStatus = ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            )
            if(permissionStatus == PackageManager.PERMISSION_GRANTED) {
                readOTP()
            }
            else {
                //request permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.READ_SMS,
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    1
                )
            }
        }

    }

    private fun readOTP() {
        binding.edtOTP.setText("123456")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readOTP()
        }
        else {
            Toast.makeText(this, "Unable to read OTP.. Please enter manually.", Toast.LENGTH_LONG).show()
        }
        if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Read contacts - Granted", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "Read contacts - Not Granted", Toast.LENGTH_LONG).show()
        }

        if(grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Read Storage - Granted", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "Read Storage - Not Granted", Toast.LENGTH_LONG).show()
        }
    }
}