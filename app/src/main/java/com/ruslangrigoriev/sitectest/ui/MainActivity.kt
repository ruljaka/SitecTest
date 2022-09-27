package com.ruslangrigoriev.sitectest.ui

import android.Manifest.permission.READ_PHONE_STATE
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ruslangrigoriev.sitectest.R
import com.ruslangrigoriev.sitectest.data.saveIMEI
import com.ruslangrigoriev.sitectest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        val requestPermissionLauncher =
            registerForActivityResult(RequestPermission())
            { isGranted: Boolean ->
                if (isGranted) {
                    setupUI()
                } else {
                    showToast("Permissions required")
                }
            }
        if (ContextCompat.checkSelfPermission(this, READ_PHONE_STATE) == PERMISSION_GRANTED) {
            setupUI()
        } else {
            requestPermissionLauncher.launch(READ_PHONE_STATE)
        }
    }

    private fun setupUI() {
        getIMEI()
        viewModel.fetchUserList()
        setObservers()
        binding.btnEnter.setOnClickListener {
            viewModel.login(
                binding.usersSpinner.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
    }

    private fun setObservers() {
        viewModel.loading.observe(this) { showLoading(it) }
        viewModel.message.observe(this) { showToast(it) }
        viewModel.userNames.observe(this) {
            setUserList(it)
            binding.btnEnter.isEnabled = it.isNotEmpty()
        }
    }

    private fun setUserList(userNames: List<String>) {
        val usersAdapter = ArrayAdapter(this, R.layout.item_dropdown_menu, userNames)
        binding.usersSpinner.setText(userNames.first(), false)
        binding.usersSpinner.setAdapter(usersAdapter)
    }

    private fun getIMEI() {
        val imei = if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM) &&
            (VERSION.SDK_INT < VERSION_CODES.Q)
        ) {
            val telephonyManager =
                getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.imei
        } else {
            UUID.randomUUID().toString()
        }
        saveIMEI(imei)
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}