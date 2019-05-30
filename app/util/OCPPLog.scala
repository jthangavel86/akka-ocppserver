package util

import akka.event.slf4j.Logger
import org.slf4j.LoggerFactory

object OCPPLog {
  val log = LoggerFactory.getILoggerFactory.getLogger("OCPPLogger")

  def connected(chargePointId : String): Unit = log.debug("Connection Established")


}
