package com.example.core.data

interface AbstractMapper<DATA, DOMAIN> {
    fun toDomainLayer(data: DATA): DOMAIN

    fun toDataLayer(domain: DOMAIN): DATA
}