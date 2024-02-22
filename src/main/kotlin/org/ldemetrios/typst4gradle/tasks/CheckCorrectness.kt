//package org.ldemetrios.typst4gradle.tasks
//
//import org.ldemetrios.typst4gradle.Typst4GradleConfigurationExtension
//import java.nio.file.Files
//
//fun t4gCheckCorrectness(config: Typst4GradleConfigurationExtension) {
//    val srcFolder = config.srcFolderProp.get()
//    val outFolder = config.outFolderProp.get()
//    val themesFolder = config.themesFolderProp.get()
//    val themeDispatcherFile = config.themeDispatcherFileProp.get()
//    val stylingFile = config.stylingFileProp.get()
//    //  Nothing lies in outFolder
//    for ((file, name, dir) in
//    listOf(
//        Triple(srcFolder, "srcFolder", true),
//        Triple(themesFolder, "themesFolder", true),
//        Triple(themeDispatcherFile, "themeDispatcherFile", false),
//        Triple(stylingFile, "stylingFile", false),
//    )) {
//        require(!file.isSubfileOf(outFolder)) {
//            "$name must not be a sub${if (dir) "directory" else "file"} of an outFolder"
//        }
//    }
//
//    require(srcFolder.toRealPath() != stylingFile.toRealPath()) {
//        "srcFolder and stylingFile must be different"
//    }
//
//    require(srcFolder.toRealPath() != themeDispatcherFile.toRealPath()) {
//        "srcFolder and themeDispatcherFile must be different"
//    }
//
//    require(themesFolder.toRealPath() != stylingFile.toRealPath()) {
//        "themesFolder and stylingFile must be different"
//    }
//
//    require(themesFolder.toRealPath() != themeDispatcherFile.toRealPath()) {
//        "themesFolder and themeDispatcherFile must be different"
//    }
//
//    require(
//        themeDispatcherFile.toRealPath() != stylingFile.toRealPath() &&
//                !Files.isSameFile(themeDispatcherFile, stylingFile)
//    ) {
//        "stylingFile and themeDispatcherFile must be different"
//    }
//}