package com.l00k.phpstormprojecttasks.listeners

import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.notification.Notification
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.l00k.phpstormprojecttasks.services.MyProjectService
import com.l00k.phpstormprojecttasks.settings.AppSettingsState
import java.nio.charset.Charset
import java.util.*


internal class MyProjectManagerListener : ProjectManagerListener {

    var myConfigState: AppSettingsState = AppSettingsState.getInstance();

    private val NOTIFICATION_GROUP = NotificationGroup("Project tasks", NotificationDisplayType.BALLOON, true)

    fun notify(project: Project, content: String?) {
        val notification: Notification = NOTIFICATION_GROUP.createNotification(content!!, NotificationType.INFORMATION)
        notification.notify(project)
    }

    override fun projectOpened(project: Project) {
        project.getService(MyProjectService::class.java)

        if (!myConfigState.projectOpenTaskPath.isEmpty()) {
            notify(project,"Running project open tasks")

            val cmds = ArrayList<String>()
            cmds.add(myConfigState.projectOpenTaskPath)
            cmds.add(myConfigState.projectOpenTaskOptions)

            val generalCommandLine = GeneralCommandLine(cmds)
            generalCommandLine.setCharset(Charset.forName("UTF-8"))
            generalCommandLine.setWorkDirectory(project.basePath)

            try {
                val processHandler: ProcessHandler = OSProcessHandler(generalCommandLine)
                processHandler.startNotify()
            }
            catch (exception: ExecutionException) {
                var x = 1;
            }
        }
    }

    override fun projectClosed(project: Project) {

        if (!myConfigState.projectCloseTaskPath.isEmpty()) {
            notify(project, "Running project close tasks")

            val cmds = ArrayList<String>()
            cmds.add(myConfigState.projectCloseTaskPath)
            cmds.add(myConfigState.projectCloseTaskOptions)

            val generalCommandLine = GeneralCommandLine(cmds)
            generalCommandLine.setCharset(Charset.forName("UTF-8"))
            generalCommandLine.setWorkDirectory(project.basePath)

            try {
                val processHandler: ProcessHandler = OSProcessHandler(generalCommandLine)
                processHandler.startNotify()
            }
            catch (exception: ExecutionException) {
            }
        }
    }

}
