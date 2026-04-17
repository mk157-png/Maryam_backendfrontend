

// 1. Initialize the chart globally so we can update it later
let moodChart;

function initChart() {
    const ctx = document.getElementById("chart");

    moodChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
            datasets: [{
                label: "Daily Score",
                data: [], // Starts empty
                borderColor: "rgba(75,192,192,1)",
                backgroundColor: "rgba(75,192,192,0.2)",
                tension: 0.4,
                fill: true,
            }],
        },
        options: {
            scales: { 
                y: {
                    min: -5,
                    max: 5,
                },
            },
        },
    });
}

// 2. The combined fetch function
async function refreshMoodDashboard(userId) {
    const avgUrl = `http://localhost:8080/api/mood-analysis/user/${userId}/weekly-average`;
    const dailyUrl = `http://localhost:8080/api/mood-analysis/user/${userId}/daily-scales`;

    try {
        const [avgResponse, dailyResponse] = await Promise.all([
            fetch(avgUrl),
            fetch(dailyUrl)
        ]);

        const avgMessage = await avgResponse.text();
        let dailyScales = await dailyResponse.json();

        document.getElementById('mood-display').innerText = avgMessage;

        const chronologicalScales = dailyScales.reverse();

        // Update the chart object
        moodChart.data.datasets[0].data = chronologicalScales;
        console.log("arr", chronologicalScales)

        moodChart.update();

    } catch (error) {
        console.error("Dashboard update failed:", error);
    }
}

// 3. Start everything
window.addEventListener("DOMContentLoaded", () => {
    refreshMoodDashboard(1);
    initChart();
});

