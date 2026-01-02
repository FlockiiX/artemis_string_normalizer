# Artemis String Normalizer

A small JetBrains plugin that converts placeholder-based strings like:

```java
"Hello <name>"
```

into valid Java code.

Depending on the postfix used, the plugin can generate either a **string concatenation expression** or a **printf statement**.

---

## Examples

### String concatenation (`.artemis`)

```java
"Hello <name>".artemis
```

⬇️

```java
"Hello " + name
```

### Printf output (`.artemisf`)

```java
"Hello <name>".artemisf
```

⬇️

```java
System.out.printf("Hello %s", name);
```

---

## JetBrains Plugin Store

[https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/](https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/)

---

## Usage

To normalize a string, append one of the following postfixes to a Java string literal:

* `.artemis` – converts the string into a Java string concatenation expression
* `.artemisf` – converts the string into a complete `System.out.printf(...)` statement

When the plugin detects the postfix, it immediately rewrites the string and removes the postfix automatically.

### Example

Before:

```java
"User <name> has <points> points".artemis
```

After:

```java
"User " + name + " has " + points + " points"
```

Or using printf:

```java
"User <name> has <points> points".artemisf
```

After:

```java
System.out.printf("User %s has %s points", name, points);
```

This works anywhere in Java code — most commonly in output statements like:

```java
System.out.println(...)
System.out.printf(...)
```

---

## Installation

1. Open **Settings → Plugins → Marketplace**
2. Search for **Artemis String Normalizer**
3. Click **Install**
4. Restart your IDE

Or install directly from the JetBrains Plugin Store:
[https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/](https://plugins.jetbrains.com/plugin/29010-artemis-string-normalizer/)

---

## Features

* Converts `<variable>` placeholders inside strings to Java code

    * `"Hello <name>"` → `"Hello " + name`
* Supports multiple placeholders

    * `"Math <x> + <y>"` → `"Math " + x + " + " + y`
* Generates `System.out.printf(...)` calls with `.artemisf`
* Replaces the special space symbol `⎵` with a normal space
* Handles escaped characters correctly (`\"`, `\t`, `\n`, `\\`, etc.)
* Preserves all non-placeholder text exactly as written
* Sanitizes variable names by removing spaces

    * `"<Var iable>"` → `Variable`
* Leaves invalid or unknown placeholders untouched
* Works with:

    * normal string literals (`"..."`)
    * single-quoted strings (`'...'`)
    * triple-quoted strings (`"""..."""`)
* Designed for use in Java (`.java` files)

---

## Disclaimer

This plugin is an independent tool and is not affiliated with, endorsed by, or connected to Artemis or TUM.