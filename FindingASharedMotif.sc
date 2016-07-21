/*
This is a draft for Rosalind's "Finding a Shared Motif"
I wanted to use a custom impl. of a Generalised Suffix Tree (GST)
----
Very Un-Functional implementation of a GST.
Each node has a visited and an counter attribute, initially set to false and 0.
For each string of DNA, we feed all its suffixes to the tree.
If not yet visited, we ++ the counter and !visited.
After feeding a string of DNA, we prune all not visited Node,
  because those nodes are not substrings of the last DNA string.
We then reset the tree and feed suffixes for all DNA strings.
----
High use of mutability, and using two pre-order tree traversal for pruning
and resetting for each DNA string... The algorithm still executes in the given
time for the Rosalind challenge.
----
TODO: Refactoring in functional style.
*/

import scala.collection.mutable
import scala.io.Source

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/FindingASharedMotif.fasta"
val data_fh = Source.fromFile(data_fn)

// Comes from a old piece of code. Don't do this.
def buildlist(b: scala.io.BufferedSource): List[String] = {
  var tmpstr = ""
  var lst = List[String]()
  for (line <- b.getLines()) {
    if (line.matches("[ATGC]+")) {
      tmpstr = tmpstr + line
    }
    else {
      lst = tmpstr :: lst; tmpstr = ""
    }
  }
  (tmpstr :: lst).reverse.tail
}

// List of all DNA strings in the file
val list = buildlist(data_fh)

class Node {
  var visited: Boolean = false
  var counter: Int = 0
  var childs: mutable.Map[Char, Node] = mutable.Map[Char, Node]()
}

def feedSuffix(N: Node)(suf: String): Node = {
  if (!N.visited) {
    N.visited = true
    N.counter += 1
  }
  if (suf.length > 0) {
    if (N.childs.contains(suf.head)) {
      feedSuffix(N.childs(suf.head))(suf.tail)
      N
    }
    else {
      N.childs.update(suf.head, new Node())
      feedSuffix(N.childs(suf.head))(suf.tail)
      N
    }
  }
  else N
}

def resetTree(N: Node): Unit = {
  N.visited = false
  if (N.childs.nonEmpty) {
    for ((k, v) <- N.childs) {
      resetTree(v)
    }
  }
}

def pruneTree(N: Node): Unit = {
  if (N.childs.nonEmpty) {
    for ((k, v) <- N.childs) {
      if (!v.visited) N.childs -= k
      else pruneTree(v)
    }
  }
}

var root: Node = new Node()

// For each DNA string
for (l <- list) {
  // Make a list of all it's suffixes
  val suffixes = l.tails.toList.reverse.tail
  // Make sure all nodes are set du false
  resetTree(root)
  // Feed all suffixes to the tree
  for (s <- suffixes) {
    root = feedSuffix(root)(s)
  }
  // Prune un-visited nodes.
  // This step is essential for the mem.
  pruneTree(root)
}

// We are looking for a node visited N times, N being
// the number or DNA strings in file
val deepest: Int = list.length

// Pre-order traversal building a string based on path.
// Keep only the longest string for all N-time visited paths.
def findDeepestPath(N: Node): String = {
  var P: String = ""
  if (N.childs.nonEmpty) {
    for ((k, v) <- N.childs) {
      if (v.counter == deepest) {
        val P2 = k.toString + findDeepestPath(v)
        if (P2.length > P.length) P = P2
      }
    }
    P
  }
  else ""
}

val deep = findDeepestPath(root)


