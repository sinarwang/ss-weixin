$(function () { 
    var colors = Highcharts.getOptions().colors,
        categories = ['外抛', '内磨', '矫直', '精整'],
        name = '工序名称',
        data = [{
                y: 1000,
                color: colors[0],
                drilldown: {
                    name: '外抛',
                    categories: ['报废', '偏心', '裂纹', '短尺'],
                    data: [240, 100, 500, 300],
                    color: colors[0]
                }
            }, {
                y: 500,
                color: colors[1],
                drilldown: {
                    name: '内磨',
                    categories: ['报废', '偏心', '裂纹', '短尺'],
                    data: [270, 200, 500, 800],
                    color: colors[1]
                }
            }, {
                y: 700,
                color: colors[2],
                drilldown: {
                    name: '矫直',
                    categories: ['报废', '偏心', '裂纹', '短尺'],
                    data: [260, 370, 300,600],
                    color: colors[2]
                }
            }, {
                y: 800,
                color: colors[3],
                drilldown: {
                    name: '精整',
                    categories: ['报废', '偏心', '裂纹', '短尺'],
                    data: [160, 270, 300,700],
                    color: colors[3]
                }
            }];
    function setChart(name, categories, data, color) {
    chart.xAxis[0].setCategories(categories, false);
    chart.series[0].remove(false);
    chart.addSeries({
        name: name,
        data: data,
        color: color || 'white'
    }, false);
    chart.redraw();
    }
    var chart = $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '异常工序详情'
        },
        subtitle: {
            text: '来源:上上RFID生产管理系统'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: '重量(kg)'
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function() {
                            var drilldown = this.drilldown;
                            if (drilldown) { // drill down
                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                            } else { // restore
                                setChart(name, categories, data);
                            }
                        }
                    }
                },
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'kg';
                    }
                }
            }
        },
        tooltip: {
            formatter: function() {
                var point = this.point,
                    s = this.x +':<b>'+ this.y +'kg 查看详情</b><br/>';
                return s;
            }
        },
        series: [{
            name: name,
            data: data,
            color: 'white'
        }],
        exporting: {
            enabled: false
        }
    })
    .highcharts();
});

$(function () { 
    var colors = Highcharts.getOptions().colors,
        categories = ['报废', '偏心', '裂纹', '短尺'],
        name = '异常类型',
        data = [{
                y: 500,
                color: colors[0],
                drilldown: {
                    name: '报废',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [240, 100, 500, 300],
                    color: colors[0]
                }
            }, {
                y: 700,
                color: colors[1],
                drilldown: {
                    name: '偏心',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [270, 200, 500, 800],
                    color: colors[1]
                }
            }, {
                y: 900,
                color: colors[2],
                drilldown: {
                    name: '裂纹',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [260, 370, 300,600],
                    color: colors[2]
                }
            }, {
                y: 1000,
                color: colors[3],
                drilldown: {
                    name: '短尺',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [160, 270, 300,700],
                    color: colors[3]
                }
            }];
    function setChart(name, categories, data, color) {
    chart.xAxis[0].setCategories(categories, false);
    chart.series[0].remove(false);
    chart.addSeries({
        name: name,
        data: data,
        color: color || 'white'
    }, false);
    chart.redraw();
    }
    var chart = $('#container-two').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '异常类型详情'
        },
        subtitle: {
            text: '来源:上上RFID生产管理系统'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: '重量(kg)'
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function() {
                            var drilldown = this.drilldown;
                            if (drilldown) { // drill down
                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                            } else { // restore
                                setChart(name, categories, data);
                            }
                        }
                    }
                },
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'kg';
                    }
                }
            }
        },
        tooltip: {
            formatter: function() {
                var point = this.point,
                    s = this.x +':<b>'+ this.y +'kg 查看详情</b><br/>';
                return s;
            }
        },
        series: [{
            name: name,
            data: data,
            color: 'white'
        }],
        exporting: {
            enabled: false
        }
    })
    .highcharts();
}); 