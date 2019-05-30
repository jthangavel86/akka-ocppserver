package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.google.inject.Singleton
import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc.{AbstractController, ControllerComponents, WebSocket}
import services.OCPPServiceActor

/**
  * Chat Controller instance that handles the incoming chat requests with the server and provides a Echo chat service.
  *
  */
@Singleton
class OCPPController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      OCPPServiceActor.props(out)
    }
  }

  implicit val transformer = MessageFlowTransformer.jsonMessageFlowTransformer

  def heartbeat = WebSocket.accept[JsValue, JsValue] { request =>
    ActorFlow.actorRef { out =>
      OCPPServiceActor.props(out)
    }
  }
}