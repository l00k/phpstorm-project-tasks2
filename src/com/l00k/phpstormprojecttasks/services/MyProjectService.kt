package com.l00k.phpstormprojecttasks.services

import com.intellij.openapi.project.Project
import com.l00k.phpstormprojecttasks.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
