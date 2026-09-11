package com.ratio.wallet.android.notification

import android.content.Context
import androidx.core.app.NotificationCompat

class RatioNotification(
    context: Context,
    val ratioChannel: RatioNotificationChannel
) : NotificationCompat.Builder(context, ratioChannel.channelId)
