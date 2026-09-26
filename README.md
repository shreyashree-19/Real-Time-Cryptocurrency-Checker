# Real-Time-Cryptocurrency-Checker

A Java-based real-time cryptocurrency monitoring application that integrates the CoinGecko API to display live USD/INR prices, market capitalization, 24-hour price changes, daily profit/loss, and 7-day price trends with interactive Chart.js visualizations.

---

## Features

- **Live Market Pricing:** Fetch real-time cryptocurrency valuation metrics in both USD and INR.
- **Market Capitalization:** View total market cap stats for over 60 supported digital assets.
- **Performance & Profit Tracking:** Automatically calculates and breaks down 24-hour price movements into daily profit or loss percentages.
- **Interactive Visualizations:** Renders dynamic 7-day price history graphs using Chart.js via CDN.
- **Geographical Origin Insights:** Includes custom tracking metadata for the developmental roots and origins of various cryptocurrencies.
- **Automated Execution:** Written in Java, the application handles file generation programmatically to launch the UI dashboard straight into your web browser.

---

## Technologies Used

- **Core Language:** Java (utilizing `java.awt.Desktop`, `java.io.File`, and `java.io.FileWriter`)
- **Frontend Dashboard:** HTML5, CSS3, and Vanilla JavaScript
- **Libraries & APIs:** 
  - Chart.js (Interactive data visualization)
  - CoinGecko REST API (Live public cryptocurrency data)

---

## How to Run

1. Clone or download this repository to your local machine.
2. Open the `Main.java` file inside your preferred Java development environment (such as VS Code, IntelliJ IDEA, or Eclipse).
3. Compile and execute the `main` method.
4. The program will automatically generate a `crypto.html` file in your directory and launch your interactive dashboard interface directly inside your default web browser.

---

## License

This project is open-source and available under the terms of the [MIT License](LICENSE).
