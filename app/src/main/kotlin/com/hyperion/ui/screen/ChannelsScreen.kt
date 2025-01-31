package com.hyperion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hyperion.R
import com.hyperion.ui.component.BackButton
import com.hyperion.ui.component.ChannelCard
import com.hyperion.ui.viewmodel.ChannelsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChannelsScreen() {
    val viewModel: ChannelsViewModel = koinViewModel()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                navigationIcon = { BackButton() },
                title = {
                    Text(stringResource(R.string.channels))
                }
            )
        }
    ) { paddingValues ->
        val channels = viewModel.channels.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = channels.itemCount,
                key = channels.itemKey { it.id },
                contentType = channels.itemContentType()
            ) { index ->
                val channel = channels[index] ?: return@items

                ChannelCard(
                    modifier = Modifier.animateItemPlacement(),
                    channel = channel,
                    onLongClick = { },
                    onClickSubscribe = { }
                )
            }
        }
    }
}