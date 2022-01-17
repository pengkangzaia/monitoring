function diskserver(data) {
    var myChart = echarts.init(document.getElementById('diskserver'));
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
                    name: "磁盘IO操作服务时间",
                    type: 'line',
                    data: data.map(function (item) {
                        return item.svctm;
                    }),
                    lineStyle: {
                        color: colors[0]
                    }
                }]
        });
}