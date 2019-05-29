import helpers.Challenge

object TranscribingDNAToRNA extends App {

  Challenge("TranscribingDNAintoRNA.txt") provides { data =>

    println(data.mkString.replaceAll("T", "U"))

  }

}
