/*
 * Copyright 2017 Alessandro Falappa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.alexfalappa.nbspringboot.windows;

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

import com.github.alexfalappa.nbspringboot.projects.service.api.SpringBootService;

/**
 * Dashboard to control currently open Spring Boot projects.
 */
@ConvertAsProperties(
        dtd = "-//com.github.alexfalappa.nbspringboot.windows//BootDashboard//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "BootDashboardTopComponent",
        iconBase = "com/github/alexfalappa/nbspringboot/springboot-logo.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "bottomSide", openAtStartup = false)
@ActionID(category = "Window", id = "com.github.alexfalappa.nbspringboot.windows.BootDashboardTopComponent")
@ActionReference(path = "Menu/Window", position = 333)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_BootDashboardAction",
        preferredID = "BootDashboardTopComponent"
)
@Messages({
    "CTL_BootDashboardAction=Spring Boot Dashboard",
    "CTL_BootDashboardTopComponent=Spring Boot Dashboard",
    "HINT_BootDashboardTopComponent=This is a BootDashboard window"
})
public final class BootDashboardTopComponent extends TopComponent {

    public BootDashboardTopComponent() {
        initComponents();
        setName(Bundle.CTL_BootDashboardTopComponent());
        setToolTipText(Bundle.HINT_BootDashboardTopComponent());
    }

    /** This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(BootDashboardTopComponent.class, "BootDashboardTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(BootDashboardTopComponent.class, "BootDashboardTopComponent.jButton2.text")); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Spring Boot Apps");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Running");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("App1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("App2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stopped");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("App3");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setVisibleRowCount(6);
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        componentOpened();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        System.out.println("com.github.alexfalappa.nbspringboot.windows.BootDashboardTopComponent.componentOpened()");
        for (Project prj : OpenProjects.getDefault().getOpenProjects()) {
            final ProjectInformation prjInfo = ProjectUtils.getInformation(prj);
            System.out.format("%s [%s]%n", prjInfo.getDisplayName(), FileUtil.getFileDisplayName(prj.getProjectDirectory()));
            SpringBootService sbs = prj.getLookup().lookup(SpringBootService.class);
            if (sbs != null) {
                System.out.println("E' un progetto Spring Boot");
            }
        }
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
        System.out.println("com.github.alexfalappa.nbspringboot.windows.BootDashboardTopComponent.componentClosed()");
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
        System.out.println("com.github.alexfalappa.nbspringboot.windows.BootDashboardTopComponent.writeProperties()");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
        System.out.println("com.github.alexfalappa.nbspringboot.windows.BootDashboardTopComponent.readProperties()");
    }
}