/*
 * Copyright 2000-2005 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.favorite;


import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;


public class OpenProjectAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        //Project project = event.getData(PlatformDataKeys.PROJECT);

        ///Volumes/SHARE/MacSystem/Home/Users/djzhang/NetBeansProjects/intellijPlugDemo
        String dir = ProjectUtil.getBaseDir();

        //Messages.showMessageDialog("Hello World!:" + dir, "Information:" + project, Messages.getInformationIcon());

        String path = "/Volumes/SHARE/MacSystem/Home/Users/djzhang/NetBeansProjects/intellijPlugDemo";
        //final Project project = PlatformDataKeys.PROJECT.getData(e.getDataContext());
        Project project = event.getData(PlatformDataKeys.PROJECT);
        ProjectUtil.openOrImport(path, project, false);
    }



}
