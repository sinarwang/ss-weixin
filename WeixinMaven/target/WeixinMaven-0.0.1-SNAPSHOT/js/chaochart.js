$(function () { 
    var colors = Highcharts.getOptions().colors,
        categories = ['����', '��ĥ', '��ֱ', '����'],
        name = '��������',
        data = [{
                y: 1000,
                color: colors[0],
                drilldown: {
                    name: '����',
                    categories: ['����', 'ƫ��', '����', '�̳�'],
                    data: [240, 100, 500, 300],
                    color: colors[0]
                }
            }, {
                y: 500,
                color: colors[1],
                drilldown: {
                    name: '��ĥ',
                    categories: ['����', 'ƫ��', '����', '�̳�'],
                    data: [270, 200, 500, 800],
                    color: colors[1]
                }
            }, {
                y: 700,
                color: colors[2],
                drilldown: {
                    name: '��ֱ',
                    categories: ['����', 'ƫ��', '����', '�̳�'],
                    data: [260, 370, 300,600],
                    color: colors[2]
                }
            }, {
                y: 800,
                color: colors[3],
                drilldown: {
                    name: '����',
                    categories: ['����', 'ƫ��', '����', '�̳�'],
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
            text: '�쳣��������'
        },
        subtitle: {
            text: '��Դ:����RFID��������ϵͳ'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: '����(kg)'
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
                    s = this.x +':<b>'+ this.y +'kg �鿴����</b><br/>';
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
        categories = ['����', 'ƫ��', '����', '�̳�'],
        name = '�쳣����',
        data = [{
                y: 500,
                color: colors[0],
                drilldown: {
                    name: '����',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [240, 100, 500, 300],
                    color: colors[0]
                }
            }, {
                y: 700,
                color: colors[1],
                drilldown: {
                    name: 'ƫ��',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [270, 200, 500, 800],
                    color: colors[1]
                }
            }, {
                y: 900,
                color: colors[2],
                drilldown: {
                    name: '����',
                    categories: ['2016-8-1', '2016-8-2', '2016-8-3', '2016-8-4'],
                    data: [260, 370, 300,600],
                    color: colors[2]
                }
            }, {
                y: 1000,
                color: colors[3],
                drilldown: {
                    name: '�̳�',
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
            text: '�쳣��������'
        },
        subtitle: {
            text: '��Դ:����RFID��������ϵͳ'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: '����(kg)'
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
                    s = this.x +':<b>'+ this.y +'kg �鿴����</b><br/>';
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