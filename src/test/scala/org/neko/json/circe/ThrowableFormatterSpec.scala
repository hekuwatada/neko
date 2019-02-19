package org.neko.json.circe

import io.circe.Encoder
import org.scalatest.{FunSpec, Matchers}

class ThrowableFormatterSpec extends FunSpec with Matchers {
  describe("encode") {
    import io.circe.syntax._

    implicit val throwableEncoder: Encoder[Throwable] = ThrowableEncoder

    it("encodes Throwable as Json") {
      val throwable: Throwable = new RuntimeException("test error")
      throwable.asJson.noSpaces shouldBe """{"message":"test error","class":"java.lang.RuntimeException","stacktrace":["org.neko.json.circe.ThrowableFormatterSpec.$anonfun$new$2(ThrowableFormatterSpec.scala:13)","org.scalatest.OutcomeOf.outcomeOf(OutcomeOf.scala:85)","org.scalatest.OutcomeOf.outcomeOf$(OutcomeOf.scala:83)","org.scalatest.OutcomeOf$.outcomeOf(OutcomeOf.scala:104)","org.scalatest.Transformer.apply(Transformer.scala:22)","org.scalatest.Transformer.apply(Transformer.scala:20)","org.scalatest.FunSpecLike$$anon$1.apply(FunSpecLike.scala:454)","org.scalatest.TestSuite.withFixture(TestSuite.scala:196)","org.scalatest.TestSuite.withFixture$(TestSuite.scala:195)","org.scalatest.FunSpec.withFixture(FunSpec.scala:1630)","org.scalatest.FunSpecLike.invokeWithFixture$1(FunSpecLike.scala:452)","org.scalatest.FunSpecLike.$anonfun$runTest$1(FunSpecLike.scala:464)","org.scalatest.SuperEngine.runTestImpl(Engine.scala:289)","org.scalatest.FunSpecLike.runTest(FunSpecLike.scala:464)","org.scalatest.FunSpecLike.runTest$(FunSpecLike.scala:446)","org.scalatest.FunSpec.runTest(FunSpec.scala:1630)","org.scalatest.FunSpecLike.$anonfun$runTests$1(FunSpecLike.scala:497)","org.scalatest.SuperEngine.$anonfun$runTestsInBranch$1(Engine.scala:396)","scala.collection.immutable.List.foreach(List.scala:392)","org.scalatest.SuperEngine.traverseSubNodes$1(Engine.scala:384)","org.scalatest.SuperEngine.runTestsInBranch(Engine.scala:373)","org.scalatest.SuperEngine.$anonfun$runTestsInBranch$1(Engine.scala:410)","scala.collection.immutable.List.foreach(List.scala:392)","org.scalatest.SuperEngine.traverseSubNodes$1(Engine.scala:384)","org.scalatest.SuperEngine.runTestsInBranch(Engine.scala:379)","org.scalatest.SuperEngine.runTestsImpl(Engine.scala:461)","org.scalatest.FunSpecLike.runTests(FunSpecLike.scala:497)","org.scalatest.FunSpecLike.runTests$(FunSpecLike.scala:496)","org.scalatest.FunSpec.runTests(FunSpec.scala:1630)","org.scalatest.Suite.run(Suite.scala:1147)","org.scalatest.Suite.run$(Suite.scala:1129)","org.scalatest.FunSpec.org$scalatest$FunSpecLike$$super$run(FunSpec.scala:1630)","org.scalatest.FunSpecLike.$anonfun$run$1(FunSpecLike.scala:501)","org.scalatest.SuperEngine.runImpl(Engine.scala:521)","org.scalatest.FunSpecLike.run(FunSpecLike.scala:501)","org.scalatest.FunSpecLike.run$(FunSpecLike.scala:500)","org.scalatest.FunSpec.run(FunSpec.scala:1630)","org.scalatest.tools.SuiteRunner.run(SuiteRunner.scala:45)","org.scalatest.tools.Runner$.$anonfun$doRunRunRunDaDoRunRun$13(Runner.scala:1346)","org.scalatest.tools.Runner$.$anonfun$doRunRunRunDaDoRunRun$13$adapted(Runner.scala:1340)","scala.collection.immutable.List.foreach(List.scala:392)","org.scalatest.tools.Runner$.doRunRunRunDaDoRunRun(Runner.scala:1340)","org.scalatest.tools.Runner$.$anonfun$runOptionallyWithPassFailReporter$24(Runner.scala:1031)","org.scalatest.tools.Runner$.$anonfun$runOptionallyWithPassFailReporter$24$adapted(Runner.scala:1010)","org.scalatest.tools.Runner$.withClassLoaderAndDispatchReporter(Runner.scala:1506)","org.scalatest.tools.Runner$.runOptionallyWithPassFailReporter(Runner.scala:1010)","org.scalatest.tools.Runner$.run(Runner.scala:850)","org.scalatest.tools.Runner.run(Runner.scala)","org.jetbrains.plugins.scala.testingSupport.scalaTest.ScalaTestRunner.runScalaTest2(ScalaTestRunner.java:131)","org.jetbrains.plugins.scala.testingSupport.scalaTest.ScalaTestRunner.main(ScalaTestRunner.java:28)"]}"""
    }
  }

  describe("decode") {
    pending
  }
}
