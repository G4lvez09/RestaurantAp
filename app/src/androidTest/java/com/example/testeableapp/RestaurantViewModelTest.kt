package com.example.testeableapp

import org.junit.Assert.*
import org.junit.Test

class RestaurantViewModelTest {



    @Test
    fun agregarItemAlPedido() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)
        val currentQty = viewModel.quantities.value[1]
        assertEquals(1, currentQty)
    }

    @Test
    fun incrementarYDecrementarCantidad() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)

        viewModel.incrementItem(1)
        assertEquals(2, viewModel.quantities.value[1])

        viewModel.decrementItem(1)
        assertEquals(1, viewModel.quantities.value[1])
    }

    @Test
    fun eliminarItemAlDecrementarDesde1() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)
        viewModel.decrementItem(1)
        val exists = viewModel.quantities.value.containsKey(1)
        assertFalse("El item debería ser eliminado del mapa al decrementar desde 1", exists)
    }

    @Test
    fun calculoDelTotalAPagar() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)
        val total = viewModel.total.value
        assertEquals(5.50, total, 0.01)
    }



    @Test
    fun generarConfirmacionAlRealizarPedido() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)
        viewModel.placeOrder()
        assertNotNull("El objeto de confirmación no debe ser nulo al realizar pedido", viewModel.confirmation.value)
    }

    @Test
    fun limpiarPedidoAlDescartarConfirmacion() {
        val viewModel = RestaurantViewModel()
        viewModel.addItem(1)
        viewModel.placeOrder()
        viewModel.dismissConfirmation()

        assertTrue("Las cantidades deben estar vacías al limpiar", viewModel.quantities.value.isEmpty())
        assertNull("La confirmación debe volver a ser nula", viewModel.confirmation.value)
    }
}