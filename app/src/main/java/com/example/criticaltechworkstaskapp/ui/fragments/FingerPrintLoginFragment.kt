package com.example.criticaltechworkstaskapp.ui.fragments

import android.content.Intent
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.criticaltechworkstaskapp.common.Constants.REQUEST_CODE
import com.example.criticaltechworkstaskapp.common.showToast
import com.example.criticaltechworkstaskapp.databinding.FragmentFingerPrintLoginBinding
import java.util.concurrent.Executor


class FingerPrintLoginFragment : Fragment() {

    private var _binding: FragmentFingerPrintLoginBinding? = null
    private val binding by lazy { _binding!! }
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFingerPrintLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDeviceHasBiometric()

        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor, object: BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                requireContext().showToast("Authentication Error $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                requireContext().showToast("FingerPrint Authenticated Successfully")
                val action = FingerPrintLoginFragmentDirections.actionFingerPrintLoginFragmentToTopHeadLinesFragment()
                Navigation.findNavController(requireView()).navigate(action)

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                requireContext().showToast("Authentication Failed")
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("FingerPrint Authentication Required")
            .setSubtitle("Enter your FingerPrint")
            .setNegativeButtonText("Go to News Headlines")
            .build()

        binding.btnAuthenticate.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }


    fun checkDeviceHasBiometric(){
        val biometricManager = BiometricManager.from(requireContext())

        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)){

            BiometricManager.BIOMETRIC_SUCCESS->{
                requireContext().showToast("FingerPrint Biometric Supported")
                binding.btnAuthenticate.isEnabled=true

            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->{
                requireContext().showToast("FingerPrint Biometrics Not Supported")
                val action = FingerPrintLoginFragmentDirections.actionFingerPrintLoginFragmentToTopHeadLinesFragment()
                Navigation.findNavController(requireView()).navigate(action)

            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG or DEVICE_CREDENTIAL)

                }
                startActivityForResult(enrollIntent, REQUEST_CODE)
            }

        }
    }




}