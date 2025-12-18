
package com.example.envelopebudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.envelopebudget.ui.EnvelopeAdapter

class MainActivity : ComponentActivity() {
    private val viewModel: EnvelopeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = EnvelopeAdapter(
            onIncome = { viewModel.addIncome(it, 500.0) },
            onExpense = { viewModel.addExpense(it, 200.0) }
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        viewModel.envelopes.observe(this) {
            adapter.submitList(it)
        }
    }
}
