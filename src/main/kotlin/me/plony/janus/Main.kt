package me.plony.janus

//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//import me.plony.janus.models.Jsep
//import me.plony.janus.videoRoomModels.JoinAs
//import kotlin.time.Duration.Companion.minutes
//
//suspend fun main(args: Array<String>) {
//    val janus = Janus("http://localhost:8088/janus")
//    janus.initializeSession()
//    janus.events
//        .onEach {
//            println(it)
//        }.launchIn(janus.scope)
//    val plugin = VideoRoomPlugin(janus.attach("janus.plugin.videoroom"))
//    val roomId = plugin.createRoom()
//    plugin.join(roomId, JoinAs.Publisher, "test")
//    plugin.joinedEvent.onEach { println("Joined $it") }.launchIn(janus.scope)
//    plugin.publish(jsep = Jsep(
//        "offer",
//        "v=0\r\no=- 20518 0 IN IP4 0.0.0.0\r\ns=-\t\r\nt=0 0\r\na=group:BUNDLE audio video\r\na=group:LS audio video\r\na=ice-options:trickle\r\na=ice-options:ice2\r\n\r\nm=audio 54609 UDP/TLS/RTP/SAVPF 109 0 8\r\nc=IN IP4 203.0.113.141\r\na=mid:audio\r\na=msid:ma ta\r\na=sendrecv\r\na=rtpmap:109 opus/48000/2\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=maxptime:120\r\na=ice-ufrag:074c6550\r\na=ice-pwd:a28a397a4c3f31747d1ee3474af08a068\r\na=fingerprint:sha-256 19:E2:1C:3B:4B:9F:81:E6:B8:5C:F4:A5:A8:D8:73:04:BB:05:2F:70:9F:04:A9:0E:05:E9:26:33:E8:70:88:A2\r\na=setup:actpass\r\na=tls-id:89J2LRATQ3ULA24G9AHWVR31VJWSLB68\r\na=rtcp-mux\r\na=rtcp-mux-only\r\na=rtcp-rsize\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=extmap:2 urn:ietf:params:rtp-hdrext:sdes:mid\r\na=candidate:0 1 UDP 2122194687 192.0.2.4 61665 typ host\r\na=candidate:1 1 UDP 1685987071 203.0.113.141 54609 typ srflx raddr 192.0.2.4 rport 61665\r\na=end-of-candidates\r\n\r\nm=video 0 UDP/TLS/RTP/SAVPF 99 120\r\nc=IN IP4 203.0.113.141\r\na=bundle-only\r\na=mid:video\r\n\r\na=sendrecv\r\na=rtpmap:99 H264/90000\r\na=fmtp:99 profile-level-id=4d0028;packetization-mode=1\r\na=rtpmap:120 VP8/90000\r\na=rtcp-fb:99 nack\r\na=rtcp-fb:99 nack pli\r\na=rtcp-fb:99 ccm fir\r\na=rtcp-fb:120 nack\r\na=rtcp-fb:120 nack pli\r\na=rtcp-fb:120 ccm fir\r\na=extmap:2 urn:ietf:params:rtp-hdrext:sdes:mid"
//    ))
//    delay(1.minutes)
//    plugin.destroyRoom(roomId)
//    janus.destroy()
//}