
package com.example.envelopebudget

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.envelopebudget.data.*
import kotlinx.coroutines.launch

class EnvelopeViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    val envelopes = db.envelopeDao().getAllEnvelopes()

    fun addIncome(env: EnvelopeEntity, amount: Double) {
        viewModelScope.launch {
            db.envelopeDao().updateEnvelope(env.copy(balance = env.balance + amount))
        }
    }

    fun addExpense(env: EnvelopeEntity, amount: Double) {
        viewModelScope.launch {
            db.envelopeDao().updateEnvelope(env.copy(balance = env.balance - amount))
        }
    }
}
