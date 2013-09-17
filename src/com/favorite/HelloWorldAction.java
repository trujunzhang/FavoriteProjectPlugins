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


import com.intellij.ide.actions.QuickSwitchSchemeAction;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.VcsBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class HelloWorldAction extends QuickSwitchSchemeAction {

    @Override
    protected boolean isEnabled() {
        return true;
    }

//    public void actionPerformed(AnActionEvent event) {
//        //Project project = event.getData(PlatformDataKeys.PROJECT);
//
//        ///Volumes/SHARE/MacSystem/Home/Users/djzhang/NetBeansProjects/intellijPlugDemo
//        String dir = ProjectUtil.getBaseDir();
//
//        //Messages.showMessageDialog("Hello World!:" + dir, "Information:" + project, Messages.getInformationIcon());
//
//        String path = "/Volumes/SHARE/MacSystem/Home/Users/djzhang/NetBeansProjects/intellijPlugDemo";
//        //final Project project = PlatformDataKeys.PROJECT.getData(e.getDataContext());
//        Project project = event.getData(PlatformDataKeys.PROJECT);
//        ProjectUtil.openOrImport(path, project, false);
//
//
//    }


    @Override
    protected void fillActions(Project project, @NotNull DefaultActionGroup defaultActionGroup, @NotNull DataContext dataContext) {
        fillVcsPopup(project, defaultActionGroup, dataContext, null);
    }


    private void fillVcsPopup(@NotNull final Project project,
                              @NotNull final DefaultActionGroup group,
                              @Nullable final DataContext dataContext,
                              @Nullable final AbstractVcs vcs) {


        // general list
        fillGeneralVcsPopup(project, group, dataContext, vcs);
    }

    private void fillGeneralVcsPopup(@NotNull final Project project,
                                     @NotNull final DefaultActionGroup group,
                                     @Nullable final DataContext dataContext,
                                     @Nullable final AbstractVcs vcs) {


        // basic operations
//        addSeparator(group, vcs != null ? vcs.getDisplayName() : null);
//        addAction("ChangesView.AddUnversioned", group);
//        addAction("CheckinProject", group);
//        addAction("CheckinFiles", group);
//        addAction(IdeActions.CHANGES_VIEW_ROLLBACK, group);

        // history and compare
//        addSeparator(group);
//        addAction("Vcs.ShowTabbedFileHistory", group);
//        addAction("Annotate", group);
//        addAction("Compare.SameVersion", group);


        // additional stuff
        addSeparator(group);
        addAction("MoveToChangeList", group);

        // local history
        addLocalHistoryActions(group);
    }


    private void addLocalHistoryActions(DefaultActionGroup group) {
        addSeparator(group, VcsBundle.message("vcs.quicklist.pupup.section.local.history"));

        //addAction("LocalHistory.ShowHistory", group);
        addAction("Actions.ActionsPlugin.OpenProject", group);
    }

    private void addActions(@NotNull final List<AnAction> actions,
                            @NotNull final DefaultActionGroup toGroup) {
        for (AnAction action : actions) {
            toGroup.addAction(action);
        }
    }

    private void addAction(final String actionId, final DefaultActionGroup toGroup) {
        final AnAction action = ActionManager.getInstance().getAction(actionId);

        // add action to group if it is available
        if (action != null) {
            toGroup.add(action);
        }
    }

    private void addSeparator(final DefaultActionGroup toGroup) {
        addSeparator(toGroup, null);
    }

    private void addSeparator(final DefaultActionGroup toGroup, @Nullable final String title) {
        final Separator separator = title == null ? new Separator() : new Separator(title);
        toGroup.add(separator);
    }

    protected String getPopupTitle(AnActionEvent e) {
        return VcsBundle.message("vcs.quicklist.popup.title");
    }

}
