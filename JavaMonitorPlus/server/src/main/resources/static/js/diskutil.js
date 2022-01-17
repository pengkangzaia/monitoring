function diskutil(data) {
    var myChart = echarts.init(document.getElementById('diskutil'));
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
                    name: "磁盘IO占CPU利用率",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.util;
                    }),
                    lineStyle: {
                        color: colors[0]
                    }
                }]
        });
}