function cpuUsage(data) {
    var myChart = echarts.init(document.getElementById('cpuUsage'));
        myChart.setOption(option = {
            backgroundColor: background_color,
            title: {
                subtext: '单位/%',
                textStyle: {
                    color: '#fff'
                }
            },
            xAxis: {
                axisLine: { lineStyle: { color: '#8392A5' } },
                data: data.map(function (item) {
                    return item.date;
                }),
                nameTextStyle: {
                    color: '#fff'
                }
            },
            yAxis: {
                scale: true,
                axisLine: { lineStyle: { color: '#8392A5' } },
                splitLine: { show: false }
            },
            tooltip: my_tooltip,
            toolbox: my_toolbox,
            dataZoom: [{
                startValue: data[0].date
            }, {
                type: 'inside'
            }],
            visualMap: [{
                show: false,
                inRange: {
                    color: white
                }
            }],
            series: [
                {
                    name: "usage",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.cpuUsage;
                    }),
                    lineStyle: {
                        color: colors[0]
                    }
                }]
        });
}