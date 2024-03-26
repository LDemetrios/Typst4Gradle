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

... And that's all yet. See *Plans* if interested.  

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
        classpath("org.ldemetrios:typst4gradle:0.2.0")
    }
}

apply<org.ldemetrios.typst4gradle.Typst4GradlePlugin>()
```

## Changelog

See [file](Changelog.md)

## Plans

- [ ] Add support of downloading third-party libraries
- [ ] Make theme customization more straightforward
  See file


## Contacts
If you experience bugs or have proposal for improvements, feel free to open issues.
PRs are also welcome, feel free to ask questions about internal structure of the project.

tg: @LDemetrios
