package com.example.jpc_address_validation_demo.data.model

data class KomootResult(
    val type: String = "",
    val features: List<Feature> = listOf()
)

data class Feature(
    val type: String = "",
    val properties: Property = Property()
)

data class Property(
    val name: String = "",
    val street: String = "",
    val housenumber: String = "",
    val city: String = "",
    val country: String = "",
    val postcode: String = "",
    val state: String = "",
    val countrycode: String = "",
    val osm_key: String = "",
    val osm_value: String = "",
    val osm_type: String = ""
)

/**
 * {
 *     "type": "FeatureCollection",
 *     "features": [
 *       {
 *         "type": "Feature",
 *         "geometry": {
 *           "coordinates": [
 *             13.438596,
 *             52.519854
 *           ],
 *           "type": "Point"
 *         },
 *         "properties": {
 *           "city": "Berlin",
 *           "country": "Germany",
 *           "name": "Berlin"
 *         }
 *       },{
 *       "type": "Feature",
 *         "geometry": {
 *           "coordinates": [
 *             61.195088,
 *             54.005826
 *           ],
 *           "type": "Point"
 *         },
 *         "properties": {
 *           "country": "Russia",
 *           "name": "Berlin",
 *           "postcode": "457130"
 *         }
 *       }
 *     ]
 *   }
 */