import org.cosmin.GitflowReleasePlugin._

name := "example gitflow release"

organization := "org.cosmin"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-feature",  "-language:postfixOps")

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

bintrayVcsUrl := Some("git@github.com:cosmin-cojocar/example-gitflow-release.git")

val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo <<= (version) { version: String =>
    val repo = "http://oss.jfrog.org/artifactory/"
    val (name, url) = if (version.contains("-SNAPSHOT"))
      ("snapshots", repo + "oss-snapshot-local")
    else
      ("releases", repo + "oss-release-local")
    Some(Resolver.url(name, new URL(url))(Resolver.mavenStylePatterns))
  },
  bintrayReleaseOnPublish := false,
  credentials := List(Path.userHome / ".bintray" / ".artifactory").filter(_.exists).map(Credentials(_))
)

gitflowReleaseSettings