/*
 * Copyright (C) 2017 Dremio Corporation
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
package com.dremio.exec.planner;

import org.mockito.Mockito;

import com.dremio.common.config.SabotConfig;
import com.dremio.common.config.LogicalPlanPersistence;
import com.dremio.common.scanner.persistence.ScanResult;
import com.dremio.exec.planner.PhysicalPlanReader;
import com.dremio.exec.proto.CoordinationProtos;
import com.dremio.exec.server.SabotContext;
import com.dremio.exec.store.StoragePluginRegistry;

public class PhysicalPlanReaderTestFactory {

  public static LogicalPlanPersistence defaultLogicalPlanPersistence(SabotConfig c, ScanResult scanResult) {
    return new LogicalPlanPersistence(c, scanResult);
  }

  public static PhysicalPlanReader defaultPhysicalPlanReader(
      SabotConfig c,
      ScanResult scanResult, StoragePluginRegistry storageRegistry) {
    return new PhysicalPlanReader(
        c, scanResult, new LogicalPlanPersistence(c, scanResult),
        CoordinationProtos.NodeEndpoint.getDefaultInstance(),
        storageRegistry, Mockito.mock(SabotContext.class));
  }
  public static PhysicalPlanReader defaultPhysicalPlanReader(SabotConfig c, ScanResult scanResult) {
    return defaultPhysicalPlanReader(c, scanResult, null);
  }

  public static PhysicalPlanReader defaultPhysicalPlanReader(SabotContext c) {
    return defaultPhysicalPlanReader(c, null);
  }

  public static PhysicalPlanReader defaultPhysicalPlanReader(
      SabotContext c,
      StoragePluginRegistry storageRegistry) {
    return new PhysicalPlanReader(
        c.getConfig(),
        c.getClasspathScan(),
        c.getLpPersistence(),
        CoordinationProtos.NodeEndpoint.getDefaultInstance(),
        storageRegistry,
        Mockito.mock(SabotContext.class));
  }

}
