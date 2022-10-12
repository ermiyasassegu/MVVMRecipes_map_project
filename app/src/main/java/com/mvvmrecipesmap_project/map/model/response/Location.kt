package com.mvvmrecipesmap_project.map.model.response

data class Location(
    var address: String?,
    var country: String?,
    var cross_street: String?,
    var formatted_address: String?,
    var locality: String?,
    var neighborhood: List<String>?,
    var postcode: String?,
    var region: String?
)