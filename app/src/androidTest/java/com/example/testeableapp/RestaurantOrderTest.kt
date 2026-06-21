package com.example.testeableapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.testeableapp.model.MenuData
import org.junit.Rule
import org.junit.Test

class RestaurantOrderTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()



    @Test
    fun mensajeDePedidoVacioVisibleAlInicio() {
        composeTestRule.onNodeWithTag("emptyOrderMessage").assertIsDisplayed()
    }

    @Test
    fun todosLosItemsDelMenuVisibles() {
        // Se hace un scroll mínimo si la pantalla es pequeña para encontrarlos
        MenuData.items.forEach { item ->
            composeTestRule.onNodeWithTag("menuItem_${item.id}").assertExists()
        }
    }

    @Test
    fun elTotalGeneralSeActualiza() {
        // Hacemos clic en agregar el primer elemento (precio 5.50)
        composeTestRule.onNodeWithTag("addButton_1").performClick()
        // El texto de la etiqueta del total debe actualizarse a 5.50 €
        composeTestRule.onNodeWithTag("totalValue").assertTextEquals("5.50 €")
    }



    @Test
    fun mensajeDeVacioDesapareceAlAgregarItem() {
        composeTestRule.onNodeWithTag("emptyOrderMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("addButton_1").performClick()
        // Tras agregar el producto, el mensaje indicando que está vacío ya no debe existir
        composeTestRule.onNodeWithTag("emptyOrderMessage").assertDoesNotExist()
    }

    @Test
    fun dialogoDeConfirmacionApareceAlRealizarPedido() {
        composeTestRule.onNodeWithTag("addButton_1").performClick()
        composeTestRule.onNodeWithTag("placeOrderButton").performClick()
        // El cuadro de diálogo de confirmación debe mostrarse en pantalla
        composeTestRule.onNodeWithTag("confirmationDialog").assertIsDisplayed()
    }
}