/*
 * Copyright 2000-2009 JetBrains s.r.o.
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

/*
 * @author max
 */
package com.favorite;

import com.favorite.utils.ConfigureHelper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.openapi.vfs.local.CoreLocalVirtualFile;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShowFavoriteFilesAction extends FavoriteBaseShowRecentFilesAction {

    ///Volumes/SHARE/MacSystem/Home/Users/djzhang/
    private static final String FAVORITE_CONFIGURE_PATH = "/Dropbox/MacSystem/intellij idea/FavoriteProjectPluins/";

    protected VirtualFile[] filesToShow(Project project) {
        String userHome = System.getenv("HOME");

        StringBuilder xmlPathBuilder = new StringBuilder();
        xmlPathBuilder.append(userHome);
        xmlPathBuilder.append(FAVORITE_CONFIGURE_PATH);

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            xmlPathBuilder.append("mac");
        }
        xmlPathBuilder.append(".xml");
        return getChangedFiles(project, xmlPathBuilder.toString());
    }

    public VirtualFile[] getChangedFiles(Project project, String xmlPath) {
        List<VirtualFile> files = new ArrayList<VirtualFile>();

        final List<String> paths = ConfigureHelper.getPaths(xmlPath, project);

        paths.add(xmlPath);
        for (String path : paths) {
            File currentFile = new File(path);
            final VirtualFile file = new CoreLocalVirtualFile(new CoreLocalFileSystem(), currentFile);
            if (file != null) {
                files.add(file);
            }
        }

        return VfsUtil.toVirtualFileArray(files);
    }

    private List<String> getFavoriteProjects() {
        List<String> projects = new LinkedList<String>();
        projects.add("/Volumes/SHARE/MacSystem/Home/Users/djzhang/DevIntellijIdea/maven-web/springmvctomcat01");
        projects.add("/Volumes/SHARE/MacSystem/Home/Users/djzhang/DevIntellijIdea/maven-web/springmvcbossexample01");
        projects.add("/Volumes/SHARE/MacSystem/Home/Users/djzhang/DevIntellijIdea/openproject/github/OpenSources/ideaIC-129.354-0k/build.xml");
        return projects;
    }

    @Override
    protected String getPeerActionId() {
        return "RecentFiles";
    }

    protected String getTitle() {
        return "Recently Edited Files";
    }
}
