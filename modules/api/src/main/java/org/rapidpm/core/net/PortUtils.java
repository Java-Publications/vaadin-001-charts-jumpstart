/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.rapidpm.core.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

public class PortUtils {

  public int defaultRestPort() {
    return 7081;
  }

  public int defaultServletPort() {
    return 7080;
  }

  //TODO static ??
  public int nextFreePortForTest() {
    int counter = 0;
    final Random random = new Random();
    while (counter < 1_00) {
      try {
        final int port = 1024 + (random.nextInt(65535 - 2048));
        new ServerSocket(port).close();
        return port;
      } catch (IOException ex) {
        counter = counter + 1;
      }
    }
    // if the program gets here, no port in the range was found
    throw new RuntimeException("no free port found");
  }

  public boolean isPortAvailable(int port) {
    try {
      new ServerSocket(port).close();
    } catch (IOException e) {
      // port is used
      return false;
    }
    // port is unused
    return true;
  }

}