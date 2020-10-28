// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.l00k.phpstormprojecttasks.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.FormBuilder;
import org.jdesktop.swingx.prompt.PromptSupport;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {
  private Project project;
  private final JPanel myMainPanel;

  private TextFieldWithBrowseButton projectOpenTaskPathText;
  private JTextField projectOpenTaskOptionsText = new JTextField();

  private TextFieldWithBrowseButton projectCloseTaskPathText;
  private JTextField projectCloseTaskOptionsText = new JTextField();

  public AppSettingsComponent() {
    PromptSupport.setPrompt("Options", projectOpenTaskOptionsText);
    PromptSupport.setPrompt("Options", projectCloseTaskOptionsText);

    projectOpenTaskPathText = new TextFieldWithBrowseButton(
            new TextBrowseFolderListener(
                    FileChooserDescriptorFactory.createSingleFileDescriptor(),
                    project
            )
    );
    projectOpenTaskPathText.addBrowseFolderListener(
            "Search for task",
            null,
            project,
            FileChooserDescriptorFactory.createSingleFileDescriptor()
    );

    projectCloseTaskPathText = new TextFieldWithBrowseButton(
            new TextBrowseFolderListener(
                    FileChooserDescriptorFactory.createSingleFileDescriptor(),
                    project
            )
    );
    projectCloseTaskPathText.addBrowseFolderListener(
            "Search for task",
            null,
            project,
            FileChooserDescriptorFactory.createSingleFileDescriptor()
    );

    myMainPanel = FormBuilder.createFormBuilder()
            .addComponent(new JBLabel("Project open task"))
            .addComponent(projectOpenTaskPathText)
            .addComponent(projectOpenTaskOptionsText)
            .addComponent(new JBLabel("Project close task"))
            .addComponent(projectCloseTaskPathText)
            .addComponent(projectCloseTaskOptionsText)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return projectOpenTaskPathText;
  }

  @NotNull
  public String getProjectOpenTaskPathText() {
    return projectOpenTaskPathText.getText();
  }
  public void   setProjectOpenTaskPathText(@NotNull String text) {
    projectOpenTaskPathText.setText(text);
  }

  @NotNull
  public String getProjectOpenTaskOptionsText() {
    return projectOpenTaskOptionsText.getText();
  }
  public void   setProjectOpenTaskOptionsText(@NotNull String text) {
    projectOpenTaskOptionsText.setText(text);
  }

  @NotNull
  public String getProjectCloseTaskPathText() {
    return projectCloseTaskPathText.getText();
  }
  public void   setProjectCloseTaskPathText(@NotNull String text) { projectCloseTaskPathText.setText(text); }

  @NotNull
  public String getProjectCloseTaskOptionsText() {
    return projectCloseTaskOptionsText.getText();
  }
  public void   setProjectCloseTaskOptionsText(@NotNull String text) {
    projectCloseTaskOptionsText.setText(text);
  }
}
