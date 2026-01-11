# TrollBoss

![License](https://img.shields.io/github/license/Minesuchtiiii/TrollBoss)
![Version](https://img.shields.io/badge/version-7.2-blue)
![Platform](https://img.shields.io/badge/platform-Paper%20%2F%20Spigot-green)

**TrollBoss** is a comprehensive, open-source trolling plugin for Minecraft servers, specifically designed for Paper and Spigot 1.21. It offers over 50 unique ways to prank and troll players, ranging from simple lightning bolts to complex alien abductions.

## 🚀 Features

- **Massive Troll Library:** Over 50 different troll commands and effects.
- **Intuitive GUI:** A user-friendly interface to execute trolls without memorizing commands.
- **Interactive Tutorial:** Built-in tutorial to help you get started with the plugin.
- **Troll Bows:** Specialized bows with unique effects like explosions, lightning, and more.
- **Statistics Tracking:** Keep track of how many times each troll has been used.
- **Highly Customizable:** Configure whether operators can be trolled and manage auto-updates.

## 🛠 Installation

1. Download the latest `TrollBoss.jar`.
2. Place the file into your server's `plugins` directory.
3. Restart or reload your server.
4. Enjoy trolling!

## 🎮 Commands

The primary command is `/troll`, which opens a GUI containing all available trolls.

### Key Commands:
- `/troll`: Opens the player selection GUI.
- `/troll <player>`: Opens the Troll-GUI for the specified player.
- `/troll help [page]`: Displays a list of all commands (6 pages available).
- `/trolltutorial`: Starts an interactive tutorial.
- `/trollop <true/false/status>`: Toggle whether operators can be trolled.

### Notable Trolls:
- `/abduct <player>`: Alien abduction sequence.
- `/herobrine <player>`: Transform into Herobrine.
- `/fakerestart <time>`: Simulate a server restart.
- `/burn`, `/freeze`, `/bolt`, `/boom`: Classic elemental trolls.
- `/shlong <player>`: A "unique" visual troll.

## 🔐 Permissions

- `troll.*`: Grants access to all TrollBoss features.
- `troll.gui`: Access to the main GUI.
- `troll.bypass`: Players with this permission cannot be trolled (if configured).
- *Individual permissions for each command follow the pattern `troll.<command_name>`.*

## ⚙️ Configuration

The `config.yml` allows you to customize basic plugin behavior:

```yaml
# Define if the plugin should automatically update
Auto-Update: true

# Define if operators can be trolled or not
Troll-Operators: true
```

## 🤝 Contributing

Contributions are welcome! Whether it's fixing a bug, adding a new troll, or improving the documentation, we appreciate your help.

### 🛠️ Getting Started

#### Prerequisites
- **Java Development Kit (JDK) 21** or higher.
- **Maven** for dependency management and building.
- An IDE (we recommend **IntelliJ IDEA**).

#### Setup
1. **Fork** the repository on GitHub.
2. **Clone** your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/TrollBoss.git
   ```
3. **Import** the project into your IDE as a Maven project.

### 🏗️ Building the Project

To build the plugin and generate the JAR file, run:
```bash
mvn clean package
```
The compiled JAR will be located in the `target/` directory.

### 🚀 Submitting Changes

1. **Create a Branch**: Create a new branch for your feature or bug fix.
   ```bash
   git checkout -b feature/your-feature-name
   ```
2. **Commit Your Changes**: Make sure your commit messages are clear and descriptive.
3. **Push to GitHub**: Push your branch to your fork.
   ```bash
   git push origin feature/your-feature-name
   ```
4. **Open a Pull Request**: Submit a PR to the main repository. Provide a clear description of what your changes do and why they are needed.

## 📄 License

This project is licensed under the **GPL-3.0 License**. See the [LICENSE](https://www.gnu.org/licenses/gpl-3.0.txt) for more details.

---
Developed with ❤️ by **Minesuchtiiii**
