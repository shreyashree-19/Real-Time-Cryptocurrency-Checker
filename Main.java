import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        String html
                = """
                  <!DOCTYPE html>
                  <html lang='en'>
                  <head>
                    <meta charset='UTF-8'>
                    <title>Real-Time Cryptocurrency Checker</title>
                    <script src='https://cdn.jsdelivr.net/npm/chart.js'></script>
                    <style>
                      body { margin:0; font-family:Segoe UI,Tahoma,Geneva,Verdana,sans-serif; background:#000000; color:#f1f5f9; padding:20px; }
                      h2 { text-align:center; font-size:28px; margin-bottom:20px; color:#38bdf8; }
                      label { font-size:16px; margin-right:10px; }
                      select,button { padding:8px 12px; border-radius:6px; border:none; margin-right:10px; font-size:14px; }
                      select { background:#111111; color:#f1f5f9; }
                      button { background:#38bdf8; color:#000000; cursor:pointer; font-weight:bold; }
                      button:hover { background:#0ea5e9; }
                      #output { margin-top:20px; font-size:16px; }
                      #dailyChanges { margin-top:20px; font-size:14px; }
                      .increase { color:#22c55e; }
                      .decrease { color:#ef4444; }
                      canvas { margin-top:30px; background:#111111; border-radius:8px; padding:10px; }
                    </style>
                  </head>
                  <body>
                    <h2>Real-Time Cryptocurrency Checker</h2>
                    <label>Select Cryptocurrency:</label>
                    <select id='coin'>
                   <option value='bitcoin'>Bitcoin (BTC)</option>
                   <option value='ethereum'>Ethereum (ETH)</option>
                   <option value='binancecoin'>BNB</option>
                   <option value='ripple'>XRP</option>
                   <option value='solana'>Solana (SOL)</option>
                   <option value='cardano'>Cardano (ADA)</option>
                   <option value='dogecoin'>Dogecoin (DOGE)</option>
                   <option value='polkadot'>Polkadot (DOT)</option>
                   <option value='litecoin'>Litecoin (LTC)</option>
                   <option value='tron'>TRON (TRX)</option>
                   <option value='avalanche-2'>Avalanche (AVAX)</option>
                   <option value='chainlink'>Chainlink (LINK)</option>
                   <option value='stellar'>Stellar (XLM)</option>
                   <option value='monero'>Monero (XMR)</option>
                   <option value='uniswap'>Uniswap (UNI)</option>
                   <option value='cosmos'>Cosmos (ATOM)</option>
                   <option value='near'>NEAR Protocol (NEAR)</option>
                   <option value='aptos'>Aptos (APT)</option>
                   <option value='arbitrum'>Arbitrum (ARB)</option>
                   <option value='the-graph'>The Graph (GRT)</option>
                   <option value='algorand'>Algorand (ALGO)</option>
                   <option value='vechain'>VeChain (VET)</option>
                  <option value='filecoin'>Filecoin (FIL)</option>
                   <option value='internet-computer'>Internet Computer (ICP)</option>
                   <option value='lido-dao'>Lido DAO (LDO)</option>
                   <option value='aave'>Aave (AAVE)</option>
                   <option value='maker'>Maker (MKR)</option>
                   <option value='rocket-pool'>Rocket Pool (RPL)</option>
                   <option value='optimism'>Optimism (OP)</option>
                   <option value='sui'>Sui (SUI)</option>
                   <option value='fantom'>Fantom (FTM)</option>
                   <option value='thorchain'>THORChain (RUNE)</option>
                   <option value='injective'>Injective (INJ)</option>
                   <option value='curve-dao-token'>Curve DAO (CRV)</option>
                   <option value='pancakeswap-token'>PancakeSwap (CAKE)</option>
                   <option value='hedera'>Hedera (HBAR)</option>
                   <option value='tezos'>Tezos (XTZ)</option>
                   <option value='eos'>EOS</option>
                   <option value='gala'>Gala (GALA)</option>
                   <option value='flow'>Flow (FLOW)</option>
                   <option value='immutable-x'>Immutable X (IMX)</option>
                   <option value='render-token'>Render (RNDR)</option>
                   <option value='axie-infinity'>Axie Infinity (AXS)</option>
                  <option value='sandbox'>Sandbox (SAND)</option>
                   <option value='decentraland'>Decentraland (MANA)</option>
                   <option value='chiliz'>Chiliz (CHZ)</option>
                   <option value='enjincoin'>Enjin Coin (ENJ)</option>
                   <option value='zcash'>Zcash (ZEC)</option>
                   <option value='dash'>Dash (DASH)</option>
                   <option value='neo'>Neo (NEO)</option>
                   <option value='iota'>IOTA (MIOTA)</option>
                   <option value='waves'>Waves (WAVES)</option>
                   <option value='kaspa'>Kaspa (KAS)</option>
                   <option value='mina-protocol'>Mina Protocol (MINA)</option>
                   <option value='conflux-token'>Conflux (CFX)</option>
                   <option value='multiversx'>MultiversX (EGLD)</option>
                   <option value='nexo'>Nexo (NEXO)</option>
                   <option value='huobi-token'>Huobi Token (HT)</option>
                   <option value='okb'>OKB</option>
                   <option value='kucoin-shares'>KuCoin (KCS)</option>
                   <option value='bitget-token'>Bitget Token (BGB)</option>
                   <option value='tether'>Tether (USDT)</option>
                   <option value='usd-coin'>USD Coin (USDC)</option>
                   <option value='dai'>Dai (DAI)</option>
                   <option value='true-usd'>TrueUSD (TUSD)</option>
                   <option value='frax'>Frax (FRAX)</option>
                   </select>
                  <button onclick='loadData()'>Check Price</button>
                  
                    <div id='output'></div>
                    <div id='dailyChanges'></div>
                    <canvas id='chart' width='600' height='300'></canvas>
                  
                  <script>
                  const origins = {
                   'bitcoin': 'United States (California, early dev in Japan)',
                   'ethereum': 'Canada (Toronto) / Switzerland (Zug)',
                   'binancecoin': 'China (Shanghai) / Malta',
                   'ripple': 'United States (California)',
                   'solana': 'United States (California)',
                   'cardano': 'United States (Wyoming)',
                   'dogecoin': 'United States (Oregon)',
                   'polkadot': 'Switzerland (Zug)',
                   'litecoin': 'United States (Massachusetts)',
                   'tron': 'China (Beijing) / Singapore',
                   'avalanche-2': 'United States (New York)',
                   'chainlink': 'United States (California)',
                   'stellar': 'United States (California)',
                   'monero': 'Global / Europe roots',
                   'uniswap': 'United States (New York)',
                   'cosmos': 'United States (California)',
                   'near': 'United States (California)',
                   'aptos': 'United States (California)',
                   'arbitrum': 'United States (New York)',
                   'the-graph': 'United States (California)',
                   'algorand': 'United States (Massachusetts)',
                   'vechain': 'China (Shanghai) / Singapore',
                   'filecoin': 'United States (California)',
                   'internet-computer': 'United States (California)',
                   'lido-dao': 'United States (New York)',
                   'aave': 'Switzerland (Zug)',
                   'maker': 'Denmark (Copenhagen)',
                   'rocket-pool': 'Australia',
                   'optimism': 'United States (New York)',
                   'sui': 'United States (California)',
                   'fantom': 'South Korea',
                   'thorchain': 'Norway',
                   'injective': 'United States (California)',
                   'curve-dao-token': 'Switzerland (Zug)',
                   'pancakeswap-token': 'Singapore',
                   'hedera': 'United States (Texas)',
                   'tezos': 'France (Paris)',
                   'eos': 'United States (Virginia)',
                   'gala': 'United States (California)',
                   'flow': 'Canada (Vancouver)',
                   'immutable-x': 'Australia (Sydney)',
                   'render-token': 'United States (California)',
                   'axie-infinity': 'Vietnam (Ho Chi Minh City)',
                   'sandbox': 'Hong Kong',
                   'decentraland': 'Argentina (Buenos Aires)',
                   'chiliz': 'Malta',
                   'enjincoin': 'Singapore',
                   'zcash': 'United States (Colorado)',
                   'dash': 'United States (Arizona)',
                   'neo': 'China (Shanghai)',
                   'iota': 'Germany (Berlin)',
                   'waves': 'Russia (Moscow)',
                   'kaspa': 'Israel',
                   'mina-protocol': 'United States (California)',
                   'conflux-token': 'China (Beijing)',
                   'multiversx': 'Romania',
                   'nexo': 'Bulgaria (Sofia)',
                   'huobi-token': 'China (Beijing)',
                   'okb': 'China (Beijing)',
                   'kucoin-shares': 'Singapore',
                   'bitget-token': 'Singapore',
                   'tether': 'Hong Kong',
                   'usd-coin': 'United States (Boston)',
                   'dai': 'United States (California)',
                   'true-usd': 'United States (California)',
                   'frax': 'United States (New York)'
                  };
                  
                  let chartInstance = null;
                  
                  function formatDateOnly(d){
                    const year = d.getFullYear();
                    const month = String(d.getMonth() + 1).padStart(2, '0');
                    const day = String(d.getDate()).padStart(2, '0');
                    return year + '-' + month + '-' + day;
                  }
                  
                  async function loadData(){
                    const coin = document.getElementById('coin').value;
                    const url = 'https://api.coingecko.com/api/v3/coins/' + coin + '?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=true';
                    const res = await fetch(url);
                    const data = await res.json();
                  
                    const priceUSD = data.market_data.current_price.usd;
                    const priceINR = data.market_data.current_price.inr;
                    const capUSD = data.market_data.market_cap.usd;
                    const capINR = data.market_data.market_cap.inr;
                    const change = data.market_data.price_change_percentage_24h;
                  
                    document.getElementById('output').innerHTML =
                      '<p><b>Price (USD):</b> $' + priceUSD + '<br>' +
                      '<b>Price (INR):</b> \u20b9' + priceINR + '<br>' +
                      '<b>Market Cap (USD):</b> $' + capUSD + '<br>' +
                      '<b>Market Cap (INR):</b> \u20b9' + capINR + '<br>' +
                      '<b>24h Change:</b> ' + change.toFixed(2) + '%<br>' +
                      '<b>Origin:</b> ' + (origins[coin] || 'Unknown') + '</p>';
                  
                    const prices = data.market_data.sparkline_7d.price;
                  
                    const labels = [];
                    const dailyPrices = [];
                    const now = new Date();
                    for (let i = 6; i >= 0; i--) {
                      const d = new Date(now);
                      d.setDate(now.getDate() - i);
                      labels.push(formatDateOnly(d));
                    }
                  
                    const N = prices.length;
                    if (N >= 7) {
                      for (let i = 1; i <= 7; i++) {
                        const idx = Math.floor(N * i / 7) - 1;
                        dailyPrices.push(prices[Math.max(0, Math.min(N - 1, idx))]);
                      }
                    } else {
                      for (let i = 0; i < 7; i++) {
                        dailyPrices.push(prices[Math.max(0, N - 1)]);
                      }
                    }
                  
                    let changesHTML = '<h3>Daily Profit/Loss (%)</h3><ul>';
                    for (let i = 1; i < dailyPrices.length; i++) {
                      const prev = dailyPrices[i - 1];
                      const curr = dailyPrices[i];
                      const pct = (prev === 0) ? 0 : ((curr - prev) / prev) * 100;
                      const dateLabel = labels[i];
                      if (pct > 0) {
                        changesHTML += '<li>' + dateLabel + ': <span class="increase">+' + pct.toFixed(2) + '% (Profit)</span></li>';
                      } else if (pct < 0) {
                        changesHTML += '<li>' + dateLabel + ': <span class="decrease">' + pct.toFixed(2) + '% (Loss)</span></li>';
                      } else {
                        changesHTML += '<li>' + dateLabel + ': 0.00% (No Change)</li>';
                      }
                    }
                    changesHTML += '</ul>';
                    document.getElementById('dailyChanges').innerHTML = changesHTML;
                  
                    const ctx = document.getElementById('chart').getContext('2d');
                    if (chartInstance) { chartInstance.destroy(); }
                    chartInstance = new Chart(ctx, {
                      type: 'line',
                      data: {
                        labels: labels,
                        datasets: [{
                          label: 'Price History (7 Days)',
                          data: dailyPrices,
                          borderColor: '#00ffff',
                          backgroundColor: 'rgba(0,255,255,0.15)',
                          fill: true,
                          tension: 0.4,
                          pointRadius: 4,
                          pointBackgroundColor: '#00ffff',
                          pointBorderColor: '#000000'
                        }]
                      },
                      options: {
                        plugins: { legend: { labels: { color: '#f1f5f9', font: { size: 14 } } } },
                        scales: {
                          x: { stacked: true, ticks: { color: '#f1f5f9', font: { size: 12 } }, grid: { color: '#333' } },
                          y: { stacked: true, ticks: { color: '#f1f5f9', font: { size: 12 } }, grid: { color: '#333' } }
                        }
                      }
                    });
                  }
                  </script>
                  </body>
                  </html>
                  """;

        File file = new File("crypto.html");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(html);
        }
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(file.toURI());
        } else {
            System.out.println("Open crypto.html manually in your browser.");
        }
    }
}
