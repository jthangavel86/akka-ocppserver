package services

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import models.HearBeat.{HearBeatRequest, HearBeatResponse}
import play.api.libs.json.{JsValue, Json}


object OCPPServiceActor {
  def props(out: ActorRef) = Props(new OCPPServiceActor(out))
}

class OCPPServiceActor(out: ActorRef) extends Actor {

  override def receive: Receive = {
    case msg: String if msg.contains("close") =>
      out ! s"Closing the connection as requested"
      self ! PoisonPill
    case msg: String =>
      out ! s"Echo, Received the message: ${msg}"

    case msg: JsValue => {
      val message = msg.as[HearBeatRequest]
      val responseMsg = HearBeatResponse(
        chargepointid = message.chargepointid,
        idtag = message.idtag,
        timestamp = new java.util.Date().getTime
      )
      out ! Json.toJson(responseMsg)
    }

  }

  override def postStop() {
    println("Closing the websocket connection.")
  }
}