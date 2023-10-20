const baseURL = "http://localhost:8080/game";

async function fetchStatus() {
    try {
        const response = await fetch(`${baseURL}/status`);
        if (!response.ok) throw new Error('Network response was not ok');
        const data = await response.text();
        document.getElementById("status").innerText = data;
    } catch (error) {
        console.error("There was a problem with the fetch operation:", error.message);
    }
}

async function makeMove(row, col) {
    try {
        const response = await fetch(`${baseURL}/move?row=${row}&col=${col}`, { method: "POST" });
        if (!response.ok) throw new Error('Network response was not ok');
        const data = await response.text();
        updateBoard(data);
        await fetchStatus();
    } catch (error) {
        console.error("There was a problem with the fetch operation:", error.message);
    }
}

async function resetGame() {
    try {
        const response = await fetch(`${baseURL}/reset`, { method: "PUT" });
        if (!response.ok) throw new Error('Network response was not ok');
        const data = await response.text();
        updateBoard(data);   // <-- Add this line
        await fetchStatus();
    } catch (error) {
        console.error("There was a problem with the fetch operation:", error.message);
    }
}


function updateBoard(data) {
    const rows = data.split('\n');
    for (let i = 0; i < 3; i++) {
        const cells = rows[i] ? rows[i].split('|') : ["", "", ""];
        for (let j = 0; j < 3; j++) {
            document.querySelector(`table tr:nth-child(${i + 1}) td:nth-child(${j + 1})`).innerText = cells[j] || "";
        }
    }
}

fetchStatus();
updateBoard();