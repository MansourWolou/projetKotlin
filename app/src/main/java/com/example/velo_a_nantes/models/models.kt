package com.example.velo_a_nantes.models

import android.location.Location
import kotlinx.serialization.*

var currentLocation : Location? = null

var stationSelected : Station? = null
var allStations : List<Station>? = null

var allPumps : List<Pump>? = null
var pumpSelected : Pump? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val status: String,
    val recordId: String,
    val latitude: Double,
    val longitude: Double,
    val bikeStands: Long,
    val address: String,
    val availableBikes: Long,
    val availableBikeStands: Long
){
    fun toLocation(): Location{
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    fun showDetails(): CharSequence?{
        return "velos disponibles:${availableBikes} places libres:${availableBikeStands} total:${bikeStands}"
    }
}

data class Pump (
    val id: Long,
    val ville: String,
    val pop: String,
    val regName: String,
    val comArmCode: String,
    val depName: String,
    val prixNom: String,
    val comCode: String,
    val epciName: String,
    val depCode: String,
    val prixID: Any? = null,
    val servicesService: String,
    val horairesAutomate2424: Any? = null,
    val comArmName: String,
    val prixMaj: String,
    val pumpID: Any? = null,
    val regCode: String,
    val adresse: Any? = null,
    val latitude: Double,
    val longitude: Double,
    val epciCode: String,
    val cp: String,
    val prixValeur: Double,
    val comName: Any? = null,
    val recordID: String
){
    fun toLocation(): Location{
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    //fun showDetails(): CharSequence?{
    //    return "velos disponibles:${availableBikes} places libres:${availableBikeStands} total:${bikeStands}"
    //}
}