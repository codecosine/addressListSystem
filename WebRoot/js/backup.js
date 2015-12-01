
var gridModule = angular.module('gridModule', ['ngGrid']);
gridModule.controller('StuListCtrl', function($scope) {
    $scope.myData = [
{
"address": "广州",
"id": "3114004923",
"name": "御坂2号",
"party": true,
"phone": "1323220000",
"sclass": "2班",
"sex": "男",
"uuid": "B6DE3F6B92C749738576983E6929314C"
},
{
"address": "广州",
"id": "3114004921",
"name": "御坂1号",
"party": true,
"phone": "1323220000",
"sclass": "2班",
"sex": "男",
"uuid": "D2B42D03F93741369D54E2D61C55737D"
},
{
"address": "广州",
"id": "3114004922",
"name": "御坂3号",
"party": true,
"phone": "1323220000",
"sclass": "2班",
"sex": "男",
"uuid": "DEC61A93C32C47049C138AE6E353922C"
}
];
    $scope.gridOptions = {
        data: 'myData',
        rowTemplate: '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
            '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
            '<div ng-cell></div>' +
            '</div></div>',
        multiSelect: false,
        enableCellSelection: true,
        enableRowSelection: false,
        enableCellEdit: true,
        enablePinning: true,
        columnDefs: [{
            field: 'index',
            displayName: '序号',
            width: 60,
            pinnable: false,
            sortable: false
        }, {
            field: 'id',
            displayName: '学号',
            enableCellEdit: true
        }, {
            field: 'name',
            displayName: '姓名',
            enableCellEdit: true,
            width: 220
        }, {
            field: 'sclass',
            displayName: '班级',
            enableCellEdit: true,
            width: 120
        }, {
            field: 'address',
            displayName: '联系地址',
            enableCellEdit: true,
            width: 120
        }, {
            field: 'sex',
            displayName: '性别',
            enableCellEdit: true,
            width: 120
        }, {
            field: 'party',
            displayName: '党员身份',
            enableCellEdit: true,
            width: 120
        }, {
            field: 'bookId',
            displayName: '操作',
            enableCellEdit: false,
            sortable: false,
            pinnable: false,
            cellTemplate: '<div><a ui-sref="bookdetail({bookId:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}">详情</a></div>'
        }],
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };
});