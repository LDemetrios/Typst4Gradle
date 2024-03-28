# Typst4Gradle* — gradle plugin for Typst projects

&ast; Draft name

## Examples

This plugin allows you to automatically compile typst files within your project in different themes. 

```kotlin
typst4gradle {
    executable = "typst" // Default
    // sourceFolder = "src" 
    // outputFolder = "out" 
    themes {
        // folder = "sty"
        default = "dev"
        theme("dev") {
            background = Color.black.t
            foreground = Color.white.t
            page.height = TAuto
        }
        theme("dark") {
            background = Color(0x21, 0x21, 0x21).t
            foreground = Color(0xf0, 0xf0, 0xf0).t
        }
        theme("regular") {
            background = Color.white.t
            foreground = Color.black.t
        }
        theme("sepia") {
            background = Color(0xeb, 0xd5, 0xb3).t
            foreground = Color.black.t
        }
    }

    compile {
        format {
            "$theme/$path/$file.$format"
        }
        ignore(Regex(".*header.typ"))
    }
}
```

Download third party libraries:

```kotlin
typst4gradle {
    libraries {
        library {
            url = "https://github.com/LDemetrios/ldemetrios-typst-commons.git"
            version = "0.1.0"
            alias = "ldemetrios-commons"
            onChange = OnChange.DISCARD
        }
        preview("cetz:0.2.2", asModule = true)
        preview("tablex:0.0.8")
    }
}
```

This will download a library from github repository and generate file `lib/all.typ`:

```typst
#import "ldemetrios-commons/0.1.0/src/lib.typ": *

#import "@preview/cetz:0.2.2"
#import "@preview/tablex:0.0.8": *
```

... And that's all yet. 

Be careful: there might be not tested edge cases. Besides that, error handling in library downloading is very drafty.  

Manual is coming.

## Installation & Usage

Plugin relies on [Typst4k](https://github.com/LDemetrios/Typst4k), you need to install it first

Plugin is not published in gradle portal, therefore you have to install it locally:

```bash
git clone https://github.com/LDemetrios/Typst4Gradle.git
cd Typst4Gradle
gradle publish
```

Then in your project, in `settings.gradle.kts` write:

```kotlin
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
```

then in `build.gradle.kts`:

```kotlin
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("org.ldemetrios:typst4gradle:0.3.0")
    }
}

apply<org.ldemetrios.typst4gradle.Typst4GradlePlugin>()
```

## Changelog

See [file](Changelog.md)

## Plans

- [x] Add support of downloading third-party libraries
- [ ] Make theme customization more straightforward

## Contacts
If you experience bugs or have proposal for improvements, feel free to open issues.
PRs are also welcome, feel free to ask questions about internal structure of the project.

tg: @LDemetrios
