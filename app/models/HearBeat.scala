package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

object HearBeat {

  case class HearBeatRequest(chargepointid: String, idtag: String)

  case class HearBeatResponse(chargepointid: String, idtag: String, timestamp: Long)


  implicit val hearBeatRequestWrites = new Writes[HearBeatRequest] {
    override def writes(writeObj: HearBeatRequest): JsValue = Json.obj(
      "charepointid" -> writeObj.chargepointid,
      "idtag" -> writeObj.idtag
    )
  }

  implicit val hearBeatRequestReads: Reads[HearBeatRequest] = (
    (JsPath \ "charepointid").read[String] and
      (JsPath \ "idtag").read[String]
    ) (HearBeatRequest.apply _)

  implicit val hearBeatResponseWrites = new Writes[HearBeatResponse] {
    override def writes(readObj: HearBeatResponse): JsValue = Json.obj(
      "charepointid" -> readObj.chargepointid,
      "idtag" -> readObj.idtag,
      "timestamp" -> readObj.timestamp
    )
  }


}
