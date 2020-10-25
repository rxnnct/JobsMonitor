fetch("/api/jobs-qty")
    .then(response => response.text())
    .then((response) => {
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            let responseArray = response.split(",");
            let responseArray2d = new Array();
            for (let i = 0; i < responseArray.length; i++) {
                responseArray2d[i] = responseArray[i].split("_");
            }
            for (let i=1; i < responseArray2d.length; i++) {
                for (let j = 1; j < responseArray2d[i].length; j++) {
                    responseArray2d[i][j] = parseInt(responseArray2d[i][j]);
                }
            }
            let data = google.visualization.arrayToDataTable(responseArray2d);
            let options = {
                title: 'Jobs statistics',
                curveType: 'function',
                legend: { position: 'bottom' }
            };
            let chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
            chart.draw(data, options);
        }
    })
    .catch(err => console.log(err));