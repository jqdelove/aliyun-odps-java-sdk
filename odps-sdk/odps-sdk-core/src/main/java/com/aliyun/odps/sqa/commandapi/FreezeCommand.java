/*
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
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.aliyun.odps.sqa.commandapi;

import java.util.Collections;
import java.util.Map;

import com.aliyun.odps.Odps;
import com.aliyun.odps.data.Record;

class FreezeCommand extends MergeTaskCommand {

  public FreezeCommand(String tableName, String partition) {
    super(tableName, partition);
  }

  @Override
  public boolean isSync() {
    return false;
  }

  @Override
  public RecordIter<Record> run(Odps odps, CommandInfo commandInfo) {
    String taskNamePrefix = "cold_storage_freeze_task_";
    Map<String, String>
        settingMap =
        Collections.singletonMap("odps.merge.cold.storage.mode", "backup");
    return mergeTaskRun(odps, commandInfo, taskNamePrefix, settingMap);
  }
}
