package com.example.velo_a_nantes.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo_a_nantes.models.Pump
import com.example.velo_a_nantes.models.Station

class DashboardViewModel : ViewModel() {

    private val _pumps = MutableLiveData<List<Pump>>().apply {
        value = ArrayList()
    }
    val pumps: MutableLiveData<List<Pump>> = _pumps
}