package com.example.velo_a_nantes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.models.*
import com.example.velo_a_nantes.ui.dashboard.PumpMapsActivity
import com.example.velo_a_nantes.ui.home.StationMapsActivity

class PumpAdapter (private val pumps:List<Pump>, private val context: Context) :
    RecyclerView.Adapter<PumpAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardView : CardView = itemView.findViewById(R.id.cardViewPump)
        val prixValeur : TextView = itemView.findViewById(R.id.prixValeur)
        val address : TextView = itemView.findViewById(R.id.address)
        val statusPump : ImageView = itemView.findViewById(R.id.statusPump)
        val available : TextView = itemView.findViewById(R.id.prixNom)
        val distancePump : TextView = itemView.findViewById(R.id.distancePump)
    }
    // ici on charge le cardview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item,parent, false)
        return ViewHolder(view)
    }
    // action realiser pour chaque pump
    // on met a jour les composans de la vue
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pump = pumps[position]
        holder.prixValeur.text = pump.prixValeur.toString()

        if(currentLocation != null){
            holder.distancePump.text = "${String.format("%.2f", currentLocation!!.distanceTo(pump.toLocation())/1000)}KM"
        }else{
            holder.distancePump.text = "Géolocalisation désactivée."
        }

        holder.address.text = pump.adresse as CharSequence?
        holder.available.text =  "- \uD83D\uDCE2 ${pump.prixNom} -"

        if(0.0 != pump.prixValeur){
            holder.statusPump.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        }else{
            holder.statusPump.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }


        holder.cardView.setOnClickListener {
            val intent = Intent(context, PumpMapsActivity::class.java)
            intent.putExtra("pump", pump.prixValeur.toString())
            pumpSelected = pump
            context.startActivity(intent)
        }
    }

    // nb element afficher dans le recycerView
    override fun getItemCount(): Int {
        return pumps.size
    }
}