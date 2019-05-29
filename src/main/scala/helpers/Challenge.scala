package helpers

import scala.io.{BufferedSource, Source}

case class Challenge(filename: String) {

  def provides(block: BufferedSource => Any): Any = {

    val source = Source.fromResource(filename)

    try {
      block(source)
    } finally {
      source.close()
    }

  }

}
