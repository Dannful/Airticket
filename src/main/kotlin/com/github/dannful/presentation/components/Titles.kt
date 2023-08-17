package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ColumnScope.Titles(state: UInt) {
    when (state) {
        0U -> Text(
            text = "Insira a origem:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        1U -> Text(
            text = "Insira o destino:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        2U -> Text(
            text = "Insira o mês de ida:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        3U -> Text(
            text = "Insira o dia de ida:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        4U -> Text(
            text = "Somente ida ou ida e volta?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        5U -> Text(
            text = "Insira o mês de volta:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        6U -> Text(
            text = "Insira o dia de volta:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        7U -> Text(
            text = "Quantos passagens adultas?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        8U -> Text(
            text = "Há menores de idade ou somente maiores de idade?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        9U -> Text(
            text = "Insira a quantidade de menores de idade:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        10U -> Text(
            text = "Há menores de idade 2 anos ou menos ou somente maiores de 2 anos?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        11U -> Text(
            text = "Insira a quantidade de pessoas com 2 anos ou menos de idade:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        12U -> Text(
            text = "Qual o tipo de passagem?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        13U -> Text(
            text = "Qual a companhia aérea?",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        14U -> Text(
            text = "Proceder para pagamento.",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        15U -> Text(
            text = "Insira o número do seu cartão de crédito:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        16U -> Text(
            text = "Insira o mês de expiração do seu cartão:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        17U -> Text(
            text = "Insira o ano de expiração do seu cartão:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        18U -> Text(
            text = "Insira o código de segurança do seu cartão:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        19U -> Text(
            text = "Insira o nome do proprietário, conforme mostrado no cartão:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        20U -> Text(
            text = "Voilà! Compra finalizada :)",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}