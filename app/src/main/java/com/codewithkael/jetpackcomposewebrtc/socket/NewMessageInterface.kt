package com.codewithkael.jetpackcomposewebrtc.socket

import com.codewithkael.jetpackcomposewebrtc.models.MessageModel

interface NewMessageInterface {
    fun onNewMessage(message: MessageModel)
}