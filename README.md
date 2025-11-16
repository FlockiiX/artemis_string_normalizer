# Artemis String Normalizer

A small JetBrains plugin that converts placeholder-based strings like:

```java
"Hello <name>"
```

into valid Java concatenation expressions:
```java
"Hello " + name
```


### JetBrains Plugin Store:
https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/

## Usage
To normalize a string, simply append the marker:
```.artemis```
to any Java string literal.
When the plugin detects this marker, it automatically rewrites the string into a proper concatenation expression.

#### Example
Before:
`"User <name> has <points> points".artemis`

After:
`"User " + name + " has " + points + " points"`

This works anywhere in Java code — most commonly in:

`System.out.println(...)`

The transformation is immediate and removes the .artemis suffix automatically.

---
## Installation

Open Settings → Plugins → Marketplace

Search for Artemis String Normalizer

Click Install

Restart your IDE

Or install directly from the JetBrains Plugin Store:
https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/
---
## Features

Converts <variable> placeholders inside strings to Java expressions

"Hello <name>" → "Hello " + name

Supports multiple placeholders

"Math <x> + <y>" → "Math " + x + " + " + y

Replaces the special space symbol ⎵ with a normal space

Handles escaped characters correctly (\", \t, \n, \\, etc.)

Keeps all non-placeholder text exactly as-is

Allows variable names with spaces (sanitized):

"<Var iable>" → Variable

Never deletes unknown or invalid placeholders — leaves them untouched

Works with normal " strings, ' strings, and """ multi-line strings

---

###  Disclaimer
This plugin is an independent tool and is not affiliated with, endorsed by, or connected to Artemis or TUM.