package com.example.evaluacion1.turestaurante

class ItemMesa(val itemMenu: ItemMenu, val cantidad: Int) {
    fun calcularSubTotal():Int{
        return itemMenu.precio * cantidad

    }
}