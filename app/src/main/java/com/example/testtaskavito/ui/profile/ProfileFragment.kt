package com.example.testtaskavito.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var isLoginMode = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextWatchers()
        setupPasswordVisibilityToggle()
        setupButtonListeners()
        updateUI() // Инициализация интерфейса на основе начального состояния

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isLoginMode) {
                    switchToRegisterMode()  // Переход в режим регистрации при нажатии "Назад"
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed() // Закрываем фрагмент
                }
            }
        })
    }

    val passwordVisible = MutableLiveData(false)

    private fun setupButtonListeners() {
        // Обработчик кнопки "Назад" в Toolbar
        binding.profileBack.setNavigationOnClickListener {
            switchToRegisterMode() // Возвращаемся к режиму регистрации
        }

        // Обработчик кнопки "Регистрация"
        binding.profileButtonRegistration.setOnClickListener {
            if (isLoginMode) {
                switchToRegisterMode()
            } else {
                // Здесь можно добавить логику для регистрации пользователя
            }
        }

        // Обработчик кнопки "Войти"
        binding.profileButtonEntrance.setOnClickListener {
            if (isLoginMode) {
                // Здесь можно добавить логику для входа пользователя
            } else {
                switchToLoginMode()
            }
        }
    }

    private fun switchToLoginMode() {
        isLoginMode = true
        updateUI()
    }

    private fun switchToRegisterMode() {
        isLoginMode = false
        updateUI()
    }

    // Обновление UI на основе состояния
    private fun updateUI() {
        if (isLoginMode) {
            // Скрываем поля для имени и подтверждения пароля, показываем кнопку "Войти"
            binding.profileCardName.visibility = View.GONE
            binding.profileCardConfirmPassword.visibility = View.GONE
            binding.profileButtonRegistration.visibility = View.GONE
            binding.profileBack.visibility = View.VISIBLE // Показываем кнопку "Назад"
            binding.profileButtonEntrance.visibility = View.VISIBLE
            binding.profileButtonEntrance.text = "Войти"
        } else {
            // Показываем все поля и кнопку "Регистрация"
            binding.profileCardName.visibility = View.VISIBLE
            binding.profileCardConfirmPassword.visibility = View.VISIBLE
            binding.profileButtonRegistration.visibility = View.VISIBLE
            binding.profileButtonEntrance.visibility = View.VISIBLE
            binding.profileBack.visibility = View.GONE // Скрываем кнопку "Назад"
            binding.profileButtonEntrance.text = "Войти"
            binding.profileButtonRegistration.text = "Регистрация"
        }
    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                updateRegisterButtonState()
                updatePasswordVisibilityIcon()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateRegisterButtonState()
                updatePasswordVisibilityIcon()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.profileName.addTextChangedListener(textWatcher)
        binding.profileMail.addTextChangedListener(textWatcher)
        binding.profilePassword.addTextChangedListener(textWatcher)
        binding.profileConfirmPassword.addTextChangedListener(textWatcher)
    }

    private fun updateRegisterButtonState() {
        val isAllFieldsFilled = !binding.profileName.text.isNullOrEmpty() &&
                !binding.profileMail.text.isNullOrEmpty() &&
                !binding.profilePassword.text.isNullOrEmpty() &&
                !binding.profileConfirmPassword.text.isNullOrEmpty()

        val isPasswordsMatch = binding.profilePassword.text.toString() == binding.profileConfirmPassword.text.toString()

        binding.profileButtonRegistration.isEnabled = isAllFieldsFilled && isPasswordsMatch
    }

    private fun updatePasswordVisibilityIcon() {
        binding.profilePasswordVisibilityIcon.visibility = if (binding.profilePassword.text.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    private fun setupPasswordVisibilityToggle() {
        binding.profilePasswordVisibilityIcon.setOnClickListener {
            val isVisible = passwordVisible.value ?: false
            passwordVisible.value = !isVisible

            binding.profilePassword.transformationMethod = if (isVisible) {
                PasswordTransformationMethod.getInstance()
            } else {
                HideReturnsTransformationMethod.getInstance()
            }

            val iconRes = if (isVisible) {
                R.drawable.ic_visibility_off
            } else {
                R.drawable.ic_visibility_on
            }
            binding.profilePasswordVisibilityIcon.setImageResource(iconRes)
            binding.profilePassword.setSelection(binding.profilePassword.text.length)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}