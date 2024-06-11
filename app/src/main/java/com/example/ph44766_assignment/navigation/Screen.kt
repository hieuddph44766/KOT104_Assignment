package com.example.ph44766_assignment.navigation


sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object CartScreen : Screen("cart")
    data object NotificationScreen : Screen("notification")
    data object PersonScreen : Screen("person")
    data object FavoriteScreen : Screen("favorite")
    data object PaymentMethodScreen : Screen("payMethod")
    data object AddPaymentMethod : Screen("addPayMethod")
    data object AddShippingAddress : Screen("addShippingAddress")
    data object ShippingAddressScreen : Screen("shippingAddressScreen")
    data object SuccessScreen : Screen("successScreen")
    data object MyOrderScreen : Screen("myOrderScreen")
    data object CheckOutScreen : Screen("checkOutScreen")
    data object CategoriesScreen : Screen("categoriesScreen")
}



