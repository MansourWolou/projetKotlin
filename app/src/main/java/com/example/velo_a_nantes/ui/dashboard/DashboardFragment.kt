package com.example.velo_a_nantes.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo_a_nantes.adapter.PumpAdapter
import com.example.velo_a_nantes.api.PumpApi
import com.example.velo_a_nantes.api.RetrofitHelper
import com.example.velo_a_nantes.api.StationApi
import com.example.velo_a_nantes.databinding.FragmentDashboardBinding
import com.example.velo_a_nantes.models.allPumps
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerViewPump = binding.recyclerViewPump

        dashboardViewModel.pumps.observe(viewLifecycleOwner) {
            recyclerViewPump.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewPump.adapter = PumpAdapter(it, requireContext())
            recyclerViewPump.visibility = View.GONE

            allPumps = it
        }
        val pumpApi = RetrofitHelper().getInstance().create(PumpApi::class.java)
        GlobalScope.launch {
            val result = pumpApi.getPumps()
            Log.d("PUMPS", result.body().toString())
            dashboardViewModel.pumps.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}