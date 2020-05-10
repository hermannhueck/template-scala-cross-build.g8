import sbt._

object ScalacOptions {

  lazy val scalacOptions213 = Seq(
    "-encoding",
    "UTF-8",                      // source files are in UTF-8
    "-deprecation",               // warn about use of deprecated APIs
    "-unchecked",                 // warn about unchecked type parameters
    "-feature",                   // warn about misused language features
    "-explaintypes",              // explain type errors in more detail
    "-opt-warnings",              // enable optimizer warnings
    "-opt:l:inline",              // enable inline optimizations ...
    "-opt-inline-from:<source>",  // ... from source files
    "-Xsource:2.13",              // Treat compiler input as Scala source for scala-2.13
    "-Xcheckinit",                // wrap field accessors to throw an exception on uninitialized access
    "-Xlint",                     // enable handy linter warnings
    "-Wconf:any:warning-verbose", // Configure reporting of compiler warnings; use `help` for details.
    "-Werror",                    // Fail the compilation if there are any warnings. // previously: -Xfatal-warnings
    "-Wdead-code",                // Warn when dead code is identified.
    "-Wextra-implicit",           // Warn when more than one implicit parameter section is defined.
    "-Wnumeric-widen",            // Warn when numerics are widened.
    "-Wself-implicit",            // Warn when an implicit resolves to an enclosing self-definition.
    "-Wunused:imports",           // Warn if an import selector is not referenced.
    "-Wunused:patvars",           // Warn if a variable bound in a pattern is unused.
    "-Wunused:privates",          // Warn if a private member is unused.
    "-Wunused:locals",            // Warn if a local definition is unused.
    "-Wunused:explicits",         // Warn if an explicit parameter is unused.
    "-Wunused:implicits",         // Warn if an implicit parameter is unused.
    "-Wunused:params",            // Enable -Wunused:explicits,implicits.
    "-Wunused:linted",            // -Xlint:unused.
    "-Wvalue-discard",            // Warn when non-Unit expression results are unused.
    // "-Woctal-literal",            // Warn on obsolete octal syntax.
    // "-Wunused:noWarn",            // Warn if a @nowarn annotation does not suppress any warnings.
    // "-Wmacros:<mode>",            // Enable lint warnings on macro expansions. Default: `before`, `help` to list choices.
    // "-Wconf:cat=unused-nowarn:silent",
    "-Ybackend-parallelism",
    "4",                                         // Enable paralellisation — change to desired number!
    "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
    "-Ycache-macro-class-loader:last-modified",  // and macro definitions. This can lead to performance improvements.
    "-Ymacro-annotations"                        // Enable support for macro annotations, formerly in macro paradise.
  )

  lazy val scalacOptions212 = Seq(
    "-encoding",
    "UTF-8",                     // source files are in UTF-8
    "-deprecation",              // warn about use of deprecated APIs
    "-unchecked",                // warn about unchecked type parameters
    "-feature",                  // warn about misused language features
    "-explaintypes",             // explain type errors in more detail
    "-opt-warnings",             // enable optimizer warnings
    "-opt:l:inline",             // enable inline optimizations ...
    "-opt-inline-from:<source>", // ... from source files
    "-language:higherKinds",     // (not required since scala 2.13.1) suppress warnings when using higher kinded types
    "-Xsource:2.12",             // Treat compiler input as Scala source for scala-2.12
    "-Xlint",                    // enable handy linter warnings
    "-Xcheckinit",               // wrap field accessors to throw an exception on uninitialized access
    "-Ypartial-unification",     // (removed in scala 2.13) allow the compiler to unify type constructors of different arities
    "-Yno-adapted-args",         // (only 2.12) Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver
    "-Ywarn-dead-code",          // Warn when dead code is identified.
    "-Ywarn-extra-implicit",     // Warn when more than one implicit parameter section is defined.
    "-Ywarn-numeric-widen",      // Warn when numerics are widened.
    "-Ywarn-unused:implicits",   // Warn if an implicit parameter is unused.
    "-Ywarn-unused:locals",      // Warn if a local definition is unused.
    "-Ywarn-unused:params",      // Warn if a value parameter is unused.
    "-Ywarn-unused:patvars",     // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",    // Warn if a private member is unused.
    "-Ywarn-value-discard",      // Warn when non-Unit expression results are unused.
    "-Ybackend-parallelism",
    "4",                                         // Enable paralellisation — change to desired number!
    "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
    "-Ycache-macro-class-loader:last-modified",  // and macro definitions. This can lead to performance improvements.
    "-Xsource:2.12",                             // Treat compiler input as Scala source for scala-2.12
    "-Yno-adapted-args",                         // (only 2.12) Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver
    "-Ypartial-unification",                     // (removed in scala 2.13) allow the compiler to unify type constructors of different arities
    "-language:higherKinds",                     // (not required since scala 2.13.1) suppress warnings when using higher kinded types
    "-Xlint"                                     // enable handy linter warnings
  )

  def scalacOptionsFor(scalaVersion: String): Seq[String] = {
    println(s"\n>>>>>          compiling for Scala \$scalaVersion\n")
    CrossVersion.partialVersion(scalaVersion) match {
      case Some((2, minor)) if minor >= 13 =>
        scalacOptions213
      case _ =>
        scalacOptions212
    }
  }

  def removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions: Seq[String]): Seq[String] =
    scalacOptions.map {
      case "-Xlint" => "-Xlint:-unused,_"
      case other    => other
    }
}
