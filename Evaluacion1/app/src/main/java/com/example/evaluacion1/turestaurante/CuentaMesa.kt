package com.example.evaluacion1.turestaurante

class CuentaMesa(
    private val mesa: Int,
    val aceptaPropina: Boolean = true
) {
    private val items: MutableList<ItemMesa> = mutableListOf()

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        items.add(ItemMesa(itemMenu, cantidad))
    }

    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calculaTotalSinPropina(): Int {
        return items.sumOf { it.calcularSubTotal() }
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) (calculaTotalSinPropina() * 0.1).toInt() else 0
    }

    fun calcularTotalConPropina(): Int {
        return calculaTotalSinPropina() + calcularPropina()
    }
}
