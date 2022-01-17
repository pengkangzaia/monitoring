function diskwait(data) {
    var myChart = echarts.init(document.getElementById('diskwait'));
        myChart.setOption(option = {
            backgroundColor: background_color,
            title: {
                subtext: '单位/ms',
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
                    name: "磁盘读等待时间",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.rawait;
                    }),
                    lineStyle: {
                        color: colors[0]
                    }
                },
                {
                    name: "磁盘写等待时间",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.wawait;
                    }),
                    lineStyle: {
                        color: colors[1]
                    }
                }]
        });
}