package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.screen.search.state.SearchUiState
import com.example.hotel.ui.theme.White
import com.example.hotel.ui.theme.textForthColor
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun FilterBottomSheet(
    state: SearchUiState,
    onSelectCountryChange: (String) -> Unit,
    onSelectSortChange: (String) -> Unit,
    onSelectRateChange: (String) -> Unit,
    onSelectFacilityChange: (String) -> Unit,
    onApplyClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.filter_hotel),
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.textPrimaryColor,
                textAlign = TextAlign.Center
            )
        )

        Divider()

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = stringResource(id = R.string.country),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.countries) { chip ->
                    CustomChip(
                        selected = chip == state.selectedCountry,
                        text = chip,
                        onSelectedChange = { onSelectCountryChange(chip) }
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = stringResource(id = R.string.sort_results),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.sortTypes) { chip ->
                    CustomChip(
                        selected = chip == state.selectedSort,
                        text = chip,
                        onSelectedChange = { onSelectSortChange(chip) }
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = stringResource(id = R.string.star_rating),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.ratings) { chip ->
                    CustomChip(
                        selected = chip == state.selectedRate,
                        text = chip,
                        onSelectedChange = { onSelectRateChange(chip) }
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(id = R.string.facilities),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.facilities) { facility ->
                    CustomCheckbox(
                        title = facility,
                        value = state.selectedFacility.contains(facility),
                        onValueChange = { onSelectFacilityChange(facility) }
                    )
                }
            }

        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomButton(
                modifier = Modifier.fillMaxWidth(0.5f),
                title = stringResource(id = R.string.cancel),
                color = MaterialTheme.colors.secondary,
                textColor = MaterialTheme.colors.textForthColor,
                onClick = onCancelClick
            )
            CustomButton(
                title = stringResource(id = R.string.apply_filter),
                color = MaterialTheme.colors.primary,
                textColor = White,
                onClick = onApplyClick
            )
        }

    }
}