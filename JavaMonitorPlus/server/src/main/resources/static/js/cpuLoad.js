function cpuLoad(data) {
    var myChart = echarts.init(document.getElementById('cpuLoad'));
        myChart.setOption(option = {
            backgroundColor: background_color,
            title: {
                subtext: '单位/个',
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
                    name: "1m avg load",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.oneMinuteLoad;
                    }),
                    lineStyle: {
                        color: colors[0]
                    }
                },
                {
                    name: "5m avg load",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.fiveMinuteLoad;
                    }),
                    lineStyle: {
                        color: colors[1]
                    }
                },
                {
                    name: "15m avg load",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.fifteenMinuteLoad;
                    }),
                    lineStyle: {
                        color: colors[2]
                    }
                }]
        });
}