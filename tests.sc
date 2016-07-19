
object FileMatcher {

  def fileMatcher(matcher: String => Boolean) = {
    for (file <- new java.io.File(".").listFiles(); if matcher(file.getName))
      yield file
  }

  def fileEndWith(query: String) =
    fileMatcher(_.endsWith(query))
}

FileMatcher.fileEndWith("n")

def plainOldSum(x: Int)(y: Int) = x + y

def onePlus = plainOldSum(1)(_)

onePlus(4)