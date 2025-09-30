package com.example.hw1_m4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1_m4.databinding.ActivityAccountDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountDetailBinding
    private val viewModel: AccountDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountId = intent.getStringExtra("key") ?:
        return

        viewModel.loadAccount(accountId)

        viewModel.account.observe(this) { account ->
            binding.tvInfo.text = """
                ID: ${account.id}
                Name: ${account.name}
                Balance: ${account.balance} ${account.currency}
                Active: ${account.isActive} 
                """.trimIndent()
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteAccount(accountId)
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val currentText = binding.tvInfo.text.toString()
            binding.tvInfo.text = "$currentText\n(Изменено пользователем)"
        }

    }
}
