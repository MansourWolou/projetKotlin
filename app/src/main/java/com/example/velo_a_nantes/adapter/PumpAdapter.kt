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
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById(R.id.availability)
        val distance : TextView = itemView.findViewById(R.id.distance)
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
        holder.name.text = pump.prixValeur.toString()

        if(currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(pump.toLocation())/1000)}KM"
        }else{
            holder.distance.text = "Géolocalisation désactivée."
        }

        holder.address.text = pump.adresse as CharSequence?
        holder.availability.text =  "- \uD83D\uDCE2 ${pump.prixNom} -"

        if(0.0 != pump.prixValeur){
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        }else{
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
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