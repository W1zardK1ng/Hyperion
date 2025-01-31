package com.hyperion.ui.component

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hyperion.R
import com.hyperion.domain.manager.AccountManager
import com.hyperion.ui.LocalNavController
import com.hyperion.ui.navigation.AppDestination
import com.zt.innertube.domain.model.DomainChannelPartial
import dev.olshevski.navigation.reimagined.navigate
import org.koin.compose.koinInject

@Composable
fun ChannelCard(
    channel: DomainChannelPartial,
    onLongClick: () -> Unit,
    onClickSubscribe: () -> Unit,
    modifier: Modifier = Modifier,
    accountManager: AccountManager = koinInject(),
) {
    val navController = LocalNavController.current

    ElevatedCard(
        modifier = modifier
            .clip(CardDefaults.elevatedShape)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    navController.navigate(AppDestination.Channel(channel.id))
                },
                onLongClick = onLongClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp),
                url = channel.avatarUrl!!,
                contentDescription = channel.name!!
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f, true),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = channel.name!!,
                    style = MaterialTheme.typography.titleMedium
                )

                channel.subscriptionsText?.let { subscriptionsText ->
                    Text(
                        text = subscriptionsText,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                channel.videoCountText?.let { videoCountText ->
                    Text(
                        text = videoCountText,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Button(
                enabled = accountManager.loggedIn,
                onClick = onClickSubscribe
            ) {
                Text(stringResource(R.string.subscribe))
            }
        }
    }
}