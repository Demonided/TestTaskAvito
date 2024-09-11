package com.example.testtaskavito.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.testtaskavito.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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

    }

    // Инициализация необходимых переменных
    val passwordVisible = MutableLiveData(false)

    // Функция для обновления состояния кнопки регистрации
    private fun updateRegisterButtonState() {
        val isAllFieldsFilled = !binding.profileName.text.isNullOrEmpty() &&
                !binding.profileMail.text.isNullOrEmpty() &&
                !binding.profilePassword.text.isNullOrEmpty() &&
                !binding.profileConfirmPassword.text.isNullOrEmpty()

        val isPasswordsMatch = binding.profilePassword.text.toString() == binding.profileConfirmPassword.text.toString()

        // Отображение кнопки регистрации только если все поля заполнены и пароли совпадают
        binding.profileButtonRegistration.isEnabled = isAllFieldsFilled && isPasswordsMatch
    }

    // Функция для управления видимостью иконки видимости пароля
    private fun updatePasswordVisibilityIcon() {
        binding.profilePasswordVisibilityIcon.visibility = if (binding.profilePassword.text.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    // Настройка слушателей изменений текста
    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateRegisterButtonState()
                updatePasswordVisibilityIcon()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Добавление слушателей на все поля
        binding.profileName.addTextChangedListener(textWatcher)
        binding.profileMail.addTextChangedListener(textWatcher)
        binding.profilePassword.addTextChangedListener(textWatcher)
        binding.profileConfirmPassword.addTextChangedListener(textWatcher)
    }

    // Настройка кнопки видимости пароля
    private fun setupPasswordVisibilityToggle() {
        binding.profilePasswordVisibilityIcon.setOnClickListener {
            val isVisible = passwordVisible.value ?: false
            passwordVisible.value = !isVisible

            binding.profilePassword.transformationMethod = if (isVisible) {
                PasswordTransformationMethod.getInstance() // Отображение пароля в виде звездочек
            } else {
                HideReturnsTransformationMethod.getInstance() // Отображение пароля
            }

            // Перемещение курсора в конец текста
            binding.profilePassword.setSelection(binding.profilePassword.text.length)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}