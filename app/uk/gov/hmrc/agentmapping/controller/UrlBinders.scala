/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.agentmapping.controller

import play.api.mvc.PathBindable
import uk.gov.hmrc.agentmtdidentifiers.model.{Arn, Utr}
import uk.gov.hmrc.domain.SaAgentReference
import uk.gov.hmrc.play.binders.SimpleObjectBinder

object UrlBinders {
  implicit val utrBinder = new PathBindable[Utr] {
    override def bind(key: String, utrValue: String): Either[String, Utr] = Utr.isValid(utrValue) match {
      case true => Right(Utr(utrValue))
      case _ => Left(raw""""$utrValue" is not a valid UTR""")
    }

    override def unbind(key: String, utr: Utr): String = utr.value
  }

  implicit val arnBinder = new PathBindable[Arn] {
    override def bind(key: String, arnValue: String): Either[String, Arn] = Arn.isValid(arnValue) match {
      case true => Right(Arn(arnValue))
      case _ => Left(raw""""$arnValue" is not a valid ARN""")
    }

    override def unbind(key: String, arn: Arn): String = arn.value
  }
  implicit val saAgentReferenceBinder = new SimpleObjectBinder[SaAgentReference](SaAgentReference.apply, _.value)
}
