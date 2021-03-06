/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package singleround

import akka.actor.ActorSystem

case object Start

object s {

  def main(args: Array[String]) {
    val system = ActorSystem("SingleRound")
    val a = system.actorOf(Node.props(1), name = "1")
    val b = system.actorOf(Node.props(2), name = "2")
    val c = system.actorOf(Node.props(3), name = "3")
 
    // neighbors
    a ! b

    b ! c

    c ! a
    c ! b

    // balls 

    a ! Ball(1)
    a ! Ball(2)
    a ! Ball(3)

    b ! Ball(4)
    b ! Ball(5)

    c ! Ball(6)
    c ! Ball(7)
    c ! Ball(8)
 
    // start snapshot procedure

    a ! Start
    b ! Start
    c ! Start
    
  }

}
