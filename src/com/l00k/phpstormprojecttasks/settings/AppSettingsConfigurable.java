// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.l00k.phpstormprojecttasks.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class AppSettingsConfigurable implements Configurable {

  private com.l00k.phpstormprojecttasks.settings.AppSettingsComponent mySettingsComponent;

  // A default constructor with no arguments is required because this implementation
  // is registered as an applicationConfigurable EP

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "SDK: Application Settings Example";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !mySettingsComponent.getProjectOpenTaskPathText().equals(settings.projectOpenTaskPath);
    modified |= !mySettingsComponent.getProjectOpenTaskOptionsText().equals(settings.projectOpenTaskOptions);
    modified |= !mySettingsComponent.getProjectCloseTaskPathText().equals(settings.projectCloseTaskPath);
    modified |= !mySettingsComponent.getProjectCloseTaskOptionsText().equals(settings.projectCloseTaskOptions);
    return modified;
  }

  @Override
  public void apply() {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.projectOpenTaskPath = mySettingsComponent.getProjectOpenTaskPathText();
    settings.projectOpenTaskOptions= mySettingsComponent.getProjectOpenTaskOptionsText();
    settings.projectCloseTaskPath = mySettingsComponent.getProjectCloseTaskPathText();
    settings.projectCloseTaskOptions = mySettingsComponent.getProjectCloseTaskOptionsText();
  }

  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    mySettingsComponent.setProjectOpenTaskPathText(settings.projectOpenTaskPath);
    mySettingsComponent.setProjectOpenTaskOptionsText(settings.projectOpenTaskOptions);
    mySettingsComponent.setProjectCloseTaskPathText(settings.projectCloseTaskPath);
    mySettingsComponent.setProjectCloseTaskOptionsText(settings.projectCloseTaskOptions);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}
